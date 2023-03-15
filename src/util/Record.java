package util;

import chess.Chess;

import java.awt.*;
import java.io.Serializable;

// 棋子记录
public class Record implements Serializable {
    private Chess chess; // 操作的棋子
    private Point start; // 起点坐标
    private Point end; // 终点坐标
    private Chess ateChess; // 被吃的棋子
    private boolean isCheck; // 是否被将军

    public Record() {}

    public Record(Chess chess, Point start, Point end) {
        this.chess = chess;
        this.start = start;
        this.end = end;
    }

    public Record(Chess chess, Point start, Point end, Chess ateChess) {
        this.chess = chess;
        this.start = start;
        this.end = end;
        this.ateChess = ateChess;
    }

    public Chess getChess() {
        return chess;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Chess getAteChess() {
        return ateChess;
    }

    public void setAteChess(Chess ateChess) {
        this.ateChess = ateChess;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean check) {
        isCheck = check;
    }

    public String[] toStringArray() {
        String[] strArr = new String[6];
        strArr[0] = chess.getPlayer() + ""; // 数组第1位代表走棋方，0黑1白
        strArr[1] = chess.getName(); // 数组第2位代表移动棋子名字
        strArr[2] = start.x + ""; // 数组第3位代表起始点x坐标
        strArr[3] = start.y + ""; // 数组第4位代表起始点y坐标
        strArr[4] = end.x + ""; // 数组第5位代表终点x坐标
        strArr[5] = end.y + ""; // 数组第6位代表终点y坐标
        return strArr;
    }
}


