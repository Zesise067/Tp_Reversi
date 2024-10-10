package T4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Checkerboard extends JPanel {
//	建立物件
	private Reversi reversi; //遊戲規則
	
	private checkerboardListener mlistener; //棋盤監聽器
//	建立變數
	private int CELL_SIZE;
	private int width;
	private int height;
	
	private int Attacker; //進攻方
	
	private boolean firstChoice = true; //決定黑棋還是白棋
	private boolean firstCheckerboard = true;
	
	private int [][]infCheckerboard; //。0:空 。1:白 。2:黑
//	建構子
	public Checkerboard(int CELL_SIZE, int[][] InfCheckerboard) {
		super(true);
//		初始化物件
		this.CELL_SIZE = CELL_SIZE;
		this.width = InfCheckerboard[0].length;
		this.height = InfCheckerboard.length;
		this.infCheckerboard = InfCheckerboard;

		reversi = new Reversi(infCheckerboard, 1); //遊戲規則
		init();
	}
//	初始化
	private void init() {
		mlistener = new checkerboardListener();
//		棋盤監聽器
		addMouseListener(mlistener);
		addMouseMotionListener(mlistener);
		setBackground(Color.black);
	}
//	取的點擊位置
	private void getLocation(int mX, int mY) { //mX, mY: 游標位置。
		System.out.println("-----------------------------------------\n"
							+ ".getLocation()");
		int cX = mX / CELL_SIZE; //cX, cY: 棋盤格位置。
		int cY = mY / CELL_SIZE;
		
		System.out.printf("\tcX:cY = %d:%d\tmX:mY = %d:%d\n", cX,cY,mX,mY);
		
//		-----------------------------------------------------------------------------------------------------------------------
//		if(firstChoice) {
//			System.out.println("First Choice!");
////			選擇黑還是白
//			if((cX==3 && cY==2) 
//			 ||(cX==2 && cY==3)
//			 ||(cX==5 && cY==4)
//			 ||(cX==4 && cY==5)) {
//				Attacker = 2;
//			}else if((cX==4 && cY==2)
//				   ||(cX==2 && cY==4)
//				   ||(cX==5 && cY==3)
//				   ||(cX==3 && cY==5)) {
//				Attacker = 1;
//			}
//			System.out.println("\tAttacker = " + Attacker);
//			reversi = new Reversi(infCheckerboard, 1); //遊戲規則
//			firstChoice = !firstChoice;
//		}
		reversi.printInfCheckerboard();
		reversi.exploreDirections(cX, cY);
		
//		System.out.println("Before");
//		printInfCheckerboard();
//		infCheckerboard = reversi.getInfCheckerboard().clone();
//		System.out.println("After");
//		printInfCheckerboard();
		

		System.out.println(".repaint()"); //--------------------------------------
		super.repaint();
		
		reversi.init();
//		getAttackerColor();
		reversi.ADExchange();
		reversi.printInfCheckerboard();
//		if(checkerboard[cY][cX] == 3) {
//			if(reversi.exploreDirections(cX,cY)) {
//				repaint();
//				reversi.init();
//				reversi.exchange();
//			}else {
//				reversi.init();
//			}
//			
//			System.out.println("Continue!");
//		}
//		
//		reversi.judge(cX, cY);
//		-----------------------------------------------------------------------------------------------------------------------
	}
//	取的攻擊方顏色
//	public String getAttackerColor() {
//		if(Attacker == 1) {
//			return "白";
//		}else {
//			return "黑";
//		}
//	}
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
				g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
//				繪製數字----------------------------------------------------------------------------
				g.setColor(Color.gray); //--------------------------------------------------------
                g.setFont(new Font("Arial", Font.PLAIN, 50));
                if(value > 9) {
                	String numberString = String.valueOf(value++);
                    g.drawString(numberString, x*CELL_SIZE +5, y*CELL_SIZE +51);
                }else {
                	String numberString = String.valueOf(value++);
                	g.drawString(numberString, x*CELL_SIZE +20, y*CELL_SIZE +51);
                } //-------------------------------------------------------------------------------
//            	空
            	if(infCheckerboard[y][x] == 0) {}
//		        可放置位置
//				else if(infCheckerboard[y][x] == 3) {
//		        	g.setColor(getCellColor(infCheckerboard[y][x]));
//		        	g.fillRect(x*CELL_SIZE +17, y*CELL_SIZE +17, 30, 30);
//				}
//				黑白棋
            	else {
			    	Ellipse2D circle = new Ellipse2D.Double(x*CELL_SIZE +5, y*CELL_SIZE +5, CELL_SIZE -10, CELL_SIZE -10); // (x, y, width, height)
			    	g2d.setColor(getCellColor(infCheckerboard[y][x]));
			        g2d.fill(circle);
			        g2d.draw(circle);
				}
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	
            }
        }
		
//		super.repaint();
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
			case 3:
				return new Color(80, 250, 252);
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
//	輸出棋盤上的值
//	public void printInfCheckerboard() {
//		System.out.println("   0  1  2  3  4  5  6  7");
//		for(int i = 0;i<height;i++) {
//			System.out.print(i + " " + Arrays.toString(infCheckerboard[i]));
//			System.out.println();
//		}
//	}
}
