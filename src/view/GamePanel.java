package view;

import chess.*;
import util.Cursors;
import util.Record;
import util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static chess.Chess.*;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener, Cloneable {

    private Chess[] chesses = new Chess[32]; // 保存所有棋子的成员变量（一个数组）
    public Chess[] getChesses() {
        return chesses;
    }
    public void setChesses(Chess[] chesses) {
        this.chesses = chesses;
        repaint();
    }

    private Chess selectedChess; // 用一个成员变量储存当前选中的棋子（用来区分第一次选择和重新选择）
    public Chess getSelectedChess() {
        return selectedChess;
    }
    public void setSelectedChess(Chess selectedChess) {
        this.selectedChess = selectedChess;
    }

    private int currentPlayer = 1; // 用来记住当前阵营，默认1（白方）先走
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private LinkedList<Record> StepList = new LinkedList<>(); // 用集合记录每一步以实现悔棋功能
    public LinkedList<Record> getStepList() {
        return StepList;
    }
    public void setStepList(LinkedList<Record> stepList) {
        StepList = stepList;
    }

    private JLabel hintLabel; // 提示的label
    public JLabel getHintLabel() {
        return hintLabel;
    }
    public void setHintLabel(JLabel hintLabel) {
        this.hintLabel = hintLabel;
    }

    private JLabel hintTimeLabel = new JLabel("时间：50");
    public JLabel getHintTimeLabel() {
        return hintTimeLabel;
    }
    public void setHintTimeLabel(JLabel hintTimeLabel) {
        this.hintTimeLabel = hintTimeLabel;
    }

    private static Cursors mouseCursor; // 鼠标光标
    public static Cursors getMouseCursor() {
        return mouseCursor;
    }

    private static Cursors chooseCursor; // 已选择光标
    public static Cursors getChooseCursor() {
        return chooseCursor;
    }

    private static Cursors nextCursor; // 下一步光标
    public static Cursors getNextCursor() {
        return nextCursor;
    }

    private int mode; // 模式：0 单机玩家   1 难度一（白）   2 难度一（黑）   3 难度二（白）   4 难度二（黑）
    public int getMode() {
        return mode;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }

    private Timer timer = new Timer(this);

    private String qipanImage = "qipan-格兰芬多.png";
    public String getQipanImage() {
        return qipanImage;
    }
    public void setQipanImage(String qipanImage) {
        this.qipanImage = qipanImage;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        String bgPath = "src" + File.separator + "picture" + File.separator + "chessboard" + File.separator + qipanImage; // 准备背景图片路径
        Image bgImg = Toolkit.getDefaultToolkit().getImage(bgPath); // 获取Toolkit实例，再获取图片
        g.drawImage(bgImg,0,0,704,704,this); // 将图片绘制到面板上

        drawChesses(g);

        drawCursor(g);

        try {
            drawNextStep(g);
        } catch (Exception e){}

    }

    // 创建棋子
    private void createChesses(){
        String[] names = {"Rook","Knight","Bishop","Queen","King","Bishop","Knight","Rook","Pawn",
                "Pawn","Pawn","Pawn","Pawn","Pawn","Pawn","Pawn"};
        int[] xs = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < names.length; i++){
            Chess c = ChessFactory.create(names[i], 0, xs[i]);
            c.setIndex(i); // 指定棋子索引
            c.reverse();
            c.reverse();
            chesses[c.getIndex()] = c; // 将棋子保存到数组中
        }
        for (int i = 0; i < names.length; i++){
            Chess c = ChessFactory.create(names[i], 1, xs[i]);
            c.reverse();
            c.setIndex(i + 16); // 指定棋子索引
            chesses[c.getIndex()] = c;
        }
    }

    // 绘制棋子
    private void drawChesses(Graphics g){
        for (Chess item : chesses){
            if (item != null){
                item.draw(g,this);
            }
        }
    }

    // 绘制选择光标
    public void drawCursor(Graphics g) {
        g.drawImage(mouseCursor.getImg(), mouseCursor.getX(), mouseCursor.getY(), Chess.getSIZE(), Chess.getSIZE(), null);
        g.drawImage(chooseCursor.getImg(), chooseCursor.getX(), chooseCursor.getY(), Chess.getSIZE(),Chess.getSIZE(),null);
    }

    // 构造方法
    public GamePanel(int mode){
        setMode(mode);
        createChesses();

        mouseCursor = new Cursors(-2,-2,"/picture/cursor/cursor-yellow.png");
        chooseCursor = new Cursors(-2,-2,"/picture/cursor/cursor-red.png");
        nextCursor = new Cursors(-2,-2,"/picture/cursor/cursor-blue.png");

        addMouseListener(this);

        addMouseMotionListener(this);

        if (mode == 0){
            timer.start();
        }
    }

    // 结束当前回合，切换阵营
    public void overMyTurn(){
        currentPlayer = currentPlayer == 0 ? 1 : 0;
        selectedChess = null;
        hintLabel.setText(currentPlayer == 0 ? "黑方走棋" : "白方走棋");
    }

    // 根据网格坐标对象p查找棋子
    public Chess getChessByP(Point p){
        for (Chess item : chesses){
            if (item != null && item.getP().equals(p)){
                return item;
            }
        }
        return null;
    }

    // 实现悔棋功能
    public void regretOneStep(){
        Record record = StepList.pollLast(); // 将最后一个数据弹出
        // 将操作棋子的坐标修改回去
        record.getChess().setP(record.getStart());
        chesses[record.getChess().getIndex()] = record.getChess();
        if (record.getAteChess() != null){
            chesses[record.getAteChess().getIndex()] = record.getAteChess();
        }
        currentPlayer = record.getChess().getPlayer(); // 修改当前走棋阵营
        selectedChess = null;
        chooseCursor.setPointX(-2);
        chooseCursor.setPointY(-2);
        // 修改提示label
        hintLabel.setText(currentPlayer == 0 ? "黑方走棋" : "白方走棋");
        repaint(); // 刷新棋盘
    }

    @Override
    public void mouseClicked(MouseEvent e) {

            //super.mouseClicked(e);
            System.out.println("坐标 x=" + e.getX() + " y=" + e.getY());
            Point p = Chess.getPointFromXY(e.getX(),e.getY());
            System.out.println("网格坐标 x=" + p.x + " y=" + p.y);

            if (selectedChess == null){
                selectedChess = getChessByP(p); // 第一次选择
                chooseCursor.setPointX(p.x);
                chooseCursor.setPointY(p.y);
                if (selectedChess != null && selectedChess.getPlayer() != currentPlayer){
                    selectedChess = null; // 必须选择的是己方棋子，否则选择无效
                }

            } else {  // 重新选择，移动，吃子
                Chess c = getChessByP(p);

                if (c != null) {  // 第n次点击的地方有棋子，重新选择，吃子

                    if (c.getPlayer() == selectedChess.getPlayer()) {  // 重新选择
                        // 点击的是己方棋子，重新选择
                        System.out.println("重新选择");
                        selectedChess = c;
                        chooseCursor.setPointX(c.getP().x);
                        chooseCursor.setPointY(c.getP().y);

                    } else {
                        // 吃子
                        System.out.println("吃子");
                        if (selectedChess.isAbleEat(p, GamePanel.this) && !selectedChess.check(selectedChess, p,this)) {
                            eatChess(selectedChess, p, c);
                            repaint();
                            checkAlert();
                            gameOver();

                            robotGo();// 人机行动
                        }
                    }

                } else {  // 第n次点击的地方没有棋子，点的是空白的地方，移动
                    System.out.println("移动");

                    // 吃过路兵
                    if (selectedChess.canEatPawn(p, this)){
                        if (selectedChess.getPlayer() == 0){
                            Chess pawn = getChessByP(new Point(p.x, 5));
                            eatChess(selectedChess, p, pawn);
                        } else {
                            Chess pawn = getChessByP(new Point(p.x, 4));
                            eatChess(selectedChess, p, pawn);
                        }
                        repaint();
                        checkAlert();
                        gameOver();

                        robotGo();// 人机行动
                    }

                    // 王车易位
                    if (selectedChess.canChange(p, this)){
                        moveChess(selectedChess, p);
                        if (p.x == 7){
                            moveChess(getChessByP(new Point(8,p.y)),new Point(6,p.y));
                        } else if (p.x == 3){
                            moveChess(getChessByP(new Point(1,p.y)),new Point(4,p.y));
                        }
                        overMyTurn();
                        repaint();
                        checkAlert();
                        gameOver();

                        robotGo();// 人机行动
                    }

                    // 判断是否可以移动
                    System.out.println(selectedChess.check(selectedChess, p,this));
                    if (selectedChess.isAbleStep(p, GamePanel.this) && !selectedChess.check(selectedChess, p,this)){
                        moveChess(selectedChess, p);
                        repaint();
                        checkAlert();
                        gameOver();

                        robotGo();// 人机行动
                    }


                }
            }
            repaint(); // 刷新棋盘,即重新执行paint方法

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int PointX = (e.getX() - getMARGIN() + getSIZE() / 2 - 25) / getSPACE() + 1;
        int PointY = (e.getY() - getMARGIN() + getSIZE() / 2 - 25) / getSPACE() + 1;

        PointX = PointX > 8 ? 8 : PointX;
        PointY = PointY > 8 ? 8 : PointY;

        mouseCursor.setPointX(PointX);
        mouseCursor.setPointY(PointY);

        repaint();
    }

    // 吃子，selected是当前选中棋子，p是目标点，c是在目标点位置的棋子
    public void eatChess(Chess selected, Point p, Chess c){

        Record record = new Record();
        record.setChess(selected);
        record.setStart(selected.getP());
        record.setEnd(p);
        record.setAteChess(c);
        if (selected.getPlayer() == 0){
            record.setIsCheck(isBlackCheck(4));
        } else {
            record.setIsCheck(isWhiteCheck(20));
        }
        StepList.add(record);
        chesses[c.getIndex()] = null; // 从棋子数组中删除被吃掉的棋子
        selected.setP(p); // 修改要移动棋子的坐标
        chooseCursor.setPointX(p.x);
        chooseCursor.setPointY(p.y);

        // 兵升变
        if (selected.canTransform()){
            pawnTransform(selected);
        }
        repaint();

        overMyTurn();
    }

    // 移动棋子，selected是当前选中棋子，p是目标点
    public void moveChess(Chess selected, Point p){

        Record record = new Record();
        record.setChess(selected);
        record.setStart(selected.getP());
        record.setEnd(p);
        if (selected.getPlayer() == 0){
            record.setIsCheck(isBlackCheck(4));
        } else {
            record.setIsCheck(isWhiteCheck(20));
        }
        StepList.add(record);
        selected.setP(p); // 修改棋子坐标以实现移动
        chooseCursor.setPointX(p.x);
        chooseCursor.setPointY(p.y);

        // 兵升变
        if (selected.canTransform()){
            pawnTransform(selected);
        }

        overMyTurn();
    }

    public void resetGame(){
        selectedChess = null;
        currentPlayer = 1;
        StepList.clear();
        hintLabel.setText("白方走棋");
        chooseCursor.setPointX(-2);
        chooseCursor.setPointY(-2);
        createChesses();
        repaint();
    }

    public void save(String fileName){
        String fileAllName = fileName + ".txt";
        String fileLocation="savings\\"+fileAllName;
        File file = new File(fileLocation);

        try {
            if(file.exists()){     // 若文档存在，询问是否覆盖
                int n = JOptionPane.showConfirmDialog(this, "是否覆盖?", "取消", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    file.delete();
                }
            }

            // 创建文档
            FileWriter fileWritter = new FileWriter(fileLocation,true);
            for (int i = 0; i < StepList.size(); i++){
                for (int j = 0; j < 6; j++){
                    fileWritter.write(StepList.get(i).toStringArray()[j]);
                    fileWritter.write(" ");
                }
                fileWritter.write("\n");
            }

            fileWritter.close();
            System.out.println("Save Done");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void load(){
        System.out.println("clicked Load Btn");

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("savings"));
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();

        if (!file.getName().endsWith(".txt")){
            JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误编码：104", JOptionPane.ERROR_MESSAGE);
            System.out.println("检测到非法修改存档！重新开始游戏");
            System.out.println("后缀错误");
            resetGame();
            return;
        }

        try {
            String temp;
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
            ArrayList<String> readList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(read);

            while((temp = reader.readLine()) != null && !"".equals(temp)){
                readList.add(temp);
                System.out.println(temp);
            }

            // 检查是否白棋开始，黑白交替
            if (!readList.get(0).startsWith("1")){
                JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误编码：102", JOptionPane.ERROR_MESSAGE);
                System.out.println("检测到非法修改存档！重新开始游戏");
                System.out.println("不是白棋开始");
                resetGame();
                return;
            }
            for (int i = 0; i < readList.size() - 1; i++){
                if (Math.abs(Integer.parseInt(readList.get(i).substring(0,1)) - Integer.parseInt(readList.get(i + 1).substring(0,1))) != 1){
                    JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误编码：102", JOptionPane.ERROR_MESSAGE);
                    System.out.println("检测到非法修改存档！重新开始游戏");
                    System.out.println("不是黑白交替");
                    resetGame();
                    return;
                }
            }

            resetGame();
            for (int i = 0; i < readList.size(); i++){
                String[] strArr = readList.get(i).split(" ");
                // 检查棋子名字
                if (!strArr[1].equals("Bishop") && !strArr[1].equals("King") && !strArr[1].equals("Knight") &&
                        !strArr[1].equals("Pawn") && !strArr[1].equals("Queen") && !strArr[1].equals("Rook")){
                    JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误编码：102", JOptionPane.ERROR_MESSAGE);
                    System.out.println("检测到非法修改存档！重新开始游戏");
                    System.out.println("棋子名字不正确");
                    resetGame();
                    return;
                }

                Chess selected = getChessByP(new Point(Integer.parseInt(strArr[2]),Integer.parseInt(strArr[3])));
                Point tp = new Point(Integer.parseInt(strArr[4]),Integer.parseInt(strArr[5]));
                Chess tpChess = getChessByP(tp);
                if (tpChess == null && !selected.isAbleStep(tp,this)){
                    JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
                    System.out.println("检测到非法修改存档！重新开始游戏");
                    System.out.println("储存的步骤不符合移动规则");
                    resetGame();
                    return;
                } else if (tpChess != null && !selected.isAbleEat(tp,this)){
                    JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
                    System.out.println("检测到非法修改存档！重新开始游戏");
                    System.out.println("储存的步骤不符合移动规则");
                    resetGame();
                    return;
                } else {
                    if (tpChess == null){  // 移动，selected是当前选中棋子，tp是目标点
                        moveChess(selected,tp);
                        repaint();
                        gameOver();
                    } else {  // 吃子
                        eatChess(selected,tp,tpChess);
                        gameOver();
                    }
                }
            }
        } catch (Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "检测到非法修改存档\n已重新开始", "错误", JOptionPane.ERROR_MESSAGE);
            System.out.println("检测到非法修改存档！重新开始游戏");
            System.out.println("无法读取存档");
            resetGame();
        }
    }

    public void robot1(){
        Chess c0 = null;
        Random random = new Random();
        ArrayList<Point> points = new ArrayList<>();
        do {
            // 随机选出一个棋子
            do {
                int index = (mode - 1) * 16 + random.nextInt(16);
                if (chesses[index] != null) {
                    c0 = chesses[index];
                }
            } while (c0 == null);

            for (int x = 1; x <= 8; x++) {
                for (int y = 1; y <= 8; y++) {
                    Point p = new Point(x, y);
                    if (getChessByP(p) == null && c0.isAbleStep(p, this) && !c0.check(c0, p, this)) {
                        points.add(p);
                    }
                    if (getChessByP(new Point(x, y)) != null && getChessByP(new Point(x, y)).getPlayer() != currentPlayer
                            && c0.isAbleEat(p, this) && !c0.check(c0, p, this)){
                        points.add(p);
                    }
                }
            }
        } while (points.size() == 0);
        Point tp = points.get(random.nextInt(points.size()));
        Chess tpChess = getChessByP(tp);
        if (tpChess == null) {  // 移动
            moveChess(c0, tp);
            repaint();
        } else {
            eatChess(c0, tp, tpChess);
            repaint();
        }
    }

    public void robot2(){
        Random random = new Random();
        ArrayList<Point> points = new ArrayList<>();
        if (mode == 3){
            int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            for(int i = 0; i < 16; i++){
                int p = random.nextInt(i + 1);
                int tmp = arr[i];
                arr[i] = arr[p];
                arr[p] = tmp;
            }
            for (int i = 0; i < 16; i++) {
                Chess c0 = chesses[arr[i]];
                //System.out.println("c0 ----> " + c0.getName());
                for (int j = 16; j < 32; j++){
                    try {
                        if (c0 != null && c0.isAbleEat(chesses[j].p, this) && !c0.check(c0, chesses[j].p, this)){
                            eatChess(c0, chesses[j].p, chesses[j]);
                            repaint();
                            return;
                        }
                    } catch (Exception e){}
                }
            }


            for (int i = 0; i < 16; i++) {
                Chess c0 = chesses[i];
                if (isBlackCheck(i)){
                    for (int x = 1; x <= 8; x++) {
                        for (int y = 1; y <= 8; y++) {
                            Point p = new Point(x, y);
                            if (getChessByP(p) == null && c0.isAbleStep(p, this) && !c0.check(c0, p, this)) {
                                points.add(p);
                            }
                            if (getChessByP(new Point(x, y)) != null && getChessByP(new Point(x, y)).getPlayer() != currentPlayer
                                    && c0.isAbleEat(p, this) && !c0.check(c0, p, this)){
                                points.add(p);
                            }
                        }
                    }
                    if (points.size() == 0){
                        continue;
                    }
                    Point tp = points.get(random.nextInt(points.size()));
                    Chess tpChess = getChessByP(tp);
                    if (tpChess == null) {  // 移动
                        moveChess(c0, tp);
                        repaint();
                        return;
                    } else {
                        eatChess(c0, tp, tpChess);
                        repaint();
                        return;
                    }
                }
            }
        } else if (mode == 4){
            int[] arr = {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
            for(int i = 0; i < 16; i++){
                int p = random.nextInt(i + 1);
                int tmp = arr[i];
                arr[i] = arr[p];
                arr[p] = tmp;
            }
            for (int i = 0; i < 16; i++) {
                Chess c0 = chesses[arr[i]];
                for (int j = 0; j < 16; j++){
                    try {
                        if (c0 != null && c0.isAbleEat(chesses[j].p, this) && !c0.check(c0, chesses[j].p, this)){
                            eatChess(c0, chesses[j].p, chesses[j]);
                            repaint();
                            return;
                        }
                    } catch (Exception e){}
                }
            }
            for (int i = 16; i < 32; i++) {
                Chess c0 = chesses[i];
                if (isBlackCheck(i)){
                    for (int x = 1; x <= 8; x++) {
                        for (int y = 1; y <= 8; y++) {
                            Point p = new Point(x, y);
                            if (getChessByP(p) == null && c0.isAbleStep(p, this) && !c0.check(c0, p, this)) {
                                points.add(p);
                            }
                            if (getChessByP(new Point(x, y)) != null && getChessByP(new Point(x, y)).getPlayer() != currentPlayer
                                    && c0.isAbleEat(p, this) && !c0.check(c0, p, this)){
                                points.add(p);
                            }
                        }
                    }
                    if (points.size() == 0){
                        continue;
                    }
                    Point tp = points.get(random.nextInt(points.size()));
                    Chess tpChess = getChessByP(tp);
                    if (tpChess == null) {  // 移动
                        moveChess(c0, tp);
                        repaint();
                        return;
                    } else {
                        eatChess(c0, tp, tpChess);
                        repaint();
                        return;
                    }
                }
            }
        }

        Chess c0 = null;
        do {
            // 随机选出一个棋子
            do {
                int index = (mode - 3) * 16 + random.nextInt(16);
                if (chesses[index] != null) {
                    c0 = chesses[index];
                }
            } while (c0 == null);

            for (int x = 1; x <= 8; x++) {
                for (int y = 1; y <= 8; y++) {
                    Point p = new Point(x, y);
                    if (getChessByP(p) == null && c0.isAbleStep(p, this) && !c0.check(c0, p, this)) {
                        points.add(p);
                    }
                    if (getChessByP(new Point(x, y)) != null && getChessByP(new Point(x, y)).getPlayer() != currentPlayer
                            && c0.isAbleEat(p, this) && !c0.check(c0, p, this)){
                        points.add(p);
                    }
                }
            }
        } while (points.size() == 0);
        Point tp = points.get(random.nextInt(points.size()));
        Chess tpChess = getChessByP(tp);
        if (tpChess == null) {  // 移动
            moveChess(c0, tp);
            repaint();
        } else {
            eatChess(c0, tp, tpChess);
            repaint();
        }
    }

    // 判断是否游戏结束，执行游戏结束的动作，比如弹窗等
    public void gameOver(){
        if (whiteCheckmate()){
            JOptionPane.showMessageDialog(null, "本局结束，黑方胜利！", "",JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        } else if (blackCheckmate()){
            JOptionPane.showMessageDialog(null, "本局结束，白方胜利！", "",JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        } else if (dawn1() || dawn2() || dawn3()){
            JOptionPane.showMessageDialog(null, "本局结束，和棋", "",JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        }
    }

    // 判断白王是否被将死
    public boolean whiteCheckmate(){
        if (!isWhiteCheck(20)){
            return false;
        }
        for (int i = 16; i <= 31; i++){
            if (chesses[i] != null){
                Chess c = chesses[i];
                for (int x = 1; x <= 8; x++){
                    for (int y = 1; y <= 8; y++){
                        Point tp = new Point(x,y);
                        Chess tpChess = getChessByP(tp);
                        if (tpChess == null){
                            if (c.isAbleStep(tp,this) && !c.check(c,tp,this)){
                                return false;
                            }
                        } else if (tpChess.getPlayer() == 0){
                            if (c.isAbleEat(tp,this) && !c.check(c,tp,this)){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    // 判断黑王是否被将死
    public boolean blackCheckmate(){
        if (!isBlackCheck(4)){
            return false;
        }
        for (int i = 0; i <= 15; i++){
            if (chesses[i] != null){
                Chess c = chesses[i];
                for (int x = 1; x <= 8; x++){
                    for (int y = 1; y <= 8; y++){
                        Point tp = new Point(x,y);
                        Chess tpChess = getChessByP(tp);
                        if (tpChess == null){
                            if (c.isAbleStep(tp,this) && !c.check(c,tp,this)){
                                return false;
                            }
                        } else if (tpChess.getPlayer() == 1){
                            if (c.isAbleEat(tp,this) && !c.check(c,tp,this)){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    // 判断是否长将和棋
    public boolean dawn1(){
        try {
            if (getStepList().get(getStepList().size() - 1).getIsCheck() && getStepList().get(getStepList().size() - 3).getIsCheck()
                    && getStepList().get(getStepList().size() - 5).getIsCheck() && getStepList().get(getStepList().size() - 7).getIsCheck()
                    && getStepList().get(getStepList().size() - 9).getIsCheck()){  // 长将和棋
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }

    // 判断是否三次重复和棋
    public boolean dawn2(){
        if (getStepList().size() < 10){
            return false;
        }
        boolean b1 = compareRecord(getStepList().get(getStepList().size() - 1), getStepList().get(getStepList().size() - 5));
        boolean b2 = compareRecord(getStepList().get(getStepList().size() - 2), getStepList().get(getStepList().size() - 6));
        boolean b3 = compareRecord(getStepList().get(getStepList().size() - 3), getStepList().get(getStepList().size() - 7));
        boolean b4 = compareRecord(getStepList().get(getStepList().size() - 4), getStepList().get(getStepList().size() - 8));

        return b1 && b2 && b3 && b4;
    }

    // 比较两个Record是否一样，一样返回true
    public boolean compareRecord(Record r1, Record r2){
        if (r1.getAteChess() != null || r2.getAteChess() != null){
            return false;
        }
        String[] arr1 = r1.toStringArray();
        String[] arr2 = r2.toStringArray();
        for (int i = 0; i < 6; i++){
            if (!arr1[i].equals(arr2[i])){
                return false;
            }
        }
        return true;
    }

    // 判断是否无子可动和棋
    public boolean dawn3(){
        int cnt = 0;
        for (int t = 0; t < 32; t++){
            Chess chess = chesses[t];
            if (chess != null && chess.getPlayer() == currentPlayer){
                for (int i = 1; i <= 8; i++){
                    for (int j = 1; j <= 8; j++){
                        Point tp = new Point(i, j);
                        Chess tpChess = getChessByP(tp);
                        if (tpChess == null){
                            if (chess.canEatPawn(tp,this) || chess.canChange(tp,this)){
                                cnt++;
                            }
                            if (chess.isAbleStep(tp,this) && !chess.check(chess, tp, this)){
                                cnt++;
                            }
                        } else if (tpChess.getPlayer() != currentPlayer){
                            if (chess.isAbleEat(tp,this) && !chess.check(chess, tp, this)){
                                cnt++;
                            }
                        }
                    }
                }
            }
        }
        return cnt == 0;
    }

    // 小兵升变
    public void pawnTransform(Chess chess){
        Object[] options = {"Knight", "Bishop", "Rook", "Queen"};
        String s = (String) JOptionPane.showInputDialog(null, "选择想要升变的棋子种类",
                "Transform", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (s.equals("Knight")){
            Chess newChess = new Knight(chess.getPlayer(),new Point(chess.getP().x,chess.getP().y));
            chesses[chess.getIndex()] = newChess;
        } else if (s.equals("Bishop")){
            Chess newChess = new Bishop(chess.getPlayer(),new Point(chess.getP().x,chess.getP().y));
            chesses[chess.getIndex()] = newChess;
        } else if (s.equals("Rook")){
            Chess newChess = new Rook(chess.getPlayer(),new Point(chess.getP().x,chess.getP().y));
            chesses[chess.getIndex()] = newChess;
        } else if (s.equals("Queen")){
            Chess newChess = new Queen(chess.getPlayer(),new Point(chess.getP().x,chess.getP().y));
            chesses[chess.getIndex()] = newChess;
        }
        repaint();
    }

    // 画下一步落子点
    public void drawNextStep(Graphics g){
        if (selectedChess != null && selectedChess.getPlayer() == currentPlayer){
            for (int i = 1; i <= 8; i++){
                for (int j = 1; j <= 8; j++){
                    Point tp = new Point(i, j);
                    Chess tpChess = getChessByP(tp);
                    if (tpChess == null){
                        if (selectedChess.canEatPawn(tp,this) || selectedChess.canChange(tp,this)){
                            int x = getMARGIN() - getSIZE() / 2 + getSPACE() * (i - 1) + 25;
                            int y = getMARGIN() - getSIZE() / 2 + getSPACE() * (j - 1) + 25;
                            g.drawImage(nextCursor.getImg(), x, y, Chess.getSIZE(), Chess.getSIZE(), null);
                        }
                        if (selectedChess.isAbleStep(tp,this) && !selectedChess.check(selectedChess, tp, this)){
                            int x = getMARGIN() - getSIZE() / 2 + getSPACE() * (i - 1) + 25;
                            int y = getMARGIN() - getSIZE() / 2 + getSPACE() * (j - 1) + 25;
                            g.drawImage(nextCursor.getImg(), x, y, Chess.getSIZE(), Chess.getSIZE(), null);
                        }
                    } else if (tpChess.getPlayer() != currentPlayer){
                        if (selectedChess.isAbleEat(tp,this) && !selectedChess.check(selectedChess, tp, this)){
                            int x = getMARGIN() - getSIZE() / 2 + getSPACE() * (i - 1) + 25;
                            int y = getMARGIN() - getSIZE() / 2 + getSPACE() * (j - 1) + 25;
                            g.drawImage(nextCursor.getImg(), x, y, Chess.getSIZE(), Chess.getSIZE(), null);
                        }
                    }
                }
            }
        }
    }

    // 将军提示
    public void checkAlert(){
        if (currentPlayer == 0){
            for (int i = 16; i <= 31; i++){
                Chess chess = chesses[i];
                if (chess != null && chess.isAbleEat(chesses[4].p, this)){
                    JOptionPane.showMessageDialog(null, "黑方被将军！", "",JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }
        } else if (currentPlayer == 1){
            for (int i = 0; i <= 15; i++){
                Chess chess = chesses[i];
                if (chess != null && chess.isAbleEat(chesses[20].p, this)){
                    JOptionPane.showMessageDialog(null, "白方被将军！", "",JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }
        }
    }

    // 人机行动
    public void robotGo(){
        if (mode == 1 || mode == 2) {
            robot1();
            checkAlert();
            gameOver();
        } else if (mode == 3 || mode == 4){
            robot2();
            checkAlert();
            gameOver();
        }
    }

    // 判断当前局面白方某棋子是否可以被吃
    public boolean isWhiteCheck(int index){
        for (int i = 0; i <= 15; i++){
            Chess chess = chesses[i];
            try {
                if (chess != null && chess.isAbleEat(chesses[index].p, this)){
                    return true;
                }
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }

    // 判断当前局面黑方是否被将军
    public boolean isBlackCheck(int index){
        for (int i = 16; i <= 31; i++){
            Chess chess = chesses[i];
            try {
                if (chess != null && chess.isAbleEat(chesses[index].p, this)){
                    return true;
                }
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }

}