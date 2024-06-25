package gui;

import javax.swing.*;

import database.Database;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField usernameField; // 用户名
    private JPasswordField passwordField; // 密码

    public LoginFrame() {
        setTitle("系统登录");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("用户名:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("密码:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        panel.add(loginButton);

        JButton registerButton = new JButton("注册");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        panel.add(registerButton);

        add(panel);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                new MainFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = Database.connect();
             PreparedStatement checkUserStmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
             PreparedStatement insertUserStmt = conn.prepareStatement("INSERT INTO users(username, password) VALUES(?, ?)")) {
            checkUserStmt.setString(1, username);
            ResultSet rs = checkUserStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "用户名已存在", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                insertUserStmt.setString(1, username);
                insertUserStmt.setString(2, password);
                insertUserStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
