package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class AddValuation extends JFrame {
    private JTextField brandField, seriesField, modelField, mileageField, purchaseDateField;
    private JTextField resultField;

    public AddValuation() {
        setTitle("--添加评估--");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2,10,10));

        panel.add(new JLabel("汽车品牌:"));
        brandField = new JTextField();
        panel.add(brandField);

        panel.add(new JLabel("车系:"));
        seriesField = new JTextField();
        panel.add(seriesField);

        panel.add(new JLabel("型号:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("行驶里程(km):"));
        mileageField = new JTextField();
        panel.add(mileageField);

        panel.add(new JLabel("购买时间:"));
        purchaseDateField = new JTextField();
        panel.add(purchaseDateField);

        JButton estimateButton = new JButton("估价");
        estimateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                estimate();
            }
        });
        panel.add(estimateButton);

        JButton backButton = new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(backButton);

        add(panel);
    }

    private void estimate() {
        String brand = brandField.getText();
        String series = seriesField.getText();
        String model = modelField.getText();
        int mileage = Integer.parseInt(mileageField.getText());
        String purchaseDate = purchaseDateField.getText();

        double estimatedPrice = calculateQuickValuation(brand, series, model, mileage, purchaseDate);

        resultField.setText("估计价格: " + estimatedPrice + " 万元");
    }

    private double calculateQuickValuation(String brand, String series, String model, int mileage, String purchaseDate) {
        double basePrice = 10; // 假设基础价格
        double depreciationRate = 0.05; // 每年折旧5%
        double mileageFactor = 0.0001; // 每公里折旧0.01%

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int purchaseYear = Integer.parseInt(purchaseDate.substring(0, 4));
        int usedYears = currentYear - purchaseYear;

        double depreciation = basePrice * depreciationRate * usedYears;
        double mileageDepreciation = mileage * mileageFactor;

        return basePrice - depreciation - mileageDepreciation;
    }

    public static void main(String[] args) {
        new AddValuation().setVisible(true);
    }
}

