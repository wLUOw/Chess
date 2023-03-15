package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class RegisterFrame extends JFrame implements ActionListener {
    JTextField tfAccount;
    JPasswordField tfPassword;

    public RegisterFrame(){
        setTitle("登录/注册");
        setSize(380, 320);
        setLocationRelativeTo(null); //设置窗口居中
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(null);//使用绝对布局

        //账号文字
        JLabel lbAccount = new JLabel("账号");
        lbAccount.setFont(new Font("", Font.BOLD, 20));
        lbAccount.setForeground(Color.BLUE);
        lbAccount.setBounds(50, 50, 50, 40);
        add(lbAccount);
        //账号输入框
        tfAccount = new JTextField();
        tfAccount.setBounds(110, 50, 200, 40);
        add(tfAccount);
        //账号文字
        JLabel lbPaasword = new JLabel("密码");
        lbPaasword.setFont(new Font("", Font.BOLD, 20));
        lbPaasword.setForeground(Color.BLUE);
        lbPaasword.setBounds(50, 100, 50, 40);
        add(lbPaasword);
        //账号输入框
        tfPassword = new JPasswordField();
        tfPassword.setBounds(110, 100, 200, 40);
        add(tfPassword);

        //注册按钮
        JButton btnReg = new JButton("注册");
        btnReg.setBounds(50, 150, 260, 40);
        btnReg.setActionCommand("register");
        btnReg.addActionListener(this);
        add(btnReg);

        ImageIcon bgPicture = new ImageIcon("src/picture/background/LoginFrame背景.png");
        JLabel bgLabel = new JLabel(bgPicture);
        bgLabel.setSize(380, 350);
        bgLabel.setLocation(0, -45);
        add(bgLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "register":
                String account = tfAccount.getText();
                char[] password = tfPassword.getPassword();
                String passwordStr = new String(password);
                try {
                    FileWriter fileWritter = new FileWriter("customers\\customers.txt",true);
                    fileWritter.write(account);
                    fileWritter.write(" ");
                    fileWritter.write(passwordStr);
                    fileWritter.write(" ");
                    fileWritter.write("0");
                    fileWritter.write("\n");

                    fileWritter.close();
                } catch (Exception e1){}

                JOptionPane.showMessageDialog(null, "注册成功", "", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                new ChooseFrame(false, null);
                break;
            default:
                break;
        }
    }
}
