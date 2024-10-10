package T3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Board extends JPanel {
//	建立物件
	private Reversi reversi; //遊戲規則
//	---
	private checkerboardListener mlistener; //棋盤監聽器
//	建立變數
	private int CELL_SIZE;
	private int width;
	private int height;
	
	private int chessColor;
	
	private int [][]checkerboard; //。0:空 。1:白 。2:黑
//	建構子
	public Board(int CELL_SIZE, int width, int height, int[][] checkerboard, int chessColor) {
		super(true);
		
		this.CELL_SIZE = CELL_SIZE;
		this.width = width;
		this.height = height;
		this.checkerboard = checkerboard;
		this.chessColor = chessColor;
		
		mlistener = new checkerboardListener();
		reversi = new Reversi(this.CELL_SIZE, this.width, this.height, this.checkerboard, this.chessColor); //遊戲規則
		init();
	}
//	初始化
	private void init() {
//		棋盤監聽器
		
		addMouseListener(mlistener);
		addMouseMotionListener(mlistener);
		setBackground(Color.black);
//		
//		reversi.getLocation(0,0,false);
		
//		setPreferredSize(new Dimension(width*CELL_SIZE, height*CELL_SIZE));
//		setPreferredSize(new Dimension(200, 300));
//		棋盤初始化
//		for(int i = 0;i<height;i++) {
//			Arrays.fill(checkerboard[i], 0);
//		}
//		checkerboard[3][3] = 1;
//		checkerboard[3][4] = 2;
//		checkerboard[4][3] = 2;
//		checkerboard[4][4] = 1;
	}
//	繪製組件
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
//		上色
		for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
//            	空
            	if(checkerboard[y][x] == 0) {
            		
            	}
//		        可放置位置
				else if(checkerboard[y][x] == 3) {
		        	g.setColor(getCellColor(checkerboard[y][x]));
		        	g.fillRect(x*CELL_SIZE +17, y*CELL_SIZE +17, 30, 30);
				}
//				黑白棋
            	else {
			    	Ellipse2D circle = new Ellipse2D.Double(x*CELL_SIZE +5, y*CELL_SIZE +5, CELL_SIZE -10, CELL_SIZE -10); // (x, y, width, height)
			    	g2d.setColor(getCellColor(checkerboard[y][x]));
			        g2d.fill(circle);
			        g2d.draw(circle);
				}
//        		格線
                g.setColor(Color.green);
				g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
		
//		super.repaint();
	}
//	取得單元格顏色
	public Color getCellColor(int cellValue) {
        switch (cellValue) {
        	case 0: 
        		
            case 1: //白棋
                return Color.white;
            case 2: //黑棋
                return Color.gray;
            case 3: //可放置位置
                return new Color(80, 250, 252);
            default:
                return Color.red;
        }
    }
//	取的棋盤資料
	public int [][]getCheckerboard(){
		return checkerboard;
	}
//	
//	@Override
//    public Dimension getPreferredSize() {
//        return new Dimension(200, 200);
//    }
//	取的點擊位置
	private void getLocation(int mX, int mY) {
		int cX = mX / CELL_SIZE;
		int cY = mY / CELL_SIZE;
		
		System.out.printf("%d %d   %d %d\n", mX,mY,cX,cY);
		
//		if(checkerboard[cY][cX] == 3) {
			if(reversi.exploreDirections(cX,cY)) {
				repaint();
				reversi.init();
				reversi.exchange();
			}else {
				reversi.init();
			}
			
			System.out.println("Continue!");
//		}
		
//		reversi.judge(cX, cY);
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
}
