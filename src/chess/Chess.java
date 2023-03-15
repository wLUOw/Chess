package chess;

import view.GamePanel;
import view.GamePanelClone;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;

public abstract class Chess implements Serializable {
    // 棋子属性
    protected static final int SIZE = 68; // 棋子大小
    protected static final int MARGIN = 50; // 棋子外边距
    protected static final int SPACE = 79; // 棋子间距

    public static int getSIZE() {
        return SIZE;
    }
    public static int getMARGIN() {
        return MARGIN;
    }
    public static int getSPACE() {
        return SPACE;
    }

    private String name; // 棋子名字
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    protected int player; // 棋子阵营，0黑，1白
    public void setPlayer(int player) {
        this.player = player;
    }
    public int getPlayer() {
        return player;
    }

    protected int x, y; // 棋子绘制时的坐标位置
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    protected int pointX;
    protected int pointY;
    public int getPointX() {
        return pointX;
    }
    public void setPointX(int pointX) {
        this.pointX = pointX;
    }
    public int getPointY() {
        return pointY;
    }
    public void setPointY(int pointY) {
        this.pointY = pointY;
    }

    public int index; // 保存每个棋子的索引位置
    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }

    public Point p; // 棋子的网格坐标
    protected Point initP; // 棋子初始位置的网络坐标，不可改变
    public void setP(Point p) {
        this.p = (Point) p.clone(); // 一定要clone()！不然会出现两方棋子公用一套坐标的问题
        if (initP == null){
            initP = this.p;
        }
        CalXY();
    }
    public Point getP() {
        return p;
    }

    public Chess(String name, int player, Point p) {
        this.name = name;
        this.player = player;
        setP(p);
    }

    public Chess(String name, Point p, int player) {
        this.name = name;
        this.player = player;
        setP(p);
    }

    // 棋子绘制方法
    public void draw(Graphics g, JPanel panel){
        String path = "src" + File.separator + "picture" + File.separator + "chess" + File.separator + name + player + ".png";
        //String path = "src" + File.separator + "picture" + File.separator + "chess" + File.separator + player + name + ".png";
        Image img = Toolkit.getDefaultToolkit().getImage(path);
        g.drawImage(img, x, y, SIZE, SIZE, panel);
    }

    // 计算xy的绘制坐标
    public void CalXY(){
        x = MARGIN - SIZE / 2 + SPACE * (p.x - 1) + 25;
        y = MARGIN - SIZE / 2 + SPACE * (p.y - 1) + 25;
    }

    // 反转网格坐标
    public void reverse(){
        p.y = 9 - p.y;
        initP = p;
        CalXY();
    }

    // 根据xy坐标计算网格坐标对象
    public static Point getPointFromXY(int x, int y){
        Point p = new Point();
        p.x = (x - MARGIN + SIZE / 2 - 25) / SPACE + 1;
        p.y = (y - MARGIN + SIZE / 2 - 25) / SPACE + 1;
        if (p.x < 1 || p.x > 8 || p.y < 1 || p.y > 8){
            return null;
        }
        return p;
    }


    // 是否符合走棋规则（不包含吃子）
    public abstract boolean isAbleStep(Point tp, GamePanel gamePanel);

    // 是否符合走棋规则（不包含吃子）
    public abstract boolean isAbleStep(Point tp, GamePanelClone gamePanelClone);

    // 是否符合吃子规则
    public abstract boolean isAbleEat(Point tp, GamePanel gamePanel);

    // 是否符合吃子规则
    public abstract boolean isAbleEat(Point tp, GamePanelClone gamePanelClone);

    // 是否能吃过路兵
    public abstract boolean canEatPawn(Point tp, GamePanel gamePanel);

    // 是否能王居易位
    public abstract boolean canChange(Point tp, GamePanel gamePanel);

    // 是否能底线升变
    public abstract boolean canTransform();

    // 走完这一步之后，是否被将军
    public boolean check(Chess selectedChess, Point tp, GamePanel gamePanel){
        // 创建一个GamePanelClone
        GamePanelClone gpc = new GamePanelClone(gamePanel.getCurrentPlayer(), gamePanel);
        Chess selected = gpc.getChesses()[selectedChess.getIndex()];
        Chess tpChess = gpc.getChessByP(tp);

        if (tpChess == null){
            gpc.moveChess(selected, tp);
        } else {
            gpc.eatChess(selected, tp, tpChess);
        }

        if (selected.getPlayer() == 0){
            for (int i = 16; i <= 31; i++){
                //if (i == 20) continue;  // 不能拿白王去吃黑王
                Chess chess = gpc.getChesses()[i];
                try {
                    if (chess != null && chess.isAbleEat(gpc.getChesses()[4].p, gpc)){
                        return true;
                    }
                } catch (Exception e){
                    return false;
                }

            }
        } else if (selected.getPlayer() == 1){
            for (int i = 0; i <= 15; i++){
                //if (i == 4) continue;  // 不能拿黑王去吃白王
                Chess chess = gpc.getChesses()[i];
                try {
                    if (chess != null && chess.isAbleEat(gpc.getChesses()[20].p, gpc)){
                        return true;
                    }
                } catch (Exception e){
                    return false;
                }

            }
        }
        return false;
    }

    // 判断棋子是否走直线或斜线或“日”字
    // 沿x方向返回3，沿y方向返回2，走斜线返回1，日返回0
    // 都不是返回-1
    public int isLine(Point tp){
        if (p.y == tp.y){
            return 3;
        } else if (p.x == tp.x){
            return 2;
        } else if (Math.abs(p.y - tp.y) == Math.abs(p.x - tp.x)){
            return 1;
        } else if (Math.abs(p.x - tp.x) == 2 && Math.abs(p.y - tp.y) == 1){
            return 0;
        } else if (Math.abs(p.x - tp.x) == 1 && Math.abs(p.y - tp.y) == 2){
            return 0;
        } else {
            return -1;
        }
    }

    // 计算起点到目标点之间的步数
    public int getStep(Point tp){
        int line = isLine(tp);
        if (line == 3){
            return Math.abs(p.x - tp.x);
        } else if (line == 2){
            return Math.abs(p.y - tp.y);
        } else if (line == 1){
            return Math.abs(p.y - tp.y);
        }
        return 0;
    }

    // 计算起点和目标点之间有几个棋子，不包括起点和目标点
    public int getCount(Point tp, GamePanel gamePanel){
        int start = 0;
        int end = 0;
        int count = 0;
        Point np = new Point();
        if (isLine(tp) == 2){  // 沿y轴走
            np.x = tp.x;
            if (tp.y > p.y){  // 从上往下
                start = p.y + 1;
                end = tp.y;
            } else {  // 从下往上
                start = tp.y + 1;
                end = p.y;
            }
            for (int i = start; i < end; i++){
                np.y = i;
                if (gamePanel.getChessByP(np) != null){
                    count++;
                }
            }
        } else if (isLine(tp) == 3){  // 沿x轴走
            np.y = tp.y;
            if (tp.x > p.x){  // 从左到右
                start = p.x + 1;
                end = tp.x;
            } else {  // 从右到左
                start = tp.x + 1;
                end = p.x;
            }
            for (int i = start; i < end; i++){
                np.x = i;
                if (gamePanel.getChessByP(np) != null){
                    count++;
                }
            }
        } else if (isLine(tp) == 1){
            if (tp.x > p.x && tp.y > p.y){  // 右下
                for (int i = 1; i < Math.abs(tp.x - p.x); i++){
                    np.x = p.x + i;
                    np.y = p.y + i;
                    if (gamePanel.getChessByP(np) != null){
                        count++;
                    }
                }
            } else if (tp.x > p.x && tp.y < p.y){  // 右上
                for (int i = 1; i < Math.abs(tp.x - p.x); i++){
                    np.x = p.x + i;
                    np.y = p.y - i;
                    if (gamePanel.getChessByP(np) != null){
                        count++;
                    }
                }
            } else if (tp.x < p.x && tp.y > p.y){  // 左下
                for (int i = 1; i < Math.abs(tp.x - p.x); i++){
                    np.x = p.x - i;
                    np.y = p.y + i;
                    if (gamePanel.getChessByP(np) != null){
                        count++;
                    }
                }
            } else if (tp.x < p.x && tp.y < p.y){  // 左上
                for (int i = 1; i < Math.abs(tp.x - p.x); i++){
                    np.x = p.x - i;
                    np.y = p.y - i;
                    if (gamePanel.getChessByP(np) != null){
                        count++;
                    }
                }
            }
        }
        //System.out.println("count" + count);
        return count;
    }

    // 计算起点和目标点之间有几个棋子，不包括起点和目标点
    public int getCount(Point tp, GamePanelClone gamePanelClone){
        int start = 0;
        int end = 0;
        int count = 0;
        Point np = new Point();
        if (isLine(tp) == 2){  // 沿y轴走
            np.x = tp.x;
            if (tp.y > p.y){  // 从上往下
                start = p.y + 1;
                end = tp.y;
            } else {  // 从下往上
                start = tp.y + 1;
                end = p.y;
            }
            for (int i = start; i < end; i++){
                np.y = i;
                if (gamePanelClone.getChessByP(np) != null){
                    count++;
                }
            }
        } else if (isLine(tp) == 3){  // 沿x轴走
            np.y = tp.y;
            if (tp.x > p.x){  // 从左到右
                start = p.x + 1;
                end = tp.x;
            } else {  // 从右到左
                start = tp.x + 1;
                end = p.x;
            }
            for (int i = start; i < end; i++){
                np.x = i;
                if (gamePanelClone.getChessByP(np) != null){
                    count++;
                }
            }
        } else if (isLine(tp) == 1){
            if (tp.x > p.x && tp.y > p.y){  // 右下
                for (int i = 1; i < Math.abs(tp.x - p.x); i++){
                    np.x = p.x + i;
                    np.y = p.y + i;
                    if (gamePanelClone.getChessByP(np) != null){
                        count++;
                    }
                }
            } else if (tp.x > p.x && tp.y < p.y){  // 右上
                for (int i = 1; i < Math.abs(tp.x - p.x); i++){
                    np.x = p.x + i;
                    np.y = p.y - i;
                    if (gamePanelClone.getChessByP(np) != null){
                        count++;
                    }
                }
            } else if (tp.x < p.x && tp.y > p.y){  // 左下
                for (int i = 1; i < Math.abs(tp.x - p.x); i++){
                    np.x = p.x - i;
                    np.y = p.y + i;
                    if (gamePanelClone.getChessByP(np) != null){
                        count++;
                    }
                }
            } else if (tp.x < p.x && tp.y < p.y){  // 左上
                for (int i = 1; i < Math.abs(tp.x - p.x); i++){
                    np.x = p.x - i;
                    np.y = p.y - i;
                    if (gamePanelClone.getChessByP(np) != null){
                        count++;
                    }
                }
            }
        }
        //System.out.println("count" + count);
        return count;
    }

    @Override
    public String toString() {
        return "Chess{" +
                "name='" + name + '\'' +
                ", player=" + player +
                ", pointX=" + p.x +
                ", pointY=" + p.y +
                ", index=" + index +
                '}';
    }
}
