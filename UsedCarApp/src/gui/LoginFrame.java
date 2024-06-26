package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import database.Database;
import service.AuthService;

//public class LoginFrame extends JFrame {
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//
//    public LoginFrame() {
//        setTitle("系统登录");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        panel.setBounds(400,300,400,250);
//
//        panel.add(new JLabel("用户名:"));
//        usernameField = new JTextField(18);
//        panel.add(usernameField);
//
//        panel.add(new JLabel("密码:"));
//        passwordField = new JPasswordField(18);
//        panel.add(passwordField);
//
//        JButton loginButton = new JButton("登录");
//        loginButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                login();
//            }
//        });
//        panel.add(loginButton);
//
//        add(panel);
//    }
//
//    private void login() {
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//
//        AuthService authService = new AuthService();
//        if (authService.login(username, password)) {
//            new MainFrame().setVisible(true);
//            dispose();
//        } else {
//            JOptionPane.showMessageDialog(this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}
public class LoginFrame extends JFrame implements ActionListener {

    private JLabel lbAccount = new JLabel("请输入账号");
    private JTextField tfAccount = new JTextField(18);
    private JLabel lbPasword = new JLabel("请输入密码");
    private JPasswordField pfPassword = new JPasswordField(18);
    private JButton btLogin = new JButton("登录");
    private JButton btRegister = new JButton("注册");
    private JButton btExit = new JButton("退出");

    public LoginFrame() {
        super("--登录--");
        this.setLayout(new FlowLayout());
        this.add(lbAccount);
        this.add(tfAccount);
        this.add(lbPasword);
        this.add(pfPassword);
        this.add(btLogin);
        this.add(btRegister);
        this.add(btExit);
        this.setBounds(600, 300, 300, 200);
//        toCenter(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//点击叉号退出程序
        this.setResizable(false);//窗体大小不可改变
        this.setVisible(true);//窗体可视
        /*********增加监听**********/
        btLogin.addActionListener(this);
        btRegister.addActionListener(this);
        btExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btLogin) {
            String account = tfAccount.getText();
            String password = new String(pfPassword.getPassword());
            AuthService authService = new AuthService();
            if (authService.login(account, password)) {
                JOptionPane.showMessageDialog(this, "登录失败！");
                return;
            }
            JOptionPane.showMessageDialog(this, "登录成功！");
            new MainFrame().setVisible(true);
            this.dispose();
        } else if (e.getSource() == btRegister) {
            new RegisterFrame();
        } else {
            JOptionPane.showMessageDialog(this, "谢谢光临！");
            System.exit(0);
        }
    }
}

//    public static void main(String[] args) {
//        new LoginFrame();
//    }