package gui;

import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame{
    /*
    本类为系统主界面，在LoginFrame中被实例化
     */
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuValuation = new JMenu("评估管理");
    private JMenuItem addValuation=new JMenuItem("添加评估");
    private JMenuItem manageValuationHistory=new JMenuItem("评估历史管理");
    private JMenu menuPersonalInfo = new JMenu("个人信息");
    private JMenuItem managePersonalInfo=new JMenuItem("管理个人信息");
    private JMenuItem changePassword=new JMenuItem("修改登录密码");
    private JMenuItem serviceAssistant =new JMenu("客服助手");
    public MainFrame() {

        setTitle("SHC-二手车估价系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.setJMenuBar(menuBar);
        menuBar.add(menuValuation);
        menuValuation.add(addValuation);
        menuValuation.add(manageValuationHistory);
        menuBar.add(menuPersonalInfo);
        menuPersonalInfo.add(managePersonalInfo);
        menuPersonalInfo.add(changePassword);
        menuBar.add(serviceAssistant);

        /*********增加监听**********/
        //addValuation添加评估
        addValuation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddValuation().setVisible(true);
            }
        });

        //manageValuationHistory评估历史管理
        manageValuationHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageValuationHistory().setVisible(true);
            }
        });
        
        //managePersonalInfo管理个人信息
        managePersonalInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagePersonalInfo().setVisible(true);
            }
        });
        
        //changePassword修改登录密码
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePassword().setVisible(true);
            }
        });
        
        //serviceAssistant客服助手
        serviceAssistant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ServiceAssistant().setVisible(true);
            }
        });


//        menuBar.add(carMenu);
//
//        JMenuItem manageCars = new JMenuItem("管理汽车信息");
//        manageCars.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new CarManagementFrame().setVisible(true);
//            }
//        });
//        carMenu.add(manageCars);
//
//        JMenu valuationMenu = new JMenu("估价管理");
//        menuBar.add(valuationMenu);
//
//        JMenuItem quickValuation = new JMenuItem("快速估价");
//        quickValuation.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new QuickValuationFrame().setVisible(true);
//            }
//        });
//        valuationMenu.add(quickValuation);
//
//        JMenuItem detailedValuation = new JMenuItem("详细估价");
//        detailedValuation.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new DetailedValuationFrame().setVisible(true);
//            }
//        });
//        valuationMenu.add(detailedValuation);
//
//        JMenuItem historyValuation = new JMenuItem("历史估价记录");
//        historyValuation.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new HistoryValuationFrame().setVisible(true);
//            }
//        });
//        valuationMenu.add(historyValuation);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}