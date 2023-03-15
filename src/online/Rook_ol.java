package online;

import java.util.List;


public class Rook_ol extends Chess_ol {

    public Rook_ol(int id, int chessBoard_x, int chessBoard_y, String imgFilePath, Team team) {
        super(id, chessBoard_x, chessBoard_y, imgFilePath, team);
    }

    @Override
    protected boolean step(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList, List<Chess_ol> blackChessList) {
        // 检查目标点是否存在己方棋子
        if (targetPointExistTeamChess(chessBoard_x, chessBoard_y, whiteChessList, blackChessList)) {
            return false;
        }

        // 判断是否走直线
        if (getChessBoard_x() == chessBoard_x && getChessBoard_y() > chessBoard_y) {// 向上移动
            for (Chess_ol chess : blackChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_y() - chessBoard_y); i++){
                    if (getChessBoard_x() == chess.getChessBoard_x()
                            && getChessBoard_y() - i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            for (Chess_ol chess : whiteChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_y() - chessBoard_y); i++){
                    if (getChessBoard_x() == chess.getChessBoard_x()
                            && getChessBoard_y() - i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            return true;
        } else if (getChessBoard_x() == chessBoard_x && getChessBoard_y() < chessBoard_y) {// 向下移动
            for (Chess_ol chess : blackChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_y() - chessBoard_y); i++){
                    if (getChessBoard_x() == chess.getChessBoard_x()
                            && getChessBoard_y() + i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            for (Chess_ol chess : whiteChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_y() - chessBoard_y); i++){
                    if (getChessBoard_x() == chess.getChessBoard_x()
                            && getChessBoard_y() + i == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            return true;
        } else if (getChessBoard_x() > chessBoard_x && getChessBoard_y() == chessBoard_y){ // 向左移动
            for (Chess_ol chess : blackChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() - i == chess.getChessBoard_x()
                            && getChessBoard_y() == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            for (Chess_ol chess : whiteChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() - i == chess.getChessBoard_x()
                            && getChessBoard_y() == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            return true;
        } else if (getChessBoard_x() < chessBoard_x && getChessBoard_y() == chessBoard_y){ // 向右移动
            for (Chess_ol chess : blackChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() + i == chess.getChessBoard_x()
                            && getChessBoard_y() == chess.getChessBoard_y()) {
                        return false;
                    }
                }
            }
            for (Chess_ol chess : whiteChessList) {
                for (int i = 1; i < Math.abs(getChessBoard_x() - chessBoard_x); i++){
                    if (getChessBoard_x() + i == chess.getChessBoard_x()
                            && getChessBoard_y() == chess.getChessBoard_y()) {
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
