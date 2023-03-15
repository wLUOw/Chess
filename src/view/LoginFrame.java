package view;

import util.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginFrame extends JFrame implements ActionListener {
    ChooseFrame chooseFrame;
    JTextField tfAccount;
    JPasswordField tfPassword;
    ArrayList<Customer> customers;

    public LoginFrame(ArrayList<Customer> customers,ChooseFrame chooseFrame){
        this.chooseFrame = chooseFrame;
        this.customers = customers;

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
        //登录按钮
        JButton btnLogin = new JButton("登录");
        btnLogin.setBounds(50, 150, 260, 40);
        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);
        add(btnLogin);
        //注册按钮
        JButton btnReg = new JButton("注册");
        btnReg.setBounds(50, 200, 260, 40);
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
            case "login":
                login();
                break;
            case "register":
                register();
                break;
            default:
                break;
        }
    }

    private void login(){
        HashMap<String, String> accPw = new HashMap<>();
        HashMap<String, Customer> accCus = new HashMap<>();
        if (customers.size() != 0){
            for (int i = 0; i < customers.size(); i++){
                accPw.put(customers.get(i).getName(), customers.get(i).getPassword());
            }
            for (int i = 0; i < customers.size(); i++){
                accCus.put(customers.get(i).getName(), customers.get(i));
            }
        }

        String account = tfAccount.getText();
        char[] password = tfPassword.getPassword();
        String passwordStr = new String(password);
        if (accPw.containsKey(account)){  // 包含此用户
            if (passwordStr.equals(accPw.get(account))){  // 密码正确
                setVisible(false);
                new ChooseFrame(true, accCus.get(account));
                chooseFrame.setVisible(false);
            } else {  // 密码错误
                JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else {  // 没有此用户
            JOptionPane.showMessageDialog(null, "没有此用户", "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void register(){
        setVisible(false);
        new RegisterFrame();
    }
}
