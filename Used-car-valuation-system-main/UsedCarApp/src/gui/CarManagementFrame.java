package gui;

import model.Car;
import service.CarService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarManagementFrame extends JFrame {
    private JTable carTable;
    private DefaultTableModel tableModel;
    private CarService carService;

    public CarManagementFrame() {
        carService = new CarService();

        setTitle("管理汽车信息");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 表格模型
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "车主ID", "品牌", "车系", "型号", "座位数", "排量", "吨位", "出厂日期", "登记日期", "车牌号", "发动机号", "车架号", "使用性质", "行驶里程"}, 0);
        carTable = new JTable(tableModel);

        // 加载数据
        loadCarData();

        // 按钮面板
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("添加汽车");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddCarFrame(tableModel).setVisible(true);
            }
        });

        JButton deleteButton = new JButton("删除汽车");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carTable.getSelectedRow();
                if (selectedRow != -1) {
                    int carId = (int) tableModel.getValueAt(selectedRow, 0);
                    carService.deleteCar(carId);
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(new JScrollPane(carTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCarData() {
        List<Car> cars = carService.getAllCars();
        for (Car car : cars) {
            tableModel.addRow(new Object[]{
                    car.getId(),
                    car.getOwnerId(),
                    car.getBrand(),
                    car.getSeries(),
                    car.getModel(),
                    car.getSeatCount(),
                    car.getDisplacement(),
                    car.getTonnage(),
                    car.getManufactureDate(),
                    car.getRegistrationDate(),
                    car.getPlateNumber(),
                    car.getEngineNumber(),
                    car.getChassisNumber(),
                    car.getUsageType(),
                    car.getMileage()
            });
        }
    }
}
