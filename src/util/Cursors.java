package util;

import chess.Chess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Cursors {
    private int x;
    private int y;
    private int PointX; // 网格坐标x
    private int PointY; // 网格坐标y
    private BufferedImage img;

    public void setPointX(int pointX) {
        PointX = pointX;
        x = Chess.getMARGIN() - Chess.getSIZE() / 2 + Chess.getSPACE() * (pointX - 1) + 25;
    }

    public void setPointY(int pointY) {
        PointY = pointY;
        y = Chess.getMARGIN() - Chess.getSIZE() / 2 + Chess.getSPACE() * (pointY - 1) + 25;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cursors(int PointX, int PointY, String imgFilePath){
        setPointX(PointX);
        setPointY(PointY);

        try {
            img = ImageIO.read(this.getClass().getResourceAsStream(imgFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
