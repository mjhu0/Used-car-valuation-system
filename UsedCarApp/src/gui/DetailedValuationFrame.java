package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class DetailedValuationFrame extends JFrame {
    private JTextField brandField, seriesField, modelField, mileageField, purchaseDateField;
    private JTextField seatCountField, displacementField, tonnageField, registrationDateField, engineNumberField, chassisNumberField;
    private JTextField resultField;

    public DetailedValuationFrame() {
        setTitle("详细估价");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(12, 2));

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

        panel.add(new JLabel("座位数:"));
        seatCountField = new JTextField();
        panel.add(seatCountField);

        panel.add(new JLabel("排量:"));
        displacementField = new JTextField();
        panel.add(displacementField);

        panel.add(new JLabel("吨位:"));
        tonnageField = new JTextField();
        panel.add(tonnageField);

        panel.add(new JLabel("登记时间:"));
        registrationDateField = new JTextField();
        panel.add(registrationDateField);

        panel.add(new JLabel("发动机号:"));
        engineNumberField = new JTextField();
        panel.add(engineNumberField);

        panel.add(new JLabel("车架号:"));
        chassisNumberField = new JTextField();
        panel.add(chassisNumberField);

        JButton estimateButton = new JButton("估价");
        estimateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                estimate();
            }
        });
        panel.add(estimateButton);

        resultField = new JTextField();
        resultField.setEditable(false);
        panel.add(resultField);

        add(panel);
    }

    private void estimate() {
        String brand = brandField.getText();
        String series = seriesField.getText();
        String model = modelField.getText();
        int mileage = Integer.parseInt(mileageField.getText());
        String purchaseDate = purchaseDateField.getText();
        int seatCount = Integer.parseInt(seatCountField.getText());
        double displacement = Double.parseDouble(displacementField.getText());
        double tonnage = Double.parseDouble(tonnageField.getText());
        String registrationDate = registrationDateField.getText();
        String engineNumber = engineNumberField.getText();
        String chassisNumber = chassisNumberField.getText();

        double estimatedPrice = calculateDetailedValuation(brand, series, model, mileage, purchaseDate, seatCount, displacement, tonnage, registrationDate, engineNumber, chassisNumber);

        resultField.setText("估计价格: " + estimatedPrice + " 万元");
    }

    private double calculateDetailedValuation(String brand, String series, String model, int mileage, String purchaseDate, int seatCount, double displacement, double tonnage, String registrationDate, String engineNumber, String chassisNumber) {
        // 简化的估价算法
        double basePrice = 10; // 假设基础价格
        double depreciationRate = 0.05; // 每年折旧5%
        double mileageFactor = 0.0001; // 每公里折旧0.01%
        double seatCountFactor = 0.1; // 每个座位加0.1万元
        double displacementFactor = 0.2; // 每升排量加0.2万元
        double tonnageFactor = 0.3; // 每吨加0.3万元

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int purchaseYear = Integer.parseInt(purchaseDate.substring(0, 4));
        int usedYears = currentYear - purchaseYear;

        double depreciation = basePrice * depreciationRate * usedYears;
        double mileageDepreciation = mileage * mileageFactor;
        double seatCountDepreciation = seatCount * seatCountFactor;
        double displacementDepreciation = displacement * displacementFactor;
        double tonnageDepreciation = tonnage * tonnageFactor;

        return basePrice - depreciation - mileageDepreciation + seatCountDepreciation + displacementDepreciation + tonnageDepreciation;
    }
}