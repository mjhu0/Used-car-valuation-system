package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Car;
import service.CarService;
import javax.swing.table.DefaultTableModel;

public class AddCarFrame extends JFrame {
    private JTextField brandField, seriesField, modelField, plateNumberField;

    private DefaultTableModel carTableModel;

    public AddCarFrame(DefaultTableModel carTableModel) {
        this.carTableModel = carTableModel;

        setTitle("添加汽车信息");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("品牌:"));
        brandField = new JTextField();
        panel.add(brandField);

        panel.add(new JLabel("车系:"));
        seriesField = new JTextField();
        panel.add(seriesField);

        panel.add(new JLabel("型号:"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("车牌号:"));
        plateNumberField = new JTextField();
        panel.add(plateNumberField);

        JButton addButton = new JButton("添加");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCar();
            }
        });
        panel.add(addButton);

        add(panel);
    }

    private void addCar() {
        String brand = brandField.getText();
        String series = seriesField.getText();
        String model = modelField.getText();
        String plateNumber = plateNumberField.getText();

        Car car = new Car(brand, series, model, plateNumber);
        CarService carService = new CarService();
        carService.addCar(car);

        carTableModel.addRow(new Object[]{car.getBrand(), car.getSeries(), car.getModel(), car.getLicensePlate()});
        dispose();
    }
}
