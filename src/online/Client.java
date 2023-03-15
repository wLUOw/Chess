package online;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {

	private static Client client = new Client();  // 实例化客户端
	private String ip;  // ip地址
	private int port;  // 端口
	private Socket s;  // 套接字
	private static PrintWriter writer;  // 写出 字符流

	private Client() {
		super();
	}

	// 连接
	public boolean connect() {
		if (s == null) {
			try {
				// 建立连接
				s = new Socket(ip, port);
				System.out.println("连接成功!");

				// 实例化writer
				writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

				// 启动客户端处理器
				new ClientHandler(s).start();

				return true;
			} catch (Exception e) {
				// 错误提示
				WindowBoard wb = new WindowBoard("错误", 300, 140);
				wb.setLayout(null);

				JLabel lbl_text = new JLabel();
				lbl_text.setText("未找到玩家，请重新确认ip和端口号！");
				lbl_text.setSize(220, 30);
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
				return false;
			}
		} else {
			return true;
		}
	}

	// 获取客户端实例
	public static Client getInstance() {
		return client;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static void sendMessage(String message) {
		System.out.println(message);
		writer.println(message);
	}

	// 检查IP格式
	public static boolean checkIP(String ip) {
		if("localhost".equals(ip)) {// 判断是否为“localhost”
			return true;
		}else if(ip.matches("(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}")) {// 验证正则表达式
			return true;
		}
		return false;
	}

	 // 检查端口格式
	public static boolean checkPort(int port) {
		// 判断端口号是否在0~65535之间
		if(port >= 0 && port <= 65535) {
			return true;
		}
		return false;
	}
}

class ClientHandler extends Thread {
	// 套接字
	private Socket s;
	// 读入 字符流
	private BufferedReader reader;

	public ClientHandler(Socket socket) {
		this.s = socket;
		try {
			// 实例化reader
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		super.run();

		String message;

		while (true) {
			try {
				// 接收字符串
				message = reader.readLine();

				parseMessage(message);
			} catch (IOException e) {
				break;
			}
		}
	}

	/**
	 * 解析消息
	 * 
	 * @param message 消息字符串
	 */
	private void parseMessage(String message) {
		String[] strs = message.split("<split>");

		// 核实消息头
		if (strs[0].equals("Move")) {
			// 进行下棋
			ChessBoard.playChess(Integer.parseInt(strs[1]), Integer.parseInt(strs[2]), Integer.parseInt(strs[3]),
					Integer.parseInt(strs[4]));
		} else if (strs[0].equals("ResetGame")) {// 重置游戏
			// 请求
			if (strs[1].equals("request")) {
				WindowBoard wb = new WindowBoard("请求重开游戏", 300, 140);
				wb.setLayout(null);

				JLabel lbl_text = new JLabel();
				lbl_text.setText("对手请求重新开始游戏，是否同意？");
				lbl_text.setSize(230, 40);
				lbl_text.setLocation((wb.getWidth() - lbl_text.getWidth()) / 2, 15);

				JButton btn_accept = new JButton("同意");
				btn_accept.setSize(90, 20);
				btn_accept.setLocation(40, 70);
				btn_accept.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Client.sendMessage("ResetGame<split>accept");
						ChessBoard.resetGame();// 执行重开游戏
						wb.dispose();// 关闭该窗口
					}
				});

				JButton btn_refuse = new JButton("拒绝");
				btn_refuse.setSize(90, 20);
				btn_refuse.setLocation(170, 70);
				btn_refuse.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Client.sendMessage("ResetGame<split>refuse");
						wb.dispose();// 关闭该窗口
					}
				});

				// 讲组件添加进面板
				wb.add(lbl_text);
				wb.add(btn_accept);
				wb.add(btn_refuse);
				
				wb.repaint();
			} else if (strs[1].equals("accept")) {// 同意
				// 执行重开
				ChessBoard.resetGame();

				// 提示重开
				WindowBoard wb = new WindowBoard("重开提示", 300, 140);
				wb.setLayout(null);

				JLabel lbl_text = new JLabel();
				lbl_text.setText("对手同意了您的重开请求！");
				lbl_text.setSize(170, 30);
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
			} else if (strs[1].equals("refuse")) {// 拒绝
				WindowBoard wb = new WindowBoard("重开提示", 300, 140);
				wb.setLayout(null);

				JLabel lbl_text = new JLabel();
				lbl_text.setText("对手拒绝您的重开请求...");
				lbl_text.setSize(170, 30);
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
			}
		} else if (strs[0].equals("Regret")) {// 悔棋
			if (strs[1].equals("request")) {// 请求
				ChessBoard.setGameState(GameState.PAUSE);
				
				WindowBoard wb = new WindowBoard("请求悔棋", 300, 140);
				wb.setLayout(null);
				wb.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
				JLabel lbl_text = new JLabel();
				lbl_text.setText("对手请求悔棋，是否同意？");
				lbl_text.setSize(160, 40);
				lbl_text.setLocation((wb.getWidth() - lbl_text.getWidth()) / 2, 15);

				JButton btn_accept = new JButton("同意");
				btn_accept.setSize(90, 20);
				btn_accept.setLocation(40, 70);
				btn_accept.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Client.sendMessage("Regret<split>accept");
						ChessBoard.regret(Team.White);// 执行悔棋
						wb.dispose();// 关闭该窗口
					}
				});

				JButton btn_refuse = new JButton("拒绝");
				btn_refuse.setSize(90, 20);
				btn_refuse.setLocation(170, 70);
				btn_refuse.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Client.sendMessage("Regret<split>refuse");
						// 切换游戏状态
						ChessBoard.switchGameState();
						wb.dispose();// 关闭该窗口
					}
				});

				// 讲组件添加进面板
				wb.add(lbl_text);
				wb.add(btn_accept);
				wb.add(btn_refuse);
				
				wb.repaint();
			} else if (strs[1].equals("accept")) {// 同意悔棋
				// 执行悔棋
				ChessBoard.regret(Team.BLACK);

				// 提示悔棋
				WindowBoard wb = new WindowBoard("悔棋提示", 300, 140);
				wb.setLayout(null);

				JLabel lbl_text = new JLabel();
				lbl_text.setText("对手同意了您的悔棋请求！");
				lbl_text.setSize(150, 30);
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
			} else if (strs[1].equals("refuse")) {// 拒绝悔棋
				WindowBoard wb = new WindowBoard("悔棋提示", 300, 140);
				wb.setLayout(null);

				JLabel lbl_text = new JLabel();
				lbl_text.setText("对手拒绝您的悔棋请求...");
				lbl_text.setSize(150, 30);
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
				
				// 切换游戏状态
				ChessBoard.switchGameState();
			}
		} else if (strs[0].equals("Defeat")) {// 认输
			// 更改游戏状态
			ChessBoard.setGameState(GameState.END);

			WindowBoard wb = new WindowBoard("提示", 300, 140);
			wb.setLayout(null);

			JLabel lbl_text = new JLabel();
			lbl_text.setText("对手已投降，您已获胜！");
			lbl_text.setSize(150, 30);
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
		} else if(strs[0].equals("Exit")) {
			ChessBoard.setGameState(GameState.END);
			
			WindowBoard wb = new WindowBoard("提示", 300, 140);
			wb.setLayout(null);

			JLabel lbl_text = new JLabel();
			lbl_text.setText("对方已关闭游戏窗口...");
			lbl_text.setSize(150, 30);
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
		}else if(strs[0].equals("GameOver")) {
			// 设置游戏状态为结束
			ChessBoard.setGameState(GameState.END);
			
			WindowBoard wb = new WindowBoard("提示", 300, 140);
			wb.setLayout(null);

			JLabel lbl_text = new JLabel();
			lbl_text.setText("游戏结束，"+ strs[1] +"胜！");
			lbl_text.setSize(150, 30);
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
		}
	}
}
