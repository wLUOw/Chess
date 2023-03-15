package online;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public abstract class Chess_ol {
	// 棋子ID
	protected int id;
	// x坐标
	protected int x;
	// y坐标
	protected int y;
	// 棋盘横线坐标 x
	protected int chessBoard_x;
	// 棋盘纵线坐标 y
	protected int chessBoard_y;
	// 宽度
	protected int width;
	// 高度
	protected int height;
	// 图片
	protected BufferedImage img;
	// 阵营
	protected Team team;
	// 步数
	public int step;

	public Chess_ol(int id, int chessBoard_x, int chessBoard_y, String imgFilePath, Team team) {
		super();

		this.id = id;

		this.x = ChessBoard.BORDER_WIDTH + ChessBoard.LATTICE_WIDTH * (chessBoard_x - 1);
		this.y = ChessBoard.BORDER_WIDTH + ChessBoard.LATTICE_WIDTH * (chessBoard_y - 1);

		this.chessBoard_x = chessBoard_x;
		this.chessBoard_y = chessBoard_y;

		try {
			// 读取图片
			img = ImageIO.read(Chess_ol.class.getResourceAsStream(imgFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = height = ChessBoard.LATTICE_WIDTH;
		this.team = team;
	}

	/**
	 * 落子
	 * 
	 * @param chessBoard_x
	 * @param chessBoard_y
	 * @param whiteChessList
	 * @param blackChessList
	 * @return 能否落子
	 */
	protected abstract boolean step(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList,
			List<Chess_ol> blackChessList);

	/**
	 * 吃子
	 * 
	 * @param chessBoard_x   目标点棋盘X坐标
	 * @param chessBoard_y   目标点棋盘Y坐标
	 * @param whiteChessList   白色棋子集合
	 * @param blackChessList 黑色棋子集合
	 * @return 能否攻击
	 */
	protected abstract boolean attack(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList,
			List<Chess_ol> blackChessList);

	/**
	 * 移动
	 * 
	 * @param chessBoard_x 移动目标棋盘X坐标
	 * @param chessBoard_y 移动目标棋盘Y坐标
	 * @return 1：能够移动 0：被将军 -1：移动不符规则
	 */
	public int move(int chessBoard_x, int chessBoard_y) {
		// 判断是否能够落子
		if (step(chessBoard_x, chessBoard_y, ChessBoard.getWhiteChessList(), ChessBoard.getBlackChessList())) {
			// 判断落子后是否处于将军
			if (!check(chessBoard_x, chessBoard_y)) {// 未被将军
				// 移动棋盘X坐标
				setChessBoard_x(chessBoard_x);
				// 移动棋盘Y坐标
				setChessBoard_y(chessBoard_y);
				step++;
				return 1;
			}
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * 将军
	 * 
	 * @param chessBoard_x 要移动的棋盘X
	 * @param chessBoard_y 要移动的棋盘Y
	 * @return 是否被将军
	 */
	protected boolean check0(int chessBoard_x, int chessBoard_y){
		return true;
	}
	protected boolean check(int chessBoard_x, int chessBoard_y) {
		// 获取镜像棋子集合
		List<Chess_ol> whiteChessList_mirror = ChessBoard.getWhiteChessList_mirror();
		List<Chess_ol> blackChessList_mirror = ChessBoard.getBlackChessList_mirror();

		// 判断将要移动的棋子是否是王
		if (getId() == 100) {// 白色王
			// 移动镜像棋子
			for (Chess_ol chess : whiteChessList_mirror) {
				if (getId() == chess.getId()) {
					chess.setChessBoard_x(chessBoard_x);
					chess.setChessBoard_y(chessBoard_y);
					break;
				}
			}

			// 遍历敌方棋子
			for (Chess_ol blackChess : blackChessList_mirror) {
				// 判断移动以后是否会被敌方棋子击杀
				if (blackChess.attack(chessBoard_x, chessBoard_y, whiteChessList_mirror, blackChessList_mirror)) {// 能击杀
					// 返回true表示被将军
					System.out.println("check");
					return true;
				}
			}
		} else if (getId() == 200) {// 黑色王
			// 移动镜像棋子
			for (Chess_ol chess : blackChessList_mirror) {
				if (getId() == chess.getId()) {
					chess.setChessBoard_x(chessBoard_x);
					chess.setChessBoard_y(chessBoard_y);
					break;
				}
			}

			// 遍历敌方棋子
			for (Chess_ol whiteChess : whiteChessList_mirror) {
				// 判断移动以后是否会被敌方棋子击杀
				if (whiteChess.attack(chessBoard_x, chessBoard_y, whiteChessList_mirror, blackChessList_mirror)) {// 能击杀
					// 返回true表示被将军
					System.out.println("check");
					return true;
				}
			}
		} else {// 其他棋子
			// 判断阵营
			if (getTeam() == Team.White) {
				Chess_ol general = null;

				// 移动镜像棋子集合中相同棋子
				for (Chess_ol chess : whiteChessList_mirror) {
					if (getId() == chess.getId()) {
						// 移动镜像中棋子
						chess.setChessBoard_x(chessBoard_x);
						chess.setChessBoard_y(chessBoard_y);

						// 判断该目标点是否有敌方棋子
						for (Chess_ol c : blackChessList_mirror) {
							if (c.getChessBoard_x() == chessBoard_x && c.getChessBoard_y() == chessBoard_y) {
								c.setChessBoard_x(-2);
								c.setChessBoard_y(-2);
							}
						}
					}
					// 判断是否是王
					if (chess.getId() == 100) {
						general = chess;
					}
				}
				// 遍历敌方棋子
				for (Chess_ol blackChess : blackChessList_mirror) {
					// 判断移动以后是否会被敌方棋子击杀
					if (blackChess.attack(general.getChessBoard_x(), general.getChessBoard_y(), whiteChessList_mirror,
							blackChessList_mirror)) {// 能击杀
						// 返回true表示被将军
						System.out.println("check");
						return true;
					}
				}
			} else if (getTeam() == Team.BLACK) {
				Chess_ol general = null;

				// 移动镜像棋子集合中相同棋子
				for (Chess_ol chess : blackChessList_mirror) {
					if (getId() == chess.getId()) {
						chess.setChessBoard_x(chessBoard_x);
						chess.setChessBoard_y(chessBoard_y);

						// 判断该目标点是否有敌方棋子
						for (Chess_ol c : whiteChessList_mirror) {
							if (c.getChessBoard_x() == chessBoard_x && c.getChessBoard_y() == chessBoard_y) {
								c.setChessBoard_x(-2);
								c.setChessBoard_y(-2);
							}
						}
					}
					// 判断是否是王
					if (chess.getId() == 200) {
						general = chess;
					}
				}
				// 遍历敌方棋子
				for (Chess_ol redChess : whiteChessList_mirror) {
					// 判断移动以后是否会被敌方棋子击杀
					if (redChess.attack(general.getChessBoard_x(), general.getChessBoard_y(), whiteChessList_mirror,
							blackChessList_mirror)) {// 能击杀
						// 返回true表示被将军
						System.out.println("check");
						return true;
					}
				}
			}
		}
		System.out.println("not check");
		return false;
	}



	/**
	 * 检查目标点存在己方棋子
	 * 
	 * @param chessBoard_x   目标点棋盘X坐标
	 * @param chessBoard_y   目标点棋盘Y坐标
	 * @param whiteChessList   白色棋子集合
	 * @param blackChessList 黑色棋子集合
	 * @return true：有 / false：无
	 */
	protected boolean targetPointExistTeamChess(int chessBoard_x, int chessBoard_y, List<Chess_ol> whiteChessList,
			List<Chess_ol> blackChessList) {
		if (getTeam() == Team.White) {// 白色方
			for (Chess_ol whiteChess : whiteChessList) {
				if (whiteChess.getChessBoard_x() == chessBoard_x && whiteChess.getChessBoard_y() == chessBoard_y) {
					return true;
				}
			}
		} else if (getTeam() == Team.BLACK) {// 黑色方
			for (Chess_ol blackChess : blackChessList) {
				if (blackChess.getChessBoard_x() == chessBoard_x && blackChess.getChessBoard_y() == chessBoard_y) {
					return true;
				}
			}
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x >= 0 && x <= ChessBoard.CHESS_BOARD_WIDTH - width) {
			this.x = x;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y >= 0 && y <= ChessBoard.CHESS_BOARD_HEIGHT - height) {
			this.y = y;
		}
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

	public Team getTeam() {
		return team;
	}

	public int getChessBoard_x() {
		return chessBoard_x;
	}

	public void setChessBoard_x(int chessBoard_x) {
		this.chessBoard_x = chessBoard_x;
		x = ChessBoard.BORDER_WIDTH + ChessBoard.LATTICE_WIDTH * (chessBoard_x - 1);
	}

	public int getChessBoard_y() {
		return chessBoard_y;
	}

	public void setChessBoard_y(int chessBoard_y) {
		this.chessBoard_y = chessBoard_y;
		y = ChessBoard.BORDER_WIDTH + ChessBoard.LATTICE_WIDTH * (chessBoard_y - 1);
	}

	@Override
	public String toString() {
		return "Chess_ol [id=" + id + ", x=" + x + ", y=" + y + ", chessBoard_x=" + chessBoard_x + ", chessBoard_y="
				+ chessBoard_y + ", width=" + width + ", height=" + height + ", team=" + team + "]";
	}
}
