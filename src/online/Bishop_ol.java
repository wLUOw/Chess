package online;

import chess.Chess;

import java.util.List;


public class Bishop_ol extends Chess_ol {

    public Bishop_ol(int id, int chessBoard_x, int chessBoard_y, String imgFilePath, Team team) {
        super(id, chessBoard_x, chessBoard_y, imgFilePath, team);
    }

    @Override
    protected boolean step(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList, List<Chess_ol> blackChessList) {
        // 检查目标点是否存在己方棋子
        if (targetPointExistTeamChess(chessBoard_x, chessBoard_y, whiteChessList, blackChessList)) {
            return false;
        }

        // 判断是否走斜线
        if (getChessBoard_x() - chessBoard_x == getChessBoard_y() - chessBoard_y
        && getChessBoard_x() - chessBoard_x < 0) {  // 右下
            for (Chess_ol chess : blackChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() + i == chess.getChessBoard_x()
                            && getChessBoard_y() + i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            for (Chess_ol chess : whiteChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() + i == chess.getChessBoard_x()
                            && getChessBoard_y() + i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            return true;
        }
        if (getChessBoard_x() - chessBoard_x == getChessBoard_y() - chessBoard_y
                && getChessBoard_x() - chessBoard_x > 0) {  // 左上
            for (Chess_ol chess : blackChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() - i == chess.getChessBoard_x()
                            && getChessBoard_y() - i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            for (Chess_ol chess : whiteChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() - i == chess.getChessBoard_x()
                            && getChessBoard_y() - i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            return true;
        }
        if (getChessBoard_x() - chessBoard_x == - getChessBoard_y() + chessBoard_y
                && getChessBoard_x() - chessBoard_x > 0) {  // 右上
            for (Chess_ol chess : blackChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() - i == chess.getChessBoard_x()
                            && getChessBoard_y() + i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            for (Chess_ol chess : whiteChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() - i == chess.getChessBoard_x()
                            && getChessBoard_y() + i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            return true;
        }
        if (getChessBoard_x() - chessBoard_x == - getChessBoard_y() + chessBoard_y
                && getChessBoard_x() - chessBoard_x < 0) {  // 左下
            for (Chess_ol chess : blackChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() + i == chess.getChessBoard_x()
                            && getChessBoard_y() - i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            for (Chess_ol chess : whiteChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() + i == chess.getChessBoard_x()
                            && getChessBoard_y() - i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean attack(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList, List<Chess_ol> blackChessList) {
        return step(chessBoard_x, chessBoard_y, whiteChessList, blackChessList);
    }


}

