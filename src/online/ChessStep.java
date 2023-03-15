package online;

public class ChessStep {
	// 步骤ID
	private int id;
	// 被吃掉的棋子
	private Chess_ol removedChess;
	// 移动的棋子
	private Chess_ol movedChess;
	// 移动前的棋盘x
	private int movedChess_cbx;
	// 移动前的棋盘y
	private int movedChess_cby;
	// 光标棋盘x
	private int cursor_cbx;
	// 光标棋盘y
	private int cursor_cby;

	
	public ChessStep(int id, Chess_ol movedChess, int movedChess_cbx, int movedChess_cby) {
		super();
		this.id = id;
		this.removedChess = null;
		this.movedChess = movedChess;
		this.movedChess_cbx = movedChess_cbx;
		this.movedChess_cby = movedChess_cby;
	}
	
	public ChessStep(int id, Chess_ol removedChess, Chess_ol movedChess, int movedChess_cbx, int movedChess_cby) {
		super();
		this.id = id;
		this.removedChess = removedChess;
		this.movedChess = movedChess;
		this.movedChess_cbx = movedChess_cbx;
		this.movedChess_cby = movedChess_cby;
	}

	public int getId() {
		return id;
	}

	public Chess_ol getRemovedChess() {
		return removedChess;
	}
	
	public void setRemovedChess(Chess_ol removedChess) {
		this.removedChess = removedChess;
	}

	public Chess_ol getMovedChess() {
		return movedChess;
	}

	public int getMovedChess_cbx() {
		return movedChess_cbx;
	}

	public int getMovedChess_cby() {
		return movedChess_cby;
	}

	public int getCursor_cbx() {
		return cursor_cbx;
	}

	public int getCursor_cby() {
		return cursor_cby;
	}
}
