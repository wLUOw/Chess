package view;

import chess.Chess;
import util.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {

    private GamePanel gp;
    boolean doubleHuiQi = false;
    int mode;

    public MainFrame(int mode){

        setTitle("国际象棋");
        setSize(960,740); // 设置窗口大小
        setLocationRelativeTo(null);// 设置窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口可以关闭

        setLayout(new BorderLayout()); // 设置布局管理员
        gp = new GamePanel(mode);
        gp.setSize(704,704);
        gp.setLocation(0,0);
        add(gp); // 将面板添加到窗口中

        JLabel hintLabel = new JLabel("白方走棋");
        hintLabel.setFont(new Font("", Font.BOLD, 25));
        hintLabel.setForeground(Color.BLACK);
        hintLabel.setSize(150,40);
        hintLabel.setLocation(750,50);
        add(hintLabel);
        gp.setHintLabel(hintLabel);

        if (mode == 0){
            JLabel hintTimeLabel = new JLabel("时间：50");
            hintTimeLabel.setFont(new Font("", Font.BOLD, 25));
            hintTimeLabel.setForeground(Color.RED);
            hintTimeLabel.setSize(150,40);
            hintTimeLabel.setLocation(750,100);
            add(hintTimeLabel);
            gp.setHintTimeLabel(hintTimeLabel);
        }


        JLabel btn1 = new JLabel(new ImageIcon("src\\picture\\button\\悔棋.png"));
        btn1.setSize(100, 40);
        btn1.setLocation(750, 200);
        add(btn1);

        JLabel btn2 = new JLabel(new ImageIcon("src\\picture\\button\\保存.png"));
        btn2.setSize(100, 40);
        btn2.setLocation(750, 275);
        add(btn2);

        JLabel btn3 = new JLabel(new ImageIcon("src\\picture\\button\\导入.png"));
        btn3.setSize(100, 40);
        btn3.setLocation(755, 350);
        add(btn3);

        JLabel btn4 = new JLabel(new ImageIcon("src\\picture\\button\\重新开始.png"));
        btn4.setSize(120, 40);
        btn4.setLocation(750, 430);
        add(btn4);

        JLabel btn5 = new JLabel(new ImageIcon("src\\picture\\button\\切换棋盘.png"));
        btn5.setSize(120, 40);
        btn5.setLocation(750, 500);
        add(btn5);

        JLabel btn6 = new JLabel(new ImageIcon("src\\picture\\button\\返回.png"));
        btn6.setSize(100, 40);
        btn6.setLocation(760, 575);
        add(btn6);

        ImageIcon bgPicture = new ImageIcon("src\\picture\\background\\MainFrame背景.png");
        JLabel bgLabel = new JLabel(bgPicture);
        bgLabel.setSize(260, 740);
        bgLabel.setLocation(704, -10);
        add(bgLabel);

        JLabel btn7 = new JLabel(new ImageIcon("src\\picture\\button\\切换棋盘.png"));
        btn7.setSize(200, 80);
        btn7.setLocation(750, 650);
        add(btn7);

        btn1.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gp.regretOneStep();
                if (doubleHuiQi){
                    gp.regretOneStep();
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

        btn2.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println("clicked Save Btn");
                    String fileName = JOptionPane.showInputDialog("请输入存档名" );
                    if(fileName != null){
                        gp.save(fileName);
                    }
                }catch (Exception a){
                    a.printStackTrace();
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

        btn3.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gp.load();
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

        btn4.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gp.resetGame();
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

        btn5.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Object[] options = {"格兰芬多", "赫奇帕奇", "拉文克劳", "斯莱特林"};
                String s = (String) JOptionPane.showInputDialog(null, "选择棋盘皮肤",
                        "切换皮肤", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (s.equals("格兰芬多")){
                    gp.setQipanImage("qipan-格兰芬多.png");
                } else if (s.equals("赫奇帕奇")){
                    gp.setQipanImage("qipan-赫奇帕奇.png");
                } else if (s.equals("拉文克劳")){
                    gp.setQipanImage("qipan-拉文克劳.png");
                } else if (s.equals("斯莱特林")){
                    gp.setQipanImage("qipan-斯莱特林.png");
                }
                repaint();
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

        btn6.addMouseListener(new MouseListener() {
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

        setVisible(true); // 设置窗口可见

        if (mode == 2 || mode == 4){
            Chess c0 = gp.getChessByP(new Point(4,7));
            Point tp = new Point(4,5);
            Record record0 = new Record();
            record0.setChess(c0);
            record0.setStart(c0.getP());
            record0.setEnd(tp);
            record0.setAteChess(null);
            gp.getStepList().add(record0);
            c0.setP(tp); // 修改棋子坐标以实现移动
            GamePanel.getChooseCursor().setPointX(tp.x);
            GamePanel.getChooseCursor().setPointY(tp.y);
            gp.overMyTurn();
            repaint();
        }



    }



}
