public class CarManagementFrame extends JFrame {
    private JTable carTable;
    private CarTableModel carTableModel;

    public CarManagementFrame() {
        setTitle("管理汽车信息");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        carTableModel = new CarTableModel();
        carTable = new JTable(carTableModel);

        JButton addButton = new JButton("添加汽车");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddCarFrame(carTableModel).setVisible(true);
            }
        });

        JButton deleteButton = new JButton("删除汽车");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carTable.getSelectedRow();
                if (selectedRow != -1) {
                    carTableModel.deleteCar(selectedRow);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(deleteButton);

        add(new JScrollPane(carTable), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }
}