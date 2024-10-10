package T9_AlmostDone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Checkerboard extends JPanel {
//	建立物件
//	private
	Reversi reversi; //遊戲規則
	
	private checkerboardListener mlistener; //棋盤監聽器
	
	JFrame frame = new JFrame();
	
//	private JLabel jlabel = new JLabel();
//	private JButton nextButton = new JButton("Next Round"); //下一局
	
//	private JButton nextButton = new JButton("Next Round"); //下一局
	
//	建立變數
	private int CELL_SIZE = 65;
	private int width = 8;
	private int height = 8;
	private int [][]infCheckerboard = new int[height][width];; //。0:空 。1:白 。2:黑
	
//	int deviation = 37; //誤差值
	int deviation = 0; //誤差值
	
	boolean First = true;
	
//	建構子
	public Checkerboard() {
		super(true);
		init();
	}
//	初始化
	private void init() {
//		new JPanel(new BorderLayout());FlowLayout()
//		JPanel Host = new JPanel(new BorderLayout()); //主圖板
//		JPanel Function = new JPanel(new FlowLayout()); //功能視窗
//		setLayout(new BorderLayout());
		
		mlistener = new checkerboardListener();
		
//		jlabel.setText("Attacker： " + "黑" + "              "); //---------------------------------
//		jlabel.setFont(new Font("Label.font", Font.PLAIN, 20));
//		jlabel.setForeground(Color.white);
		

//		add(jlabel);		
//		add(nextButton);
		
		
		
		
//		Host.add(this, BorderLayout.CENTER);
//		Host.add(Function, BorderLayout.SOUTH);
		
		
//		棋盤初始化
//		nextButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//TODO Auto-generated method stub
//				reversi.initInfCheckerboard();
//				First = !First;
//				jlabel.setText("Attacker： " + " " + "              ");
//				repaint();
//			}
//		});
		
//		JFrame frame = new JFrame("JDialog Example");
//                JDialog dialog = new JDialog(frame, "Dialog", true); // 第三個參數true表示模態對話框
//
//                dialog.setSize(300, 200);
//                dialog.setLocationRelativeTo(frame);
//
////                dialog.add(new JButton("OK") {
////                    {
////                        addActionListener(new ActionListener() {
////                            public void actionPerformed(ActionEvent e) {
////                                dialog.dispose();
////                            }
////                        });
////                    }
////                });
//
//                dialog.setVisible(true);
            
		
		
		reversi = new Reversi(); //遊戲規則
		infCheckerboard = reversi.getInfCheckerboard().clone();
//		System.arraycopy(reversi.getInfCheckerboard(), 0, infCheckerboard, 0, reversi.getInfCheckerboard().length);

		reversi.setFirstAttacker(2);
		
//		棋盤監聽器
		addMouseListener(mlistener);
		addMouseMotionListener(mlistener);
		setBackground(Color.black);
		
	
	}
//	取的點擊位置
	private void getLocation(int mX, int mY) { //mX, mY: 游標位置。
		System.out.println(" -> .getLocation()-----------------------------------------------------------------------------------");

		mY = mY - deviation; //補上誤差值
		
		int cX = mX / CELL_SIZE; //cX, cY: 棋盤格位置。
		int cY = mY / CELL_SIZE;
		
		reversi.initReversiChess();
		
		if(mY < 0) { //避免例外誤差值
			
		}else {			
			
			
//			System.out.printf("Start\tcX:cY = %d:%d\tmX:mY = %d:%d\n", cX,cY,mX,mY);
////			探索開始
//			reversi.exploreDirections(cX, cY);
//			infCheckerboard = reversi.getInfCheckerboard().clone();
//			repaint();
//			reversi.ADExchange();
//			jlabel.setText("Attacker： " + changeAttacker() + "              ");
			
			
			System.out.printf("Start\tcX:cY = %d:%d\tmX:mY = %d:%d\n", cX,cY,mX,mY);
//			探索開始
			reversi.exploreDirections(cX, cY);
			
			if(reversi.getRST()) {
				
				
				reversi.tempInfCheckerboard = reversi.infCheckerboard.clone();
				infCheckerboard = reversi.getInfCheckerboard().clone();
				
				
				reversi.ADExchange();

				reversi.initGameOver();
				reversi.judgeGameOver(); //下完棋
				
				
				reversi.judgeCheckmate(); //死棋
				
				repaint();
				
				if(reversi.getGameOver() || reversi.getGameOver2()) {
					System.out.println(" - Game Over -");
					// 創建一個新的彈跳視窗
	                JDialog dialog = new JDialog(frame, "Reversi 黑白棋", true);
	                dialog.setLayout(new FlowLayout());
	                dialog.setLocation(100 +50 +40 -20,100 +180 +20 -5);
	                
	                	// 在彈跳視窗中顯示用戶輸入的內容
	                JLabel label = new JLabel("贏家 " + changeAttacker() + "方");
	                label.setFont(new Font("Label.font", Font.PLAIN, 27));
	                dialog.add(label);

	                // 設置彈跳視窗大小
	                dialog.setSize(300, 100);

	                // 顯示彈跳視窗
	                dialog.setVisible(true);
				}else {
//					jlabel.setText("Attacker： " + changeAttacker() + "              ");
				}
				reversi.initReversi();
				reversi.initGameOver();
				reversi.initGameOver2();
				reversi.initRST();
//				reversi.initQuantity();
				System.out.println("finish");
			}else {
				System.out.println("Error!!!");
			}
			
//			--------------------------------------------------------------------------------------------
//			if(First) { //判斷先手者為黑或白
//				if((cX==3 && cY==2) //黑  編號: 19
//				 ||(cX==2 && cY==3) //		  26
//				 ||(cX==5 && cY==4) //		  37
//				 ||(cX==4 && cY==5)) { //	  44
//					System.out.printf("\tAttacker: 2\tcX:cY = %d:%d\tmX:mY = %d:%d\n", cX,cY,mX,mY);
//					reversi.setFirstAttacker(2);
////					探索開始
////					reversi.initReversiChess();
//					reversi.exploreDirections(cX, cY);
//					infCheckerboard = reversi.getInfCheckerboard().clone();
//					repaint();
////					---------------------
//					reversi.ADExchange();
//					jlabel.setText("Attacker： " + changeAttacker() + "              ");
//					First = !First;
//				}else if((cX==4 && cY==2) //白  編號: 20
//					   ||(cX==2 && cY==4) //		34
//					   ||(cX==5 && cY==3) //		29
//					   ||(cX==3 && cY==5)) { //		43
//					System.out.printf("\tAttacker: 1\tcX:cY = %d:%d\tmX:mY = %d:%d\n", cX,cY,mX,mY);
//					reversi.setFirstAttacker(1);
////					探索開始
////					reversi.initReversiChess();
//					reversi.exploreDirections(cX, cY);
//					infCheckerboard = reversi.getInfCheckerboard().clone();
//					repaint();
////					---------------------
//					reversi.ADExchange();
//					jlabel.setText("Attacker： " + changeAttacker() + "              ");
//					First = !First;
//				}else { //判斷先手者下棋位置是否正確
//					System.out.printf("Error\tcX:cY = %d:%d\tmX:mY = %d:%d\n", cX,cY,mX,mY);
//				}
//			}else { //遊戲正式開始
//				System.out.printf("Start\tcX:cY = %d:%d\tmX:mY = %d:%d\n", cX,cY,mX,mY);
////				探索開始
////				reversi.initReversiChess();
//				reversi.exploreDirections(cX, cY);
//				infCheckerboard = reversi.getInfCheckerboard().clone();
//				repaint();
////				---------------------
//				reversi.ADExchange();
//				jlabel.setText("Attacker： " + changeAttacker() + "              ");
//			}
//			--------------------------------------------------------------------------------
			
		}
	}
//	繪製組件
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

        int value = 0;
        
        
		Graphics2D g2d = (Graphics2D) g;
//		上色
		for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
//        		格線
                g.setColor(Color.green);
				g.drawRect(x * CELL_SIZE, y * CELL_SIZE +deviation, CELL_SIZE, CELL_SIZE);
//				繪製數字----------------------------------------------------------------------------
//				g.setColor(Color.gray); //--------------------------------------------------------
//                g.setFont(new Font("Arial", Font.PLAIN, 50));
//                if(value > 9) {
//                	String numberString = String.valueOf(value++);
//                    g.drawString(numberString, x*CELL_SIZE +5, y*CELL_SIZE +51 +deviation);
//                }else {
//                	String numberString = String.valueOf(value++);
//                	g.drawString(numberString, x*CELL_SIZE +20, y*CELL_SIZE +51 +deviation);
//                } //-------------------------------------------------------------------------------
//            	空
            	if(infCheckerboard[y][x] == 0) {}
//		        可放置位置
//				else if(infCheckerboard[y][x] == 3) {
//		        	g.setColor(getCellColor(infCheckerboard[y][x]));
//		        	g.fillRect(x*CELL_SIZE +17, y*CELL_SIZE +17, 30, 30);
//				}
//				黑白棋
            	else {
			    	Ellipse2D circle = new Ellipse2D.Double(x*CELL_SIZE +5, y*CELL_SIZE +5 +deviation, CELL_SIZE -10, CELL_SIZE -10); // (x, y, width, height)
			    	g2d.setColor(getCellColor(infCheckerboard[y][x]));
			        g2d.fill(circle);
			        g2d.draw(circle);
				}
            }
        }
	}
//	取得單元格顏色
	public Color getCellColor(int cellValue) {
        switch (cellValue) {
//        	白棋
            case 1:
                return Color.white;
//          黑棋
            case 2:
                return Color.gray;
//			可放置位置
//			case 3:
//				return new Color(80, 250, 252);
            default:
                return Color.red;
        }
    }
//	棋盤監聽器
	class checkerboardListener implements MouseListener, MouseMotionListener {
//		建立變數
		public boolean mouseclicked; //判斷是否點擊點擊鼠標
//		建構子
		public checkerboardListener() {
			mouseclicked = false;
		}
//		當滑鼠按下按鈕時觸發此事件
		public void mousePressed(MouseEvent e) { 
			mouseclicked = true;
			getLocation(e.getX(), e.getY());
		}
//		當滑鼠釋放按鈕時觸發此事件
		public void mouseReleased(MouseEvent e) {
			mouseclicked = false;
		}
//		當滑鼠拖曳時觸發此事件
		public void mouseDragged(MouseEvent e) {}
//		當滑鼠移動時觸發此事件
		public void mouseMoved(MouseEvent e) {}
//		當滑鼠進入面板時觸發此事件
		public void mouseEntered(MouseEvent e) {}
//		當滑鼠離開面板時觸發此事件
		public void mouseExited(MouseEvent e) {}
//		當滑鼠點擊時觸發此事件
		public void mouseClicked(MouseEvent e) {}
	}
//	轉換目前進攻者
	public String changeAttacker() {
		System.out.println(" -> .changeAttacker()");

		if(reversi.getAttacker() == 1) {
			return "白";
		}else {
			return "黑";
		}
	}
}
