package online;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Cursor {
	// x坐标
	private int x;
	// y坐标
	private int y;
	// 棋盘横线坐标 x
	protected int chessBoard_x;
	// 棋盘纵线坐标 y
	protected int chessBoard_y;
	// 宽度
	protected int width;
	// 高度
	protected int height;
	// 图片
	private BufferedImage img;
	
	public Cursor(int chessBoard_x, int chessBoard_y, String imgFilePath) {
		setChessBoard_x(chessBoard_x);
		setChessBoard_y(chessBoard_y);
		
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream(imgFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = img.getWidth();
		height = img.getHeight();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getChessBoard_x() {
		return chessBoard_x;
	}

	public void setChessBoard_x(int chessBoard_x) {
		this.chessBoard_x = chessBoard_x;
		x = ChessBoard.BORDER_WIDTH + ChessBoard.LATTICE_WIDTH * chessBoard_x - 1;
	}

	public int getChessBoard_y() {
		return chessBoard_y;
	}

	public void setChessBoard_y(int chessBoard_y) {
		this.chessBoard_y = chessBoard_y;
		y = ChessBoard.BORDER_WIDTH + ChessBoard.LATTICE_WIDTH * chessBoard_y - 1;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImg() {
		return img;
	}
}
