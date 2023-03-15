package chess;

import view.GamePanel;
import view.GamePanelClone;

import java.awt.*;

public class King extends Chess {
    public King(int player, Point p) {
        super("King", player, p);
    }

    public King(int player, int px) {
        this(player, new Point(px, 1));
    }


    @Override
    public boolean isAbleStep(Point tp, GamePanel gamePanel) {
        return getStep(tp) == 1;
    }

    @Override
    public boolean isAbleStep(Point tp, GamePanelClone gamePanelClone) {
        return getStep(tp) == 1;
    }

    @Override
    public boolean isAbleEat(Point tp, GamePanel gamePanel) {
        return isAbleStep(tp, gamePanel);
    }

    @Override
    public boolean isAbleEat(Point tp, GamePanelClone gamePanelClone) {
        return isAbleStep(tp, gamePanelClone);
    }

    @Override
    public boolean canEatPawn(Point tp, GamePanel gamePanel) {
        return false;
    }

    @Override
    public boolean canChange(Point tp, GamePanel gamePanel) {
        if (check(this, tp, gamePanel)){
            return false;
        }
        if (getPlayer() == 1){  // 白方
            if (p.x == 5 && p.y == 8){
                if (tp.x == 7 && tp.y == 8 && gamePanel.getChessByP(new Point(8, 8)) != null
                && gamePanel.getChessByP(new Point(8, 8)).getName().equals("Rook")
                && gamePanel.getChessByP(new Point(6, 8)) == null
                && gamePanel.getChessByP(new Point(7, 8)) == null){  // 短易位
                    return true;
                }
                if (tp.x == 3 && tp.y == 8 && gamePanel.getChessByP(new Point(1, 8)) != null
                        && gamePanel.getChessByP(new Point(1, 8)).getName().equals("Rook")
                        && gamePanel.getChessByP(new Point(2, 8)) == null
                        && gamePanel.getChessByP(new Point(3, 8)) == null
                        && gamePanel.getChessByP(new Point(4, 8)) == null){  // 长易位
                    return true;
                }
            }
        } else if (getPlayer() == 0){  // 黑方
            if (p.x == 5 && p.y == 1){
                if (tp.x == 7 && tp.y == 1 && gamePanel.getChessByP(new Point(8, 1)) != null
                        && gamePanel.getChessByP(new Point(8, 1)).getName().equals("Rook")
                        && gamePanel.getChessByP(new Point(6, 1)) == null
                        && gamePanel.getChessByP(new Point(7, 1)) == null){  // 短易位
                    return true;
                }
                if (tp.x == 3 && tp.y == 1 && gamePanel.getChessByP(new Point(1, 1)) != null
                        && gamePanel.getChessByP(new Point(1, 1)).getName().equals("Rook")
                        && gamePanel.getChessByP(new Point(2, 1)) == null
                        && gamePanel.getChessByP(new Point(3, 1)) == null
                        && gamePanel.getChessByP(new Point(4, 1)) == null){  // 长易位
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canTransform() {
        return false;
    }
}
