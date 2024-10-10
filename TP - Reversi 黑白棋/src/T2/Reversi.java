package T2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

public class Reversi {
//	建立物件
	private Board GS;
//	建立變數
	private int CELL_SIZE; //單元格大小(預設 65)
	private int width; //寬(預設 8)
	private int height; //高(預設 8)
	
	private int tX; //tX、tY: 暫存黑白棋位置
	private int tY;
	
	private int chessColor; //黑白棋顏色(判斷目前誰下棋)
	private int enemyChessColor;
	
	private boolean function;
	
	private int[] FSituation; //判斷八個方向是否可行走: 假設可以走，索引值對應內容值 。0:無路
	private boolean[][] FChessMark; //判斷有無訪問過棋盤上位置
	
	private int[][] checkerboard; //棋盤上資料
	private int[][][] reversiChess = new int[8][8][8];
//	建構子
	public Reversi(int CELL_SIZE, int width, int height, int[][] checkerboard, int chessColor) {
		this.CELL_SIZE = CELL_SIZE; //單元格大小(預設 65)
		this.width = width; //寬(預設 8)
		this.height = height; //高(預設 8)
		this.checkerboard = checkerboard; //目前棋盤資料: 。0: 空 。1: 白 。2:黑
		this.chessColor = chessColor; //黑白棋顏色(判斷目前誰下棋): 。0: 空 。1: 白 。2:黑
		
		getEnemyChessColor();
		
		init();
	}
//	初始化
	private void init() {
//		重新定義
		FSituation = new int[9]; //判斷八個方向是否可行走
		FChessMark = new boolean[8][8]; //判斷有無訪問過棋盤上位置
//		歸零
		for(int i = 0;i<height;i++) {
			Arrays.fill(FSituation, 0);
			Arrays.fill(FChessMark[i], false);
			for(int j = 0;j<height;j++) {
				Arrays.fill(reversiChess[i][j], 0);
			}
		}
	}
//	取的對手棋色
	public void getEnemyChessColor() {
		if(chessColor == 1) {
			enemyChessColor = 2;
		}else if(chessColor == 2) {
			enemyChessColor = 1;
		}
	}
//	取的目前黑白棋位置
	public void getLocation (int x, int y, boolean Function) { //。0: 空 。1；白 。2；黑
		this.function = Function;
		if(function) {
			exploreDirections(x, y); //探索方向
		}else {
			for(int i = 0; i < checkerboard.length; i++) {
			    for(int j = 0; j < checkerboard[i].length; j++) {
			    	if(checkerboard[i][j] == chessColor) {
			    		System.out.printf("%d  %d %d\n",checkerboard[i][j],i,j);
			    		exploreDirections(i, j); //探索方向
			    	}
			    }
			}
		}
	}
//	探索方向(連線道路)
	public void exploreDirections(int x, int y) {
//		夾角系列
		if(x==0 && y==0) { //左上角
			FSituation[5] = 5; //可行走道路
			FSituation[7] = 7;
			FSituation[8] = 8;
		}else if(x==7 && y==0) { //右上角
			FSituation[4] = 4; //可行走道路
			FSituation[6] = 6;
			FSituation[7] = 7;
		}else if(x==0 && y==7) { //左下角
			FSituation[2] = 2; //可行走道路
			FSituation[3] = 3;
			FSituation[5] = 5;
		}else if(x==7 && y==7) { //右下角
			FSituation[1] = 1; //可行走道路
			FSituation[2] = 2;
			FSituation[4] = 4;
		}
//		邊緣系列
		else if(x==0 || x==1) { //左邊、中心例外
//			邊緣例外
			if(y==1) {
				FSituation[7] = 7; //可行走道路
				FSituation[8] = 8;
			}else if(y==6) {
				FSituation[2] = 2;
				FSituation[3] = 3;
			}else {
				FSituation[2] = 2;
				FSituation[3] = 3;
				FSituation[7] = 7;
				FSituation[8] = 8;
			}
			FSituation[5] = 5; //固定
		}else if(y==0) { //上邊
//			邊緣例外
			if(x==1) {
				FSituation[5] = 5; //可行走道路
				FSituation[8] = 8;
			}else if(x==6) {
				FSituation[4] = 4;
				FSituation[6] = 6;
			}else {
				FSituation[4] = 4;
				FSituation[5] = 5;
				FSituation[6] = 6;
				FSituation[8] = 8;
			}
			FSituation[7] = 7; //固定
		}else if(x==6 || x==7) { //右邊、中心例外
//			邊緣例外
			if(y==1) {
				FSituation[6] = 6; //可行走道路
				FSituation[7] = 7;
			}else if(y==6) {
				FSituation[1] = 1;
				FSituation[2] = 2;
			}else {
				FSituation[1] = 1;
				FSituation[2] = 2;
				FSituation[6] = 6;
				FSituation[7] = 7;
			}
			FSituation[4] = 4; //固定
		}else if(y==7) { //下邊
//			邊緣例外
			if(x==1) {
				FSituation[3] = 3; //可行走道路
				FSituation[5] = 5;
			}else if(x==6) {
				FSituation[1] = 1;
				FSituation[4] = 4;
			}else {
				FSituation[1] = 1;
				FSituation[3] = 3;
				FSituation[4] = 4;
				FSituation[5] = 5;
			}
			FSituation[2] = 2; //固定
		}
//		中心
		else {
			for(int i = 1;i<9;i++) {
				FSituation[i] = i;
			}
		}
		exploreStart(x, y); //探索開始
	}
//	探索開始
	public void exploreStart(int x, int y) { //exploreStart: 探索開始
//		對八個方向進行探索
		for(int i = 1;i<9;i++) {
			exploreDirection(FSituation[i], x, y);
		}
	}
//	反轉黑白棋
	public void reversi(int x, int y, boolean function) {
//		reversiChess[y][x];
	}
//	探索方向
	public void exploreDirection(int direction, int x, int y) {
//		建立變數
		int minValue;
		
		switch(direction){
//		沒有路
			case 0 :
			break; //break continue 區別 誰最快下一步?
//		上左
			case 1:
//			暫存黑白棋位置，省略 exploreDirections 以判斷過位置
				tX = x -1;
				tY = y -1;
//			取的與邊界的最短距離
				minValue = Math.min(x, y);
				
				if(checkerboard[y-1][x-1] != enemyChessColor) break;
				
				for(int j = 0;j<minValue;j++) {
					if(checkerboard[tY][tX] == enemyChessColor) {
						tX--;
						tY--;
					}else if(checkerboard[tY][tX] == 0) {
						checkerboard[tY][tX] = 3;
						break;
					}
				}
//				for(int j = 0;j<minValue;j++) {
//					if(function) {
//						if(checkerboard[tY][tX] == chessColor) {
//							break;
//						}
//						if(checkerboard[tY][tX] == enemyChessColor) {
//							tX--;
//							tY--;
//						}
//					}else{
//						if(checkerboard[tY][tX] == enemyChessColor) {
//							tX--;
//							tY--;
//						}else if(checkerboard[tY][tX] == 0) {
//							checkerboard[tY][tX] = 3;
//							break;
//						}
//					}
//				}
			break;
//			上中
			case 2:
				tX = x;
				tY = y-2;
				
				if(checkerboard[y-1][x] != enemyChessColor) break;
				
				while(tY >= 0) {
					if(checkerboard[tY][tX] == enemyChessColor) {
						tY--;
					}else if(checkerboard[tY][tX] == 0) {
						checkerboard[tY][tX] = 3;
						break;
					}
				}
			break;
//			上右
			case 3:
				tX = x +2;
				tY = y -2;
//				取的與邊界的最短距離
				minValue = Math.min(7-x, y);
				
				if(checkerboard[y-1][x+1] != enemyChessColor) break;
				
				for(int j = 0;j<minValue;j++) {
					if(checkerboard[tY][tX] == enemyChessColor) {
						tX++;
						tY--;
					}else if(checkerboard[tY][tX] == 0) {
						checkerboard[tY][tX] = 3;
						break;
					}
				}
			break;
//			中左
			case 4:
				tX = x -2;
				tY = y;
				
				if(checkerboard[y-1][x] != enemyChessColor) break;
				
				while(tX >= 0) {
					if(checkerboard[tY][tX] == enemyChessColor) {
						tX--;
					}else if(checkerboard[tY][tX] == 0) {
						checkerboard[tY][tX] = 3;
						break;
					}
				}
			break;
//			中右
			case 5:
				tX = x +2;
				tY = y;
				
				if(checkerboard[y][x+1] != enemyChessColor) break;
				
				while(tX <= 7) {
					if(checkerboard[tY][tX] == enemyChessColor) {
						tX++;
					}else if(checkerboard[tY][tX] == 0) {
						checkerboard[tY][tX] = 3;
						break;
					}
				}
			break;
//			下左
			case 6:
				tX = x -2;
				tY = y +2;
//				取的與邊界的最短距離
				minValue = Math.min(x, 7-y);
				
				if(checkerboard[y+1][x-1] != enemyChessColor) break;
				
				for(int j = 0;j<minValue;j++) {
					if(checkerboard[tY][tX] == enemyChessColor) {
						tX--;
						tY++;
					}else if(checkerboard[tY][tX] == 0) {
						checkerboard[tY][tX] = 3;
						break;
					}
				}
			break;
//			下中
			case 7:
				tX = x;
				tY = y +2;
	
				if(checkerboard[y+1][x] != enemyChessColor) break;
				
				while(tY <= 7) {
					if(checkerboard[tY][tX] == enemyChessColor) {
						tY++;
					}else if(checkerboard[tY][tX] == 0) {
						checkerboard[tY][tX] = 3;
						break;
					}
				}
			break;
//			下右
			case 8:
				tX = x +2;
				tY = y +2;
//			取的與邊界的最短距離
				minValue = Math.min(7-x, 7-y);
				
				if(checkerboard[y+1][x+1] != enemyChessColor) break;
				
				for(int j = 0;j<minValue;j++) {
					if(checkerboard[tY][tX] == enemyChessColor) {
						tX++;
						tY++;
					}else if(checkerboard[tY][tX] == 0) {
						checkerboard[tY][tX] = 3;
						break;
					}
				}
			break;
			default:
				System.out.println("Error");
			break;
		}
	}
}
