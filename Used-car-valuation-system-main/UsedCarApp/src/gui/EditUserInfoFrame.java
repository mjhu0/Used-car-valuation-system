package gui;

import database.WriteDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditUserInfoFrame extends JDialog implements ActionListener {
    /*
    本类为用户编辑个人信息界面，在LoginFrame和ManagePersonalInfo中被实例化；
    唤醒位置：LoginFrame中的btRegister（注册）按钮监听器；
            ManagePersonalInfo中的btModify(修改个人信息)按钮监听器。
     */

    private static String title;
    private static String account;
    private static String password;
    private JPanel panel1=new JPanel();
    private JLabel lbName = new JLabel("      姓名：");
    private JTextField tfName = new JTextField(22);
    private JLabel lbSex = new JLabel("      性别：");
    private JTextField tfSex= new JTextField(22);

//    private JRadioButton rbSex1 = new JRadioButton("男 ");
//    private JRadioButton rbSex2 = new JRadioButton("女                                       ");
    private JLabel lbAge = new JLabel("      年龄：");
    private JTextField tfAge= new JTextField(22);
    private JLabel lbLocation = new JLabel("   所在地：");
    private JTextField tfLocation= new JTextField(22);
    private JLabel lbID = new JLabel("证件号码：");
    private JTextField tfID= new JTextField(22);
    private JLabel lbPhone = new JLabel("联系电话：");
    private JTextField tfPhone= new JTextField(22);
    private JLabel lbEmail = new JLabel("邮箱地址：");
    private JTextField tfEmail= new JTextField(22);

    private JButton btRegister = new JButton("注册");
    private JButton btCancel = new JButton("取消");

    public EditUserInfoFrame(String title, String account, String password) throws HeadlessException {
        this.title=title;
        this.account=account;
        this.password=password;
        super.setTitle("--注册--");
        this.setLayout(new FlowLayout());
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.add(lbName);
        this.add(tfName);
        this.add(lbSex);
        this.add(tfSex);
//        ButtonGroup bgSex=new ButtonGroup();
//        bgSex.add(rbSex1);
//        bgSex.add(rbSex2);
//        this.add(rbSex1);
//        this.add(rbSex2);
        this.add(lbAge);
        this.add(tfAge);
        this.add(lbLocation);
        this.add(tfLocation);
        this.add(lbID);
        this.add(tfID);
        this.add(lbPhone);
        this.add(tfPhone);
        this.add(lbEmail);
        this.add(tfEmail);
        panel1.add(btRegister);
        panel1.add(btCancel);
        this.add(panel1);

        this.setBounds(600, 300, 300, 400);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
//        toCenter(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//点击叉号退出程序
        this.setResizable(false);//窗体大小不可改变
        this.setVisible(true);//窗体可视
        /*********增加监听**********/
//        rbSex1.addActionListener(this);
//        rbSex2.addActionListener(this);
        btRegister.addActionListener(this);
        btCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == btRegister) {
                String name=tfName.getText();
                String sex=tfSex.getText();
//                final String[] sex = {null};
//                rbSex1.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (rbSex1.isSelected()) sex[0] = "男";
//                        else {
//                            sex[0] = "女";
//                        }
//                    }
//                });
                int age= Integer.parseInt(tfAge.getText());
                String location=tfLocation.getText();
                String id=tfID.getText();
                String phone=tfPhone.getText();
                String email=lbEmail.getText();

                WriteDatabase wdb=new WriteDatabase();
                wdb.addUserLogin(title,account,password);
                wdb.addUserInfo(account,name,sex,age,location,id,phone,email);

                /*
                此处需编写代码：实现账号密码用户名向数据库t_User表的写入。
                格式要求：姓名：无限制；
                        性别：单选；
                        年龄：12~120之间唯一整数；
                        所在地：无限制字符串；
                        证件号码：18位字符串；
                        联系电话：11位整数；
                        邮箱地址：含“@”字符的字符串；
                提示信息：若写入失败，则提示“编辑信息失败！”；
                        若未填写姓名，则提示“姓名不能为空”；
                        若未选择性别，则提示“请选择性别”；
                        若未填写所在地，则提示“所在地不能为空”；
                        若未填写证件号码，则提示“证件号码不能为空”；
                        若未填写联系电话，则提示“联系电话不能为空”；
                        若未填写邮箱地址，则提示“邮箱地址不能为空”；

                        若年龄不满足格式要求，则提示“年龄超出合理范围”；
                        若证件号码不满足格式要求，则提示“请填写18位有效证件号码”；
                        若联系电话不满足格式要求，则提示“请填写11位有效电话号码”。
                        若邮箱地址不满足格式要求，则提示“请填写含’@‘的有效邮箱地址”。
                 */
    //            String name = tfName.getText();
    //            String sex = ()?:;
    //            String password = new String(pfPassword.getPassword());
    //            AuthService authService = new AuthService();
    //
    //            if (authService.login(account, password)) {
    //                JOptionPane.showMessageDialog(this, "登录失败");
    //                return;
    //            }
                JOptionPane.showMessageDialog(this, "注册成功，请返回登录！");
            }
        } catch (HeadlessException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } finally {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new EditUserInfoFrame(account, account, password);
    }
}
