package gui;

import service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    /*
    本类为注册界面，在LoginFrame中被实例化；
    唤醒位置：LoginFrame中的btRegister（注册）按钮监听器
     */
    private JLabel lbTitle = new JLabel("请设置用户名：");
    private JTextField tfTitle = new JTextField(19);
    private JLabel lbAccount = new JLabel("   请设置账号：");
    private JTextField tfAccount = new JTextField(19);
    private JLabel lbPasword = new JLabel("   请设置密码：");
    private JPasswordField pfPassword = new JPasswordField(19);
    private JButton btContinue = new JButton("继续");
    private JButton btCancel = new JButton("取消");

    public RegisterFrame() {
        super("--注册--");
        this.setLayout(new FlowLayout());
        this.add(lbTitle);
        this.add(tfTitle);
        this.add(lbAccount);
        this.add(tfAccount);
        this.add(lbPasword);
        this.add(pfPassword);
        this.add(btContinue);
        this.add(btCancel);
        this.setBounds(650, 350, 300, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//点击叉号退出程序
        this.setResizable(false);//窗体大小不可改变
        this.setVisible(true);//窗体可视
        /*********增加监听**********/
        btContinue.addActionListener(this);
        btCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btContinue) {
            String title = tfTitle.getText();
            String account = tfAccount.getText();
            String password = new String(pfPassword.getPassword());


            /*
            此处需要编写代码：实现账号密码用户名向数据库t_Login表的写入
            格式要求：用户名：无限制
                    账号：10位整数
                    密码：不少于6位的字符串
            提示信息：若写入失败，则提示“注册失败！”；
                    若未填写用户名，则提示“用户名不能为空”；
                    若未填写账号，则提示“账号不能为空”；
                    若账号不满足格式要求，则提示“账号应为10位数字”
                    若未填写密码，则提示“密码不能为空”；
                    若账号不满足格式要求，则提示“密码不能少于6位”
             */

//            AuthService authService = new AuthService();
//            if (authService.login(account, password)) {
//                JOptionPane.showMessageDialog(this, "登录失败");
//                return;
//            }

//            JOptionPane.showMessageDialog(this, "注册成功，请返回登录！");

            //进入个人信息编辑界面
            new EditUserInfoFrame(title,account,password);

            this.dispose();
        } else {
            this.dispose();
        }
    }

//    public static void main(String[] args) {
//        new RegisterFrame();
//    }
}
