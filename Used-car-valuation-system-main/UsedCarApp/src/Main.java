import javax.swing.*;

import database.CreateDatabase;
import gui.LoginFrame;
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //创建数据库
                new CreateDatabase();
                // 创建登录界面并显示
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}