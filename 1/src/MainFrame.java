public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("二手汽车估价系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu carMenu = new JMenu("汽车管理");
        menuBar.add(carMenu);

        JMenuItem manageCars = new JMenuItem("管理汽车信息");
        manageCars.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CarManagementFrame().setVisible(true);
            }
        });
        carMenu.add(manageCars);

        JMenu valuationMenu = new JMenu("估价管理");
        menuBar.add(valuationMenu);

        JMenuItem quickValuation = new JMenuItem("快速估价");
        quickValuation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuickValuationFrame().setVisible(true);
            }
        });
        valuationMenu.add(quickValuation);

        JMenuItem detailedValuation = new JMenuItem("详细估价");
        detailedValuation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DetailedValuationFrame().setVisible(true);
            }
        });
        valuationMenu.add(detailedValuation);

        JMenuItem historyValuation = new JMenuItem("历史估价记录");
        historyValuation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HistoryValuationFrame().setVisible(true);
            }
        });
        valuationMenu.add(historyValuation);
    }
}