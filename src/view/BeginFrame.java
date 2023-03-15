package view;


import online.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BeginFrame extends JFrame {

    public BeginFrame() {
        this.setTitle("国际象棋");
        this.setLayout(null);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口可以关闭


        ImageIcon mainBtn1Picture = new ImageIcon("src/picture/button/pvp.png");
        JLabel mainBtn1Label = new JLabel(mainBtn1Picture);
        mainBtn1Label.setSize(250, 120);
        mainBtn1Label.setLocation(170, 50);
        add(mainBtn1Label);

        ImageIcon mainBtn2Picture = new ImageIcon("src/picture/button/pve.png");
        JLabel mainBtn2Label = new JLabel(mainBtn2Picture);
        mainBtn2Label.setSize(250, 120);
        mainBtn2Label.setLocation(180, 190);
        add(mainBtn2Label);

        ImageIcon mainBtn3Picture = new ImageIcon("src/picture/button/pvpOnline.png");
        JLabel mainBtn3Label = new JLabel(mainBtn3Picture);
        mainBtn3Label.setSize(250, 120);
        mainBtn3Label.setLocation(170, 350);
        add(mainBtn3Label);

        ImageIcon bgPicture = new ImageIcon("src/picture/background/BeginFrame背景.png");
        JLabel bgLabel = new JLabel(bgPicture);
        bgLabel.setSize(600, 600);
        bgLabel.setLocation(0, 0);
        add(bgLabel);


        this.setVisible(true);

        mainBtn1Label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                MainFrame mainFrame = new MainFrame(0);
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
                ChooseFrame chooseFrame = new ChooseFrame(false, null);
                chooseFrame.setVisible(true);
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
                Game.go();
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



}
