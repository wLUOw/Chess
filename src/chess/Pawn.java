package chess;

import view.GamePanel;
import view.GamePanelClone;

import java.awt.*;

public class Pawn extends Chess{
    public Pawn(int player, Point p) {
        super("Pawn", player, p);
    }

    public Pawn(int player, int px) {
        this(player, new Point(px, 2));
    }

    @Override
    public boolean isAbleStep(Point tp, GamePanel gamePanel) {
        if (getPlayer() == 0){ // 黑方
            if (p.y == 2){ // 第一步
                return tp.y > p.y && isLine(tp) == 2 && (getStep(tp) == 1 || getStep(tp) == 2) && getCount(tp, gamePanel) == 0;
            } else {
                return tp.y > p.y && isLine(tp) == 2 && getStep(tp) == 1;
            }
        } else if (getPlayer() == 1){ // 白方
            if (p.y == 7){ // 第一步
                return tp.y < p.y && isLine(tp) == 2 && (getStep(tp) == 1 || getStep(tp) == 2) && getCount(tp, gamePanel) == 0;
            } else {
                return tp.y < p.y && isLine(tp) == 2 && getStep(tp) == 1;
            }
        }
        return false;
    }

    public boolean isAbleStep(Point tp, GamePanelClone gamePanelClone) {
        if (getPlayer() == 0){ // 黑方
            if (p.y == 2){ // 第一步
                return tp.y > p.y && isLine(tp) == 2 && (getStep(tp) == 1 || getStep(tp) == 2);
            } else {
                return tp.y > p.y && isLine(tp) == 2 && getStep(tp) == 1;
            }
        } else if (getPlayer() == 1){ // 白方
            if (p.y == 7){ // 第一步
                return tp.y < p.y && isLine(tp) == 2 && (getStep(tp) == 1 || getStep(tp) == 2);
            } else {
                return tp.y < p.y && isLine(tp) == 2 && getStep(tp) == 1;
            }
        }
        return false;
    }

    @Override
    public boolean isAbleEat(Point tp, GamePanel gamePanel) {
        if (getPlayer() == 0){ // 黑方
            return tp.y > p.y && isLine(tp) == 1 && getStep(tp) == 1;
        } else if (getPlayer() == 1){ // 白方
            return tp.y < p.y && isLine(tp) == 1 && getStep(tp) == 1;
        }
        return false;
    }

    @Override
    public boolean isAbleEat(Point tp, GamePanelClone gamePanelClone) {
        if (getPlayer() == 0){ // 黑方
            return tp.y > p.y && isLine(tp) == 1 && getStep(tp) == 1;
        } else if (getPlayer() == 1){ // 白方
            return tp.y < p.y && isLine(tp) == 1 && getStep(tp) == 1;
        }
        return false;
    }

    @Override
    public boolean canEatPawn(Point tp, GamePanel gamePanel){
        if (getPlayer() == 0 && p.y == 5 && tp.y == 6 && Math.abs(tp.x - p.x) == 1){ // 黑方
            Chess c = gamePanel.getChessByP(new Point(tp.x, 5));
            if (c != null && (c.getName().equals("Pawn") && c.getPlayer() == 1)){
                return true;
            }
        } else if (getPlayer() == 1 && p.y == 4 && tp.y == 3 && Math.abs(tp.x - p.x) == 1){ // 白方
            Chess c = gamePanel.getChessByP(new Point(tp.x, 4));
            if (c != null && (c.getName().equals("Pawn") && c.getPlayer() == 0)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canChange(Point tp, GamePanel gamePanel) {
        return false;
    }

    @Override
    public boolean canTransform() {
        if (getPlayer() == 1){  // 白方
            return p.y == 1;
        } else if (getPlayer() == 0){  // 黑方
            return p.y == 8;
        }
        return false;
    }

}
