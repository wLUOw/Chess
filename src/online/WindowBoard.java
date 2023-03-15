package online;

import javax.swing.*;

public class WindowBoard extends JFrame {

	/**
	 * 初始化窗口面板
	 * 
	 * @param title  窗体标题
	 * @param width  窗体宽度
	 * @param height 窗体高度
	 */
	public WindowBoard(String title, int width, int height) {
		super();

		// 设置标题
		setTitle(title);
		// 设置宽高
		setSize(width, height);
		// 设置窗口位置(居中)
		//setLocation((Game.SCREEN_WIDTH - width) / 2, (Game.SCREEN_HEIGHT - height) / 2);
		setLocationRelativeTo(this);
		// 设置禁止改变大小
		setResizable(false);
		// 设置可视
		setVisible(true);
		// 设置关闭
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// 刷新组件
		validate();
	}
}
