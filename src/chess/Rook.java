package chess;

import view.GamePanel;
import view.GamePanelClone;

import java.awt.*;

public class Rook extends Chess {
    public Rook(int player, Point p) {
        super("Rook", player, p);
    }

    public Rook(int player, int px) {
        this(player, new Point(px, 1));
    }

    @Override
    public boolean isAbleStep(Point tp, GamePanel gamePanel) {
        return (isLine(tp) == 2 || isLine(tp) == 3) && getCount(tp, gamePanel) == 0;
    }

    @Override
    public boolean isAbleStep(Point tp, GamePanelClone gamePanelClone) {
        return (isLine(tp) == 2 || isLine(tp) == 3) && getCount(tp, gamePanelClone) == 0;
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
        return false;
    }

    @Override
    public boolean canTransform() {
        return false;
    }
}
