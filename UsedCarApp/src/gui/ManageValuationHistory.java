package gui;

import model.Valuation;
import service.ValuationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageValuationHistory extends JFrame {
    private JTable valuationTable;
    private DefaultTableModel tableModel;
    private ValuationService valuationService;

    public ManageValuationHistory() {
        valuationService = new ValuationService();

        setTitle("历史估价记录");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 表格模型
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "车辆ID", "估价日期", "估价类型", "估计价格"}, 0);
        valuationTable = new JTable(tableModel);

        // 加载数据
        loadValuationData();

        // 按钮面板
        JPanel buttonPanel = new JPanel();

        JButton deleteButton = new JButton("删除记录");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRecord();
            }
        });

        JButton downloadButton = new JButton("下载记录");
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                downloadSelectedRecord();
            }
        });

        buttonPanel.add(deleteButton);
        buttonPanel.add(downloadButton);

        add(new JScrollPane(valuationTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadValuationData() {
        List<Valuation> valuations = valuationService.getAllValuations();
        for (Valuation valuation : valuations) {
            tableModel.addRow(new Object[]{
                    valuation.getId(),
                    valuation.getVehicleId(),
                    valuation.getValuationDate(),
                    valuation.getValuationType(),
                    valuation.getEstimatedPrice()
            });
        }
    }

    private void deleteSelectedRecord() {
        int selectedRow = valuationTable.getSelectedRow();
        if (selectedRow != -1) {
            int valuationId = (int) tableModel.getValueAt(selectedRow, 0);
            valuationService.deleteValuation(valuationId);
            tableModel.removeRow(selectedRow);
        }
    }

    private void downloadSelectedRecord() {
        int selectedRow = valuationTable.getSelectedRow();
        if (selectedRow != -1) {
            int valuationId = (int) tableModel.getValueAt(selectedRow, 0);
            Valuation valuation = valuationService.getValuationById(valuationId);
            // Implement logic to save valuation to file
            saveValuationToFile(valuation);
        }
    }

    private void saveValuationToFile(Valuation valuation) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("保存估价记录");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                try (java.io.FileWriter writer = new java.io.FileWriter(fileToSave)) {
                    writer.write("估价ID: " + valuation.getId() + "\n");
                    writer.write("车辆ID: " + valuation.getVehicleId() + "\n");
                    writer.write("估价日期: " + valuation.getValuationDate() + "\n");
                    writer.write("估价类型: " + valuation.getValuationType() + "\n");
                    writer.write("估计价格: " + valuation.getEstimatedPrice() + "\n");
                }
                JOptionPane.showMessageDialog(this, "文件已保存", "信息", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "文件保存失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
