package view;

import chess.Chess;
import chess.ChessFactory;

import java.awt.*;

public class GamePanelClone {
    private Chess[] chesses = new Chess[32]; // 保存所有棋子的成员变量（一个数组）
    public Chess[] getChesses() {
        return chesses;
    }
    public void setChesses(Chess[] chesses) {
        this.chesses = chesses;
    }

    private int currentPlayer = 1; // 用来记住当前阵营，默认1（白方）先走
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void moveChess(Chess selected, Point p){
        selected.setP(p); // 修改棋子坐标以实现移动
        overMyTurn();
    }

    private void overMyTurn(){
        currentPlayer = currentPlayer == 0 ? 1 : 0;
    }

    public void eatChess(Chess selected, Point p, Chess c){
        chesses[c.getIndex()] = null; // 从棋子数组中删除被吃掉的棋子
        selected.setP(p); // 修改要移动棋子的坐标
        overMyTurn();
    }

    public Chess getChessByP(Point p){
        for (Chess item : chesses){
            if (item != null && item.getP().equals(p)){
                return item;
            }
        }
        return null;
    }

    public GamePanelClone(int currentPlayer, GamePanel gamePanel) {
        this.currentPlayer = currentPlayer;
        Chess[] chesses = new Chess[32];
        int[] xs = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i <= 15; i++) {
            if (gamePanel.getChesses()[i] != null){
                Chess c = ChessFactory.create(gamePanel.getChesses()[i].getName(), 0, xs[i]);
                c.setIndex(i); // 指定棋子索引
                c.reverse();
                c.reverse();
                c.setPointX(gamePanel.getChesses()[i].getPointX());
                c.setPointY(gamePanel.getChesses()[i].getPointY());
                c.setP(new Point(gamePanel.getChesses()[i].getP().x, gamePanel.getChesses()[i].getP().y));
                chesses[i] = c; // 将棋子保存到数组中
            } else {
                chesses[i] = null;
            }
        }
        for (int i = 0; i <= 15; i++) {
            if (gamePanel.getChesses()[i + 16] != null){
                Chess c = ChessFactory.create(gamePanel.getChesses()[i + 16].getName(), 1, xs[i]);
                c.reverse();
                c.setIndex(i + 16); // 指定棋子索引
                c.setPointX(gamePanel.getChesses()[i + 16].getPointX());
                c.setPointY(gamePanel.getChesses()[i + 16].getPointY());
                c.setP(new Point(gamePanel.getChesses()[i + 16].getP().x, gamePanel.getChesses()[i + 16].getP().y));
                chesses[i + 16] = c;
            } else {
                chesses[i + 16] = null;
            }
        }
        this.setChesses(chesses);
    }
}
