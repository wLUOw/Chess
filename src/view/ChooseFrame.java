package view;

import util.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ChooseFrame extends JFrame {

    boolean isLogin; // 是否登录
    Customer customer; // 当前用户
    ArrayList<Customer> customers = new ArrayList<>(); // 所有用户列表
    ChooseFrame chooseFrame;
    public ChooseFrame(boolean isLogin, Customer customer){
        chooseFrame = this;
        try {
            File file = new File("customers\\customers.txt");

            String temp;
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
            ArrayList<String> readList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(read);

            while(true){
                if (!((temp = reader.readLine()) != null && !"".equals(temp))) break;
                readList.add(temp);
                System.out.println(temp);
            }

            for (int i = 0; i < readList.size(); i++){
                String[] strArr = readList.get(i).split(" ");
                customers.add(new Customer(strArr[0], strArr[1], Integer.parseInt(strArr[2])));
            }

        } catch (Exception e){}

        this.isLogin = isLogin;
        this.customer = customer;

        this.setTitle("国际象棋");
        this.setLayout(null);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口可以关闭

        ImageIcon mainBtn1Picture = new ImageIcon("src/picture/button/难度一白方.png");
        JLabel mainBtn1Label = new JLabel(mainBtn1Picture);
        mainBtn1Label.setSize(200, 60);
        mainBtn1Label.setLocation(150, 80);
        add(mainBtn1Label);

        ImageIcon mainBtn2Picture = new ImageIcon("src/picture/button/难度一黑方.png");
        JLabel mainBtn2Label = new JLabel(mainBtn2Picture);
        mainBtn2Label.setSize(200, 60);
        mainBtn2Label.setLocation(150, 180);
        add(mainBtn2Label);

        ImageIcon mainBtn3Picture = new ImageIcon("src/picture/button/难度二白方.png");
        JLabel mainBtn3Label = new JLabel(mainBtn3Picture);
        mainBtn3Label.setSize(220, 60);
        mainBtn3Label.setLocation(150, 280);
        add(mainBtn3Label);

        ImageIcon mainBtn4Picture = new ImageIcon("src/picture/button/难度二黑方.png");
        JLabel mainBtn4Label = new JLabel(mainBtn4Picture);
        mainBtn4Label.setSize(200, 60);
        mainBtn4Label.setLocation(150, 400);
        add(mainBtn4Label);

        ImageIcon mainBtn5Picture = new ImageIcon("src/picture/button/用户.png");
        JLabel mainBtn5Label = new JLabel(mainBtn5Picture);
        mainBtn5Label.setSize(100, 40);
        mainBtn5Label.setLocation(450, 20);
        add(mainBtn5Label);

        ImageIcon mainBtn6Picture = new ImageIcon("src/picture/button/排行榜.png");
        JLabel mainBtn6Label = new JLabel(mainBtn6Picture);
        mainBtn6Label.setSize(100, 40);
        mainBtn6Label.setLocation(450, 80);
        add(mainBtn6Label);

        ImageIcon mainBtn7Picture = new ImageIcon("src/picture/button/返回.png");
        JLabel mainBtn7Label = new JLabel(mainBtn7Picture);
        mainBtn7Label.setSize(200, 60);
        mainBtn7Label.setLocation(-40, 0);
        add(mainBtn7Label);

        ImageIcon bgPicture = new ImageIcon("src/picture/background/BeginFrame背景.png");
        JLabel bgLabel = new JLabel(bgPicture);
        bgLabel.setSize(600, 600);
        bgLabel.setLocation(0, 0);
        add(bgLabel);

        this.setVisible(true);

        mainBtn1Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(1);
                mainFrame.doubleHuiQi = true;
                mainFrame.mode = 1;
                mainFrame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn2Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(2);
                mainFrame.doubleHuiQi = true;
                mainFrame.mode = 2;
                mainFrame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn3Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(3);
                mainFrame.doubleHuiQi = true;
                mainFrame.mode = 3;
                mainFrame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn4Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(4);
                mainFrame.doubleHuiQi = true;
                mainFrame.mode = 4;
                mainFrame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn5Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (isLogin){  // 已登录
                    showInformation();
                } else {  // 未登录
                    //setVisible(false);
                    new LoginFrame(customers,chooseFrame);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn6Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Collections.sort(customers, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer o1, Customer o2) {
                        if (o1.getPoints() > o2.getPoints()){
                            return -1;
                        } else if (o1.getPoints() == o2.getPoints()){
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
                String rank = "排名  昵称(分数)\n";
                for (int i = 0; i < customers.size(); i++){
                    rank += "  " + (i + 1) + "      " + customers.get(i).getName() + "(" + customers.get(i).getPoints() + ")\n";
                }
                JOptionPane.showMessageDialog(null, rank, "排行榜",JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });

        mainBtn7Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new BeginFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.HAND_CURSOR);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(DEFAULT_CURSOR);
            }
        });
    }

    public void showInformation(){
        String inf = "账号：" + customer.getName() + "\n" + "分数：" + customer.getPoints();
        JOptionPane.showMessageDialog(null, inf, "账户信息",JOptionPane.INFORMATION_MESSAGE);
    }
}
