package online;

import java.util.List;

public class King_ol extends Chess_ol {

    public King_ol(int id, int chessBoard_x, int chessBoard_y, String imgFilePath, Team team) {
        super(id, chessBoard_x, chessBoard_y, imgFilePath, team);
    }

    @Override
    protected boolean step(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList, List<Chess_ol> blackChessList) {
        // 检查目标点是否存在己方棋子
        if (targetPointExistTeamChess(chessBoard_x, chessBoard_y, whiteChessList, blackChessList)) {
            return false;
        }

        if (Math.abs(getChessBoard_x() - chessBoard_x) == 1 && getChessBoard_y() - chessBoard_y == 0) {
            return true;
        }
        if (getChessBoard_x() - chessBoard_x == 0 && Math.abs(getChessBoard_y() - chessBoard_y) == 1) {
            return true;
        }
        if (Math.abs(getChessBoard_x() - chessBoard_x) == 1 && Math.abs(getChessBoard_y() - chessBoard_y) == 1){
            return true;
        }
        return false;
    }

    @Override
    protected boolean attack(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList, List<Chess_ol> blackChessList) {
        // 检查目标点是否存在己方棋子
        if (targetPointExistTeamChess(chessBoard_x, chessBoard_y, whiteChessList, blackChessList)) {
            return false;
        }

        if (Math.abs(getChessBoard_x() - chessBoard_x) == 1 && getChessBoard_y() - chessBoard_y == 0) {
            return true;
        }
        if (getChessBoard_x() - chessBoard_x == 0 && Math.abs(getChessBoard_y() - chessBoard_y) == 1) {
            return true;
        }
        if (Math.abs(getChessBoard_x() - chessBoard_x) == 1 && Math.abs(getChessBoard_y() - chessBoard_y) == 1){
            return true;
        }
        return false;
    }


}
