package online;

import view.BeginFrame;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame {
	
	public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int FRAME_WIDTH = 614;
	public static final int FRAME_HEIGHT = 614 + 50;

	public Game() {
		// 设置图标
//		try {
//			setIconImage(ImageIO.read(Game.class.getResourceAsStream("/imgs/icon.png")));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		// 设置标题
		setTitle("国际象棋");
		// 设置窗体位置
		setLocation((SCREEN_WIDTH - FRAME_WIDTH) / 2, (SCREEN_HEIGHT - FRAME_HEIGHT) / 2);
		// 设置窗体大小
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// 设置不可调整大小
		setResizable(false);
		// 设置关闭结束进程
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 设置可视化
		setVisible(true);
		
		addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {  // 关闭窗口退出游戏
				// 判断主机
				if (ChessBoard.isClient()) {
					Client.sendMessage("Exit<split>GameOver");
				} else {
					Server.sendMessage("Exit<split>GameOver");
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		});
	}

	public static void go() {
		new MainFrame_ol();
	}
}

class MainFrame_ol extends JFrame{
	public static Client client;
	public static Server server;
	public MainFrame_ol(){
		// 创建游戏窗口
		Game g = new Game();

		// 上面板
		JPanel topJPanel = new JPanel();
		topJPanel.setLayout(null);
		topJPanel.setBounds(0, 0, 600, 30);

		// 棋盘
		ChessBoard chessBoard = new ChessBoard();

		// 菜单栏
		JMenuBar jmb = new JMenuBar();
		jmb.setLocation(0, 0);
		jmb.setSize(635, 30);

		JMenu Menu_startGame = new JMenu("　　  操作  　　");

		JMenuItem jmi_onlineMode = new JMenuItem("　    连接    　");

		JMenuItem jmi_back = new JMenuItem("　    返回    　");

		jmi_onlineMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowBoard wb = new WindowBoard("联机模式", 300, 235);

				// 清除布局器
				wb.setLayout(null);

				// 实例化“玩家IP”标签
				JLabel lbl_playerIP = new JLabel();
				lbl_playerIP.setText("玩家IP：");
				lbl_playerIP.setLocation(30, 15);
				lbl_playerIP.setSize(80, 20);

				// 实例化“玩家IP”文本框
				JTextField txt_playerIP = new JTextField();
				txt_playerIP.setLocation(105, 15);
				txt_playerIP.setSize(150, 20);

				// 实例化“玩家端口”标签
				JLabel lbl_playerPort = new JLabel();
				lbl_playerPort.setText("玩家端口：");
				lbl_playerPort.setLocation(30, 45);
				lbl_playerPort.setSize(80, 20);

				// 实例化“玩家端口”文本框
				JTextField txt_playerPort = new JTextField();
				txt_playerPort.setLocation(105, 45);
				txt_playerPort.setSize(100, 20);

				// 实例化"连接状态"标签
				JLabel lbl_connectState = new JLabel();
				lbl_connectState.setText("连接状态：");
				lbl_connectState.setLocation(30, 75);
				lbl_connectState.setSize(80, 20);

				// 实例化"连接状态"标签
				JLabel lbl_connectState1 = new JLabel();
				lbl_connectState1.setText("未连接");
				lbl_connectState1.setLocation(105, 75);
				lbl_connectState1.setSize(100, 20);

				// 实例化"本地IP"标签
				JLabel lbl_localIP = new JLabel();
				lbl_localIP.setText("本机IP：");
				lbl_localIP.setLocation(30, 135);
				lbl_localIP.setSize(80,20);

				// 实例化"本地IP"标签
				JLabel lbl_localIP1 = new JLabel();
				lbl_localIP1.setLocation(105, 135);
				lbl_localIP1.setSize(100,20);
				try {
					lbl_localIP1.setText(InetAddress.getLocalHost().getHostAddress().toString());
				} catch (UnknownHostException e1) {
					lbl_localIP1.setText("获取失败");
				}

				// 实例化"本机端口"标签
				JLabel lbl_localPort = new JLabel();
				lbl_localPort.setText("本机端口：");
				lbl_localPort.setLocation(30, 165);
				lbl_localPort.setSize(80,20);

				// 实例化"本机端口"标签
				JLabel lbl_localPort1 = new JLabel();
				lbl_localPort1.setText("请先启动服务端...");
				lbl_localPort1.setLocation(105, 165);
				lbl_localPort1.setSize(150,20);

				// 实例化"连接玩家"按钮
				JButton btn_connectPlayer = new JButton();
				btn_connectPlayer.setText("连接玩家");
				btn_connectPlayer.setLocation(30, 105);
				btn_connectPlayer.setSize(100,20);

				btn_connectPlayer.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 获取输入的IP
						String ip = txt_playerIP.getText().trim();
						// 获取输入的端口号
						int port = Integer.parseInt(txt_playerPort.getText().equals("")?"99999" : txt_playerPort.getText());

						// 验证IP格式是否正确
						if(Client.checkIP(ip)) {// IP正确
							// 验证端口格式是否正确
							if(Client.checkPort(port)) {// 端口正确
								// 设置ip和端口
								client = Client.getInstance();
								client.setIp(ip);
								client.setPort(port);

								// 判断是否已连接
								if(client.connect()) {
									ChessBoard.setActionTeam(Team.White);
									ChessBoard.setGameState(GameState.PAUSE);
									ChessBoard.setClient(true);
									lbl_connectState1.setText("已连接！");

									// 1.5秒后关闭窗口
									new Timer().schedule(new TimerTask() {
										@Override
										public void run() {
											wb.dispose();
										}
									}, 1500);
								}
							}else {
								// 错误提示
								WindowBoard wb = new WindowBoard("错误", 300, 140);
								wb.setLayout(null);

								JLabel lbl_text = new JLabel();
								lbl_text.setText("端口号格式有误，大小在0~65535之间！");
								lbl_text.setSize(230, 30);
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
						}else {// 错误
							// 错误提示
							WindowBoard wb = new WindowBoard("错误", 300, 140);
							wb.setLayout(null);

							JLabel lbl_text = new JLabel();
							lbl_text.setText("IP地址格式有误，请检查后重新输入！");
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
						}
					}
				});

				// 实例化"启动服务端"按钮
				JButton btn_runServer = new JButton();
				btn_runServer.setText("启动服务端");
				btn_runServer.setLocation(155, 105);
				btn_runServer.setSize(100,20);

				btn_runServer.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 获取服务端实例
						server = Server.getInstance();
						lbl_localPort1.setText(""+server.getPort());
						// 连接玩家按钮设置只读
						btn_connectPlayer.setEnabled(false);

						new Timer().schedule(new TimerTask() {
							@Override
							public void run() {
								// 判断是否已连接
								if(server.accept()) {
									ChessBoard.setActionTeam(Team.White);
									ChessBoard.setGameState(GameState.RUNNING);
									ChessBoard.setClient(false);
									lbl_connectState1.setText("已连接！");

									// 1.5秒后关闭窗口
									new Timer().schedule(new TimerTask() {
										@Override
										public void run() {
											wb.dispose();
										}
									}, 1500);
								}
							}
						}, 0);
					}
				});

				wb.add(lbl_playerIP);
				wb.add(txt_playerIP);
				wb.add(lbl_playerPort);
				wb.add(txt_playerPort);
				wb.add(lbl_connectState);
				wb.add(lbl_connectState1);
				wb.add(btn_connectPlayer);
				wb.add(btn_runServer);
				wb.add(lbl_localIP);
				wb.add(lbl_localIP1);
				wb.add(lbl_localPort);
				wb.add(lbl_localPort1);
			}
		});

		jmi_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BeginFrame();
				g.setVisible(false);
			}
		});

		Menu_startGame.add(jmi_onlineMode);
		Menu_startGame.add(jmi_back);
		jmb.add(Menu_startGame);

		JMenu Menu_playerTools = new JMenu("　　玩家操作　　");

		JMenuItem jmi_restart = new JMenuItem("　　重开　　");
		jmi_restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 判断信息发送方
				if(ChessBoard.isClient()) {
					Client.sendMessage("ResetGame<split>request");
				}else {
					Server.sendMessage("ResetGame<split>request");
				}
			}
		});

		JMenuItem jmi_regret = new JMenuItem("　　悔棋　　");
		jmi_regret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 判断悔棋标识
				if(ChessBoard.getGameStep() > 0) {
					if(ChessBoard.isClient()) {
						Client.sendMessage("Regret<split>request");
					}else {
						Server.sendMessage("Regret<split>request");
					}
					// 暂停游戏
					ChessBoard.setGameState(GameState.PAUSE);
				}
			}
		});

		JMenuItem jmi_surrender = new JMenuItem("　　认输　　");
		jmi_surrender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ChessBoard.isClient()) {
					// 发送投降信息
					Client.sendMessage("Defeat<split>QAQ");
					// 更改游戏状态
					ChessBoard.setGameState(GameState.END);
				}else {
					// 发送投降信息
					Server.sendMessage("Defeat<split>QAQ");
					// 更改游戏状态
					ChessBoard.setGameState(GameState.END);
				}
			}
		});



		Menu_playerTools.add(jmi_restart);
		Menu_playerTools.add(jmi_regret);
		Menu_playerTools.add(jmi_surrender);
		jmb.add(Menu_playerTools);

		topJPanel.add(jmb);

		// 分栏
		JSplitPane jsp = new JSplitPane();
		// 设置分割线宽度
		jsp.setDividerSize(1);
		// 设置分割方向
		jsp.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jsp.setTopComponent(topJPanel);
		jsp.setBottomComponent(chessBoard);

		// 30px 处分割
		jsp.setDividerLocation(30);
		// 禁止拖动
		jsp.setEnabled(false);

		g.add(jsp);
		g.validate();// 绘制
		g.repaint();

		// 重绘棋盘
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if(ChessBoard.getGameState() != GameState.END) {
					chessBoard.repaint();
				}
			}
		}, 100,6);
	}
}
