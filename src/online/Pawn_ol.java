package online;

import java.util.List;

public class Pawn_ol extends Chess_ol {

    public Pawn_ol(int id, int chessBoard_x, int chessBoard_y, String imgFilePath, Team team) {
        super(id, chessBoard_x, chessBoard_y, imgFilePath, team);
    }

    @Override
    protected boolean step(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList, List<Chess_ol> blackChessList) {
        // 检查目标点是否存在己方棋子
        if (targetPointExistTeamChess(chessBoard_x, chessBoard_y, whiteChessList, blackChessList)) {
            return false;
        }
        System.out.println("111");

        if (attack(chessBoard_x, chessBoard_y, whiteChessList, blackChessList)){
            return true;
        }
        System.out.println("222");

        if (team == Team.White){
            if (getChessBoard_y() != 7){  // 不是第一步走
                if (chessBoard_x - getChessBoard_x() == 0 && chessBoard_y - getChessBoard_y() == -1){
                    for (Chess_ol chess : blackChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                            System.out.println("w2 11");
                            return false;
                        }
                    }
                    for (Chess_ol chess : whiteChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                            System.out.println("w2 12");
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            } else {
                if (chessBoard_x - getChessBoard_x() == 0 && chessBoard_y - getChessBoard_y() == -1){
                    for (Chess_ol chess : blackChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                            System.out.println("w1 11");
                            return false;
                        }
                    }
                    for (Chess_ol chess : whiteChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                            System.out.println("w1 12");
                            return false;
                        }
                    }
                    return true;
                }
                if (chessBoard_x - getChessBoard_x() == 0 && chessBoard_y - getChessBoard_y() == -2){
                    for (Chess_ol chess : blackChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == 2){
                            System.out.println("w1 21");
                            return false;
                        }
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                            System.out.println("w1 21'");
                            return false;
                        }
                    }
                    for (Chess_ol chess : whiteChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == 2){
                            System.out.println("w1 22");
                            return false;
                        }
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                            System.out.println("w1 22'");
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        } else if (team == Team.BLACK){
            if (getChessBoard_y() != 2){  // 不是第一步走
                if (chessBoard_x - getChessBoard_x() == 0 && chessBoard_y - getChessBoard_y() == 1){
                    for (Chess_ol chess : whiteChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                            return false;
                        }
                    }
                    for (Chess_ol chess : blackChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                            return false;
                        }
                    }
                    System.out.println("true");
                    return true;
                }
                return false;
            } else {
                if (chessBoard_x - getChessBoard_x() == 0 && chessBoard_y - getChessBoard_y() == 1){
                    for (Chess_ol chess : whiteChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                            return false;
                        }
                    }
                    for (Chess_ol chess : blackChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                            return false;
                        }
                    }
                    return true;
                }
                if (chessBoard_x - getChessBoard_x() == 0 && chessBoard_y - getChessBoard_y() == 2){
                    for (Chess_ol chess : whiteChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == -2){
                            return false;
                        }
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                            return false;
                        }
                    }
                    for (Chess_ol chess : blackChessList){
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == -2){
                            return false;
                        }
                        if (getChessBoard_x() - chess.getChessBoard_x() == 0 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        }

        return false;
    }

    @Override
    protected boolean attack(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList, List<Chess_ol> blackChessList) {
        // 检查目标点是否存在己方棋子
        if (targetPointExistTeamChess(chessBoard_x, chessBoard_y, whiteChessList, blackChessList)) {
            return false;
        }

        if (team == Team.White){
            if (chessBoard_x - getChessBoard_x() == 1 && chessBoard_y - getChessBoard_y() == -1){
                for (Chess_ol chess : blackChessList){
                    if (getChessBoard_x() - chess.getChessBoard_x() == -1 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                        return true;
                    }
                }
                for (Chess_ol chess : whiteChessList){
                    if (getChessBoard_x() - chess.getChessBoard_x() == -1 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                        return true;
                    }
                }
            }
            if (chessBoard_x - getChessBoard_x() == -1 && chessBoard_y - getChessBoard_y() == -1){
                for (Chess_ol chess : blackChessList){
                    if (getChessBoard_x() - chess.getChessBoard_x() == 1 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                        return true;
                    }
                }
                for (Chess_ol chess : whiteChessList){
                    if (getChessBoard_x() - chess.getChessBoard_x() == 1 && getChessBoard_y() - chess.getChessBoard_y() == 1){
                        return true;
                    }
                }
            }

        } else if(team == Team.BLACK) {
            if (chessBoard_x - getChessBoard_x() == 1 && chessBoard_y - getChessBoard_y() == 1){
                for (Chess_ol chess : whiteChessList){
                    if (getChessBoard_x() - chess.getChessBoard_x() == -1 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                        return true;
                    }
                }
                for (Chess_ol chess : blackChessList){
                    if (getChessBoard_x() - chess.getChessBoard_x() == -1 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                        return true;
                    }
                }
            }
            if (chessBoard_x - getChessBoard_x() == -1 && chessBoard_y - getChessBoard_y() == 1){
                for (Chess_ol chess : whiteChessList){
                    if (getChessBoard_x() - chess.getChessBoard_x() == 1 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                        return true;
                    }
                }
                for (Chess_ol chess : blackChessList){
                    if (getChessBoard_x() - chess.getChessBoard_x() == 1 && getChessBoard_y() - chess.getChessBoard_y() == -1){
                        return true;
                    }
                }
            }

        }
        return false;
    }


}
