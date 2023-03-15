package chess;

import view.GamePanel;
import view.GamePanelClone;

import java.awt.*;

public class Knight extends Chess {
    public Knight(int player, Point p) {
        super("Knight", player, p);
    }

    public Knight(int player, int px) {
        this(player, new Point(px, 1));
    }

    @Override
    public boolean isAbleStep(Point tp, GamePanel gamePanel) {
        return isLine(tp) == 0;
    }

    @Override
    public boolean isAbleStep(Point tp, GamePanelClone gamePanelClone) {
        return isLine(tp) == 0;
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
