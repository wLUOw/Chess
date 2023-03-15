package online;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class ChessBoard extends JPanel implements MouseMotionListener, MouseListener {
	
	// 棋盘宽度
	public static final int CHESS_BOARD_WIDTH = 635;
	// 棋盘高度
	public static final int CHESS_BOARD_HEIGHT = 635;
	// 棋格宽度
	public static final int LATTICE_WIDTH = 72;
	// 边距宽度
	public static final int BORDER_WIDTH = 14;

	// 游戏状态
	private static GameState gameState;

	// 游戏主机方
	private static boolean isClient;

	// 该回合的行动方
	private static Team actionTeam;
	// 我的队伍
	private static Team team;

	// 已选择的棋子
	private Chess_ol choosedChess;

	// 背景图片
	private BufferedImage backgroundImage;

	// 白色棋子集合
	private static List<Chess_ol> whiteChessList = new List<Chess_ol>() {
		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public Iterator<Chess_ol> iterator() {
			return null;
		}

		@Override
		public Object[] toArray() {
			return new Object[0];
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return null;
		}

		@Override
		public boolean add(Chess_ol chess) {
			return false;
		}

		@Override
		public boolean remove(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends Chess_ol> c) {
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends Chess_ol> c) {
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return false;
		}

		@Override
		public void clear() {

		}

		@Override
		public Chess_ol get(int index) {
			return null;
		}

		@Override
		public Chess_ol set(int index, Chess_ol element) {
			return null;
		}

		@Override
		public void add(int index, Chess_ol element) {

		}

		@Override
		public Chess_ol remove(int index) {
			return null;
		}

		@Override
		public int indexOf(Object o) {
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			return 0;
		}

		@Override
		public ListIterator<Chess_ol> listIterator() {
			return null;
		}

		@Override
		public ListIterator<Chess_ol> listIterator(int index) {
			return null;
		}

		@Override
		public List<Chess_ol> subList(int fromIndex, int toIndex) {
			return null;
		}
	};
	// 黑色棋子集合
	private static List<Chess_ol> blackChessList = new List<Chess_ol>() {
		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public Iterator<Chess_ol> iterator() {
			return null;
		}

		@Override
		public Object[] toArray() {
			return new Object[0];
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return null;
		}

		@Override
		public boolean add(Chess_ol chess) {
			return false;
		}

		@Override
		public boolean remove(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends Chess_ol> c) {
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends Chess_ol> c) {
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return false;
		}

		@Override
		public void clear() {

		}

		@Override
		public Chess_ol get(int index) {
			return null;
		}

		@Override
		public Chess_ol set(int index, Chess_ol element) {
			return null;
		}

		@Override
		public void add(int index, Chess_ol element) {

		}

		@Override
		public Chess_ol remove(int index) {
			return null;
		}

		@Override
		public int indexOf(Object o) {
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			return 0;
		}

		@Override
		public ListIterator<Chess_ol> listIterator() {
			return null;
		}

		@Override
		public ListIterator<Chess_ol> listIterator(int index) {
			return null;
		}

		@Override
		public List<Chess_ol> subList(int fromIndex, int toIndex) {
			return null;
		}
	};

	// 镜像白色棋子集合
	private static List<Chess_ol> whiteChessList_mirror = new List<Chess_ol>() {
		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public Iterator<Chess_ol> iterator() {
			return null;
		}

		@Override
		public Object[] toArray() {
			return new Object[0];
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return null;
		}

		@Override
		public boolean add(Chess_ol chess) {
			return false;
		}

		@Override
		public boolean remove(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends Chess_ol> c) {
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends Chess_ol> c) {
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return false;
		}

		@Override
		public void clear() {

		}

		@Override
		public Chess_ol get(int index) {
			return null;
		}

		@Override
		public Chess_ol set(int index, Chess_ol element) {
			return null;
		}

		@Override
		public void add(int index, Chess_ol element) {

		}

		@Override
		public Chess_ol remove(int index) {
			return null;
		}

		@Override
		public int indexOf(Object o) {
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			return 0;
		}

		@Override
		public ListIterator<Chess_ol> listIterator() {
			return null;
		}

		@Override
		public ListIterator<Chess_ol> listIterator(int index) {
			return null;
		}

		@Override
		public List<Chess_ol> subList(int fromIndex, int toIndex) {
			return null;
		}
	};
	// 镜像黑色棋子集合
	private static List<Chess_ol> blackChessList_mirror = new List<Chess_ol>() {
		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public Iterator<Chess_ol> iterator() {
			return null;
		}

		@Override
		public Object[] toArray() {
			return new Object[0];
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return null;
		}

		@Override
		public boolean add(Chess_ol chess) {
			return false;
		}

		@Override
		public boolean remove(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends Chess_ol> c) {
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends Chess_ol> c) {
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return false;
		}

		@Override
		public void clear() {

		}

		@Override
		public Chess_ol get(int index) {
			return null;
		}

		@Override
		public Chess_ol set(int index, Chess_ol element) {
			return null;
		}

		@Override
		public void add(int index, Chess_ol element) {

		}

		@Override
		public Chess_ol remove(int index) {
			return null;
		}

		@Override
		public int indexOf(Object o) {
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			return 0;
		}

		@Override
		public ListIterator<Chess_ol> listIterator() {
			return null;
		}

		@Override
		public ListIterator<Chess_ol> listIterator(int index) {
			return null;
		}

		@Override
		public List<Chess_ol> subList(int fromIndex, int toIndex) {
			return null;
		}
	};

	// 游戏步数
	private static int gameStep;
	// 历史棋谱
	private static List<ChessStep> chessStepList;

	// 鼠标光标
	private static Cursor mouseCursor;
	// 已选择光标
	private static Cursor chooseCursor;


	public ChessBoard() {
		// 设置尺寸
		setSize(CHESS_BOARD_WIDTH, CHESS_BOARD_HEIGHT);

		try {
			backgroundImage = ImageIO.read(ChessBoard.class.getResourceAsStream("/picture/chessboard/qipan-grey.png"));
		} catch (IOException e) {
			System.out.println("未找到图片!");
		}

		gameState = GameState.END;

		actionTeam = Team.White;

		chessStepList = new ArrayList<ChessStep>();

		mouseCursor = new Cursor(-2, -2, "/picture/cursor/cursor-yellow.png");
		chooseCursor = new Cursor(-2, -2, "/picture/cursor/cursor-red.png");

		// 生产棋子
		generateChesses();

		// 添加鼠标监听
		addMouseListener(this);
		// 添加鼠标运动监听
		addMouseMotionListener(this);
	}

	/**
	 * 生成棋子
	 */
	private static void generateChesses() {
		// 生成白色棋子集合
		whiteChessList = generateWhiteChessList();
		// 生成黑色棋子集合
		blackChessList = generateBlackChessList();
		// 生成镜像白色棋子集合
		whiteChessList_mirror = generateWhiteChessList();
		// 生成镜像黑色棋子集合
		blackChessList_mirror = generateBlackChessList();
	}

	/**
	 * 生成白色棋子集合
	 * 
	 * @return 全新的白色棋子集合
	 */
	private static List<Chess_ol> generateWhiteChessList() {
		List<Chess_ol> whiteChessList = new ArrayList<Chess_ol>();
		generateWhiteChessList(whiteChessList);
		return whiteChessList;
	}

	/**
	 * 生成白色棋子集合
	 * 
	 * @param whiteChessList 需要添加棋子的白色棋子集合
	 */
	private static void generateWhiteChessList(List<Chess_ol> whiteChessList) {
		// 判断集合内是否有棋子
		if (whiteChessList.size() != 0) {// 有
			// 清空集合
			whiteChessList.clear();
		}

		// 棋子:车1
		whiteChessList.add(new Rook_ol(141, 1, 8, "/picture/chess/Rook1.png", Team.White));
		// 棋子:马1
		whiteChessList.add(new Knight_ol(131, 2, 8, "/picture/chess/Knight1.png", Team.White));
		// 棋子:象1
		whiteChessList.add(new Bishop_ol(121, 3, 8, "/picture/chess/Bishop1.png", Team.White));
		// 棋子:后1
		whiteChessList.add(new Queen_ol(111, 4, 8, "/picture/chess/Queen1.png", Team.White));
		// 棋子:王
		whiteChessList.add(new King_ol(100, 5, 8, "/picture/chess/King1.png", Team.White));
		// 棋子:象2
		whiteChessList.add(new Bishop_ol(122, 6, 8, "/picture/chess/Bishop1.png", Team.White));
		// 棋子:马2
		whiteChessList.add(new Knight_ol(132, 7, 8, "/picture/chess/Knight1.png", Team.White));
		// 棋子:车2
		whiteChessList.add(new Rook_ol(142, 8, 8, "/picture/chess/Rook1.png", Team.White));

		// 棋子：兵1
		whiteChessList.add(new Pawn_ol(161, 1, 7, "/picture/chess/Pawn1.png", Team.White));
		// 棋子：兵2
		whiteChessList.add(new Pawn_ol(162, 2, 7, "/picture/chess/Pawn1.png", Team.White));
		// 棋子：兵3
		whiteChessList.add(new Pawn_ol(163, 3, 7, "/picture/chess/Pawn1.png", Team.White));
		// 棋子：兵4
		whiteChessList.add(new Pawn_ol(164, 4, 7, "/picture/chess/Pawn1.png", Team.White));
		// 棋子：兵5
		whiteChessList.add(new Pawn_ol(165, 5, 7, "/picture/chess/Pawn1.png", Team.White));
		// 棋子：兵6
		whiteChessList.add(new Pawn_ol(166, 6, 7, "/picture/chess/Pawn1.png", Team.White));
		// 棋子：兵7
		whiteChessList.add(new Pawn_ol(167, 7, 7, "/picture/chess/Pawn1.png", Team.White));
		// 棋子：兵8
		whiteChessList.add(new Pawn_ol(168, 8, 7, "/picture/chess/Pawn1.png", Team.White));
	}

	/**
	 * 生成黑色棋子集合
	 * 
	 * @return 全新的黑色棋子集合
	 */
	private static List<Chess_ol> generateBlackChessList() {
		List<Chess_ol> blackChessList = new ArrayList<Chess_ol>();
		generateBlackChessList(blackChessList);
		return blackChessList;
	}

	/**
	 * 生成黑色棋子集合
	 * 
	 * @param blackChessList 需要添加棋子的黑色棋子集合
	 */
	private static void generateBlackChessList(List<Chess_ol> blackChessList) {
		// 判断集合内是否有棋子
		if (blackChessList.size() != 0) {// 有
			// 清空集合
			blackChessList.clear();
		}

		// 棋子:车1
		blackChessList.add(new Rook_ol(241, 1, 1, "/picture/chess/Rook0.png", Team.BLACK));
		// 棋子:马1
		blackChessList.add(new Knight_ol(231, 2, 1, "/picture/chess/Knight0.png", Team.BLACK));
		// 棋子:象1
		blackChessList.add(new Bishop_ol(221, 3, 1, "/picture/chess/Bishop0.png", Team.BLACK));
		// 棋子:后1
		blackChessList.add(new Queen_ol(211, 4, 1, "/picture/chess/Queen0.png", Team.BLACK));
		// 棋子:王
		blackChessList.add(new King_ol(200, 5, 1, "/picture/chess/King0.png", Team.BLACK));
		// 棋子:象2
		blackChessList.add(new Bishop_ol(222, 6, 1, "/picture/chess/Bishop0.png", Team.BLACK));
		// 棋子:马2
		blackChessList.add(new Knight_ol(232, 7, 1, "/picture/chess/Knight0.png", Team.BLACK));
		// 棋子:车2
		blackChessList.add(new Rook_ol(242, 8, 1, "/picture/chess/Rook0.png", Team.BLACK));

		// 棋子：兵1
		blackChessList.add(new Pawn_ol(261, 1, 2, "/picture/chess/Pawn0.png", Team.BLACK));
		// 棋子：兵2
		blackChessList.add(new Pawn_ol(262, 2, 2, "/picture/chess/Pawn0.png", Team.BLACK));
		// 棋子：兵3
		blackChessList.add(new Pawn_ol(263, 3, 2, "/picture/chess/Pawn0.png", Team.BLACK));
		// 棋子：兵4
		blackChessList.add(new Pawn_ol(264, 4, 2, "/picture/chess/Pawn0.png", Team.BLACK));
		// 棋子：兵5
		blackChessList.add(new Pawn_ol(265, 5, 2, "/picture/chess/Pawn0.png", Team.BLACK));
		// 棋子：兵6
		blackChessList.add(new Pawn_ol(266, 6, 2, "/picture/chess/Pawn0.png", Team.BLACK));
		// 棋子：兵7
		blackChessList.add(new Pawn_ol(267, 7, 2, "/picture/chess/Pawn0.png", Team.BLACK));
		// 棋子：兵8
		blackChessList.add(new Pawn_ol(268, 8, 2, "/picture/chess/Pawn0.png", Team.BLACK));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// 画背景
		g.drawImage(backgroundImage, 0, 0, null);
		// 画棋子
		drawChesses(g);
		// 画选择光标
		drawCursor(g);
	}

	// 画棋子
	public void drawChesses(Graphics g) {
		// 黑色棋子
		for (Chess_ol chess : blackChessList) {
			g.drawImage(chess.getImg(), chess.getX(), chess.getY(), null);
		}

		// 白色棋子
		for (Chess_ol chess : whiteChessList) {
			g.drawImage(chess.getImg(), chess.getX(), chess.getY(), null);
		}
	}

	// 画选择光标
	public void drawCursor(Graphics g) {
		g.drawImage(mouseCursor.getImg(), mouseCursor.getX(), mouseCursor.getY(), null);
		g.drawImage(chooseCursor.getImg(), chooseCursor.getX(), chooseCursor.getY(), null);
	}

	// 清除选择棋子及标记
	private void clearChoose() {
		// 清除标记棋子
		choosedChess = null;
		// 清除棋子标记
		chooseCursor.setChessBoard_x(-2);
		chooseCursor.setChessBoard_y(-2);
	}

	// 重置游戏
	public static void resetGame() {
		// 设置游戏模式
		gameState = GameState.END;
		// 清除剩余棋子
		blackChessList.clear();
		whiteChessList.clear();
		// 生成棋子
		generateChesses();
		// 重置光标
		mouseCursor.setChessBoard_x(-2);
		mouseCursor.setChessBoard_y(-2);
		chooseCursor.setChessBoard_x(-2);
		chooseCursor.setChessBoard_y(-2);
		// 重置行动方
		actionTeam = Team.White;

		// 清除队伍
		team = null;

		// 重置游戏状态
		// 判断主机方
		if (isClient) {// 客户端方
			gameState = GameState.PAUSE;
		} else {// 服务端方
			gameState = GameState.RUNNING;
		}
	}

	/**
	 * 悔棋
	 * 
	 * @param requestTeam 请求悔棋方
	 */
	public static void regret(Team requestTeam) {
		// 判断游戏模式
		// 判断请求悔棋方
		if (requestTeam == actionTeam) {
			// 悔棋两步
			regretOneStep();
			// 判断是否还能悔棋
			if (gameStep > 0) {
				regretOneStep();
			}
		} else {
			// 悔棋一步
			regretOneStep();
		}
		// 切换游戏状态
		switchGameState();
	}

	/**
	 * 悔一步棋
	 */
	private static void regretOneStep() {
		// 便利历史棋谱
		for (ChessStep chessStep : chessStepList) {
			// 判断
			if (chessStep.getId() == (gameStep - 1)) {
				// 还原被吃棋子
				if (chessStep.getRemovedChess() != null) {// 判断是否有被吃棋子
					// 判断被吃棋子所属阵营
					if (chessStep.getRemovedChess().getTeam() == Team.White) {
						whiteChessList.add(chessStep.getRemovedChess());
					} else {
						blackChessList.add(chessStep.getRemovedChess());
					}
				}

				// 还原移动棋子
				chessStep.getMovedChess().setChessBoard_x(chessStep.getMovedChess_cbx());
				chessStep.getMovedChess().setChessBoard_y(chessStep.getMovedChess_cby());

				// 更换行动方
				actionTeam = actionTeam == Team.White ? Team.BLACK : Team.White;
			}
		}
		// 游戏步数-1
		gameStep--;
		// 移除被还原的棋谱
		chessStepList.remove(gameStep);
	}

	/**
	 * 切换游戏状态
	 */
	public static void switchGameState() {
		// 切换游戏状态
		// 判断行动方
		if (ChessBoard.getActionTeam() == Team.White) {// 行动方：白方
			if (ChessBoard.isClient()) {// 我是黑方
				ChessBoard.setGameState(GameState.PAUSE);
			} else {// 我是红方
				ChessBoard.setGameState(GameState.RUNNING);
			}
		} else {// 行动方：黑方
			if (ChessBoard.isClient()) {// 我是黑方
				ChessBoard.setGameState(GameState.RUNNING);
			} else {// 我是红方
				ChessBoard.setGameState(GameState.PAUSE);
			}
		}
	}

	// 下棋
	public static void playChess(int id, int chessBoard_x, int chessBoard_y, int enemyId) {
		Chess_ol choosedChess = null;
		Chess_ol targetChess = null;

		int lastX = -2;
		int lastY = -2;

		if (id >= 200) {// 黑棋子
			// 查找棋子
			for (Chess_ol chess : blackChessList) {
				if (chess.id == id) {
					lastX = chess.chessBoard_x;
					lastY = chess.chessBoard_y;
					choosedChess = chess;
				}
			}
		} else {// 白棋子
				// 查找棋子
			for (Chess_ol chess : whiteChessList) {
				if (chess.id == id) {
					lastX = chess.chessBoard_x;
					lastY = chess.chessBoard_y;
					choosedChess = chess;
				}
			}
		}

		if (enemyId >= 200) {// 黑棋子
			// 查找棋子
			for (Chess_ol chess : blackChessList) {
				if (chess.id == enemyId) {
					targetChess = chess;
				}
			}
		} else if (enemyId >= 100) {// 白棋子
			// 查找棋子
			for (Chess_ol chess : whiteChessList) {
				if (chess.id == enemyId) {
					targetChess = chess;
				}
			}
		}

		// 生成棋谱
		ChessStep cs = new ChessStep(gameStep, choosedChess, choosedChess.getChessBoard_x(),
				choosedChess.getChessBoard_y());

		// 移动棋子
		choosedChess.setChessBoard_x(chessBoard_x);
		choosedChess.setChessBoard_y(chessBoard_y);

		// 吃掉棋子
		if (targetChess != null) {
			if (enemyId >= 200) {
				// 添加被移除的棋子
				cs.setRemovedChess(targetChess);
				// 移除被吃棋子
				blackChessList.remove(targetChess);
			} else if (enemyId >= 100) {
				// 添加被移除的棋子
				cs.setRemovedChess(targetChess);
				// 移除被吃棋子
				whiteChessList.remove(targetChess);
			}
		}
		// 添加棋谱
		chessStepList.add(cs);
		// 增加游戏步数
		gameStep++;

		// 换方
		actionTeam = actionTeam == Team.White ? Team.BLACK : Team.White;
		// 调整游戏状态
		setGameState(GameState.RUNNING);
		
		// 判断胜负
		String victoryTeam = judgeVictory();
		// 若胜利队伍不为空
		if (!"".equals(victoryTeam)) {
			WindowBoard wb = new WindowBoard("提示", 300, 140);
			wb.setLayout(null);

			JLabel lbl_text = new JLabel();
			lbl_text.setFont(new Font("宋体", Font.BOLD, 25));
			lbl_text.setText(victoryTeam + "胜!");
			lbl_text.setSize(100, 26);
			lbl_text.setLocation((wb.getWidth() - lbl_text.getWidth()) / 2, 15);

			JButton btn_confirm = new JButton();
			btn_confirm.setText("确认");
			btn_confirm.setSize(80, 30);
			btn_confirm.setLocation((wb.getWidth() - btn_confirm.getWidth()) / 2, 60);

			btn_confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					wb.dispose();
				}
			});

			wb.add(lbl_text);
			wb.add(btn_confirm);

			wb.repaint();
			
			setGameState(GameState.END);
		}
	}

	/**
	 * 判断胜利
	 * 
	 * @return 胜利方
	 */
	public static String judgeVictory() {
		return judgeWhiteDead() ? "黑方" : judgeBlackDead() ? "白方" : "";
	}

	/**
	 * 判断白王被将死
	 * 
	 * @return 是否被将死
	 */
	private static boolean judgeWhiteDead() {
		// 判断白子路径
		for (Chess_ol whiteChess : getWhiteChessList_mirror()) {
			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					// 判断该位置是否能走
					if (whiteChess.step(i, j, getWhiteChessList_mirror(), getBlackChessList_mirror())) {
						// 判断是否白王处于将军
						if (!whiteChess.check(i, j)) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	/**
	 * 判断黑王是否被将死
	 * 
	 * @return 是否被将死
	 */
	private static boolean judgeBlackDead() {
		// 判断黑子路径
		for (Chess_ol blackChess : getBlackChessList_mirror()) {
			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					// 判断该位置是否能走
					if (blackChess.step(i, j, getWhiteChessList_mirror(), getBlackChessList_mirror())) {
						// 判断是否黑王处于将军
						if (!blackChess.check(i, j)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	// 鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		// 判断游戏是否处于运行状态
		if (gameState == GameState.RUNNING) {
			int mouseX = e.getX();
			int mouseY = e.getY();

			Chess_ol chess = null;

			// 判断点击位置是否有棋子
			for (Chess_ol blackChess : blackChessList) {
				if (mouseX >= blackChess.getX() && mouseX <= blackChess.getX() + blackChess.getWidth()
						&& mouseY >= blackChess.getY() && mouseY <= blackChess.getY() + blackChess.getHeight()) {
					chess = blackChess;
					break;
				}
			}

			// 判断是否已筛选出棋子
			if (chess == null) {// 未筛选出棋子则继续筛选白色棋子
				for (Chess_ol whiteChess : whiteChessList) {
					if (mouseX >= whiteChess.getX() && mouseX <= whiteChess.getX() + whiteChess.getWidth()
							&& mouseY >= whiteChess.getY() && mouseY <= whiteChess.getY() + whiteChess.getHeight()) {
						chess = whiteChess;
						break;
					}
				}
			}

			// 判断是否已选择棋子
			if (choosedChess != null) {// 已有选择
				// 判断点击点是否有棋子
				if (chess != null) {// 有
					// 判断棋子所属方
					if (chess.getTeam() == actionTeam) {// 己方
						// 切换标记棋子
						choosedChess = chess;
						// 切换棋子标记
						chooseCursor.setChessBoard_x(chess.chessBoard_x - 1);
						chooseCursor.setChessBoard_y(chess.chessBoard_y - 1);
					} else {// 敌方
						// 记录未移动时位置
						int lastSetpChessBoard_x = choosedChess.getChessBoard_x();
						int lastSetpChessBoard_y = choosedChess.getChessBoard_y();

						// 获取是否能移动结果
						int result = choosedChess.move((mouseX - BORDER_WIDTH) / LATTICE_WIDTH + 1,
								(mouseY - BORDER_WIDTH) / LATTICE_WIDTH + 1);
						// 判断结果
						if (result == 1) {
							// 添加上一步棋谱
							chessStepList.add(new ChessStep(gameStep, chess, choosedChess, lastSetpChessBoard_x,
									lastSetpChessBoard_y));
							// 游戏步数增加
							gameStep++;

							// 判断被点击棋子所属方
							if (chess.getTeam() == Team.White) {
								whiteChessList.remove(chess);
							} else if (chess.getTeam() == Team.BLACK) {
								blackChessList.remove(chess);
							}

							gameState = GameState.PAUSE;

							if (isClient) {
								Client.sendMessage("Move<split>" + choosedChess.getId() + "<split>"
										+ choosedChess.getChessBoard_x() + "<split>"
										+ choosedChess.getChessBoard_y() + "<split>" + chess.id);
							} else {
								Server.sendMessage("Move<split>" + choosedChess.getId() + "<split>"
										+ choosedChess.getChessBoard_x() + "<split>"
										+ choosedChess.getChessBoard_y() + "<split>" + chess.id);
							}

							// 换方
							actionTeam = actionTeam == Team.White ? Team.BLACK : Team.White;

							// 清除鼠标选择标记
							clearChoose();

							// 判断胜负
							String victoryTeam = judgeVictory();
							// 若胜利队伍不为空
							if (!"".equals(victoryTeam)) {
								WindowBoard wb = new WindowBoard("提示", 300, 140);
								wb.setLayout(null);

								JLabel lbl_text = new JLabel();
								lbl_text.setFont(new Font("宋体", Font.BOLD, 25));
								lbl_text.setText(victoryTeam + "胜!");
								lbl_text.setSize(100, 26);
								lbl_text.setLocation((wb.getWidth() - lbl_text.getWidth()) / 2, 15);

								JButton btn_confirm = new JButton();
								btn_confirm.setText("确认");
								btn_confirm.setSize(80, 30);
								btn_confirm.setLocation((wb.getWidth() - btn_confirm.getWidth()) / 2, 60);

								btn_confirm.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										wb.dispose();
									}
								});

								wb.add(lbl_text);
								wb.add(btn_confirm);

								wb.repaint();
								
								setGameState(GameState.END);
							}
						} else if (result == 0) {// 被将军
							WindowBoard wb = new WindowBoard("提示", 300, 140);
							wb.setLayout(null);

							JLabel lbl_text = new JLabel();
							lbl_text.setText("将军！此步无效！");
							lbl_text.setSize(110, 30);
							lbl_text.setLocation((wb.getWidth() - lbl_text.getWidth()) / 2, 15);

							JButton btn_confirm = new JButton();
							btn_confirm.setText("确认");
							btn_confirm.setSize(80, 30);
							btn_confirm.setLocation((wb.getWidth() - btn_confirm.getWidth()) / 2, 60);

							btn_confirm.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									wb.dispose();
								}
							});

							wb.add(lbl_text);
							wb.add(btn_confirm);

							wb.repaint();

							new Timer().schedule(new TimerTask() {
								@Override
								public void run() {
									wb.dispose();
								}
							}, 1500);
						} else {
							// 清除鼠标选择标记
							clearChoose();
						}
					}
				} else {// 无
					// 记录未移动时位置
					int lastSetpChessBoard_x = choosedChess.getChessBoard_x();
					int lastSetpChessBoard_y = choosedChess.getChessBoard_y();
					// 获取是否能移动结果
					int result = choosedChess.move((mouseX - BORDER_WIDTH) / LATTICE_WIDTH + 1,
							(mouseY - BORDER_WIDTH) / LATTICE_WIDTH + 1);
					// 能移动
					if (result == 1) {
						// 添加上一步棋谱
						chessStepList
								.add(new ChessStep(gameStep, choosedChess, lastSetpChessBoard_x, lastSetpChessBoard_y));
						// 游戏步数增加
						gameStep++;


						gameState = GameState.PAUSE;

						if (isClient) {
							Client.sendMessage("Move<split>" + choosedChess.getId() + "<split>"
									+ choosedChess.getChessBoard_x() + "<split>" + choosedChess.getChessBoard_y()
									+ "<split>0");
						} else {
							Server.sendMessage("Move<split>" + choosedChess.getId() + "<split>"
									+ choosedChess.getChessBoard_x() + "<split>" + choosedChess.getChessBoard_y()
									+ "<split>0");
						}

						// 换方
						actionTeam = actionTeam == Team.White ? Team.BLACK : Team.White;

						// 清除鼠标选择标记
						clearChoose();

						// 判断胜负
						String victoryTeam = judgeVictory();
						// 若胜利队伍不为空
						if (!"".equals(victoryTeam)) {
							WindowBoard wb = new WindowBoard("提示", 300, 140);
							wb.setLayout(null);

							JLabel lbl_text = new JLabel();
							lbl_text.setFont(new Font("宋体", Font.BOLD, 25));
							lbl_text.setText(victoryTeam + "胜!");
							lbl_text.setSize(100, 30);
							lbl_text.setLocation((wb.getWidth() - lbl_text.getWidth()) / 2, 15);

							JButton btn_confirm = new JButton();
							btn_confirm.setText("确认");
							btn_confirm.setSize(80, 26);
							btn_confirm.setLocation((wb.getWidth() - btn_confirm.getWidth()) / 2, 60);

							btn_confirm.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									wb.dispose();
								}
							});

							wb.add(lbl_text);
							wb.add(btn_confirm);

							wb.repaint();
							
							setGameState(GameState.END);
						}
					} else if (result == 0) {// 被将军
						WindowBoard wb = new WindowBoard("提示", 300, 140);
						wb.setLayout(null);

						JLabel lbl_text = new JLabel();
						lbl_text.setText("将军！此步无效！");
						lbl_text.setSize(110, 30);
						lbl_text.setLocation((wb.getWidth() - lbl_text.getWidth()) / 2, 15);

						JButton btn_confirm = new JButton();
						btn_confirm.setText("确认");
						btn_confirm.setSize(80, 30);
						btn_confirm.setLocation((wb.getWidth() - btn_confirm.getWidth()) / 2, 60);

						btn_confirm.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								wb.dispose();
							}
						});

						wb.add(lbl_text);
						wb.add(btn_confirm);

						wb.repaint();

						new Timer().schedule(new TimerTask() {
							@Override
							public void run() {
								wb.dispose();
							}
						}, 1500);
					} else {
						clearChoose();
					}
				}
			} else {// 无选择
					// 判断点击点是否有棋子
				if (chess != null) {// 有
					// 判断棋子所属方
					if (chess.getTeam() == actionTeam) {// 己方
						// 标记棋子
						choosedChess = chess;
						// 执行棋子标记
						chooseCursor.setChessBoard_x(chess.chessBoard_x - 1);
						chooseCursor.setChessBoard_y(chess.chessBoard_y - 1);
					}
				}
			}
		}
	}

	// 鼠标移入事件
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	// 鼠标移出事件
	@Override
	public void mouseExited(MouseEvent e) {

	}

	// 鼠标按下事件
	@Override
	public void mousePressed(MouseEvent e) {

	}

	// 鼠标抬起事件
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	// 鼠标
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	// 鼠标移动事件
	@Override
	public void mouseMoved(MouseEvent e) {
		if (gameState != GameState.END) {
			int chessBoard_x = (e.getX() - BORDER_WIDTH) / LATTICE_WIDTH;
			int chessBoard_y = (e.getY() - BORDER_WIDTH) / LATTICE_WIDTH;

			chessBoard_x = chessBoard_x > 7 ? 7 : chessBoard_x;
			chessBoard_y = chessBoard_y > 7 ? 7 : chessBoard_y;

			mouseCursor.setChessBoard_x(chessBoard_x);
			mouseCursor.setChessBoard_y(chessBoard_y);
		}
	}

	/*
	 * Get/Set
	 */
	public static List<Chess_ol> getWhiteChessList() {
		return whiteChessList;
	}

	public static List<Chess_ol> getBlackChessList() {
		return blackChessList;
	}

	public static List<Chess_ol> getWhiteChessList_mirror() {
		// 开始镜像
		for (int i = 0; i < whiteChessList_mirror.size(); i++) {
			Chess_ol mirrorChess = whiteChessList_mirror.get(i);
			Chess_ol chess = null;
			for (Chess_ol c : whiteChessList_mirror) {
				// 判断是否为同一棋子
				if (c.getId() == mirrorChess.getId()) {// 是
					chess = c;
				}
			}
			// 判断是否获取到该棋子
			if (chess == null) {// 未获取到
				mirrorChess.setChessBoard_x(-2);
				mirrorChess.setChessBoard_y(-2);
			} else {// 获取到则进行镜像
				mirrorChess.setChessBoard_x(chess.getChessBoard_x());
				mirrorChess.setChessBoard_y(chess.getChessBoard_y());
			}
		}
		return whiteChessList_mirror;
	}

	public static List<Chess_ol> getBlackChessList_mirror() {
		// 开始镜像
		for (int i = 0; i < blackChessList_mirror.size(); i++) {
			Chess_ol mirrorChess = blackChessList_mirror.get(i);
			Chess_ol chess = null;
			for (Chess_ol c : blackChessList) {
				// 判断是否为同一棋子
				if (c.getId() == mirrorChess.getId()) {// 是
					chess = c;
				}
			}
			// 判断是否获取到该棋子
			if (chess == null) {// 未获取到
				mirrorChess.setChessBoard_x(-2);
				mirrorChess.setChessBoard_y(-2);
			} else {// 获取到则进行镜像
				mirrorChess.setChessBoard_x(chess.getChessBoard_x());
				mirrorChess.setChessBoard_y(chess.getChessBoard_y());
			}
		}
		return blackChessList_mirror;
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		ChessBoard.gameState = gameState;
	}

	public static Team getActionTeam() {
		return actionTeam;
	}

	public static void setActionTeam(Team actionTeam) {
		ChessBoard.actionTeam = actionTeam;
	}

	public static Team getTeam() {
		return team;
	}

	public static void setTeam(Team team) {
		ChessBoard.team = team;
	}

	public static boolean isClient() {
		return isClient;
	}

	public static void setClient(boolean isClient) {
		ChessBoard.isClient = isClient;
	}

	public static int getGameStep() {
		return gameStep;
	}

	public static void setGameStep(int gameStep) {
		ChessBoard.gameStep = gameStep;
	}
}