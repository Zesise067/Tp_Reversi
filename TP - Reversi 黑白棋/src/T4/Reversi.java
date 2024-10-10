package T4;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

public class Reversi {
//	建立物件
	private Checkerboard GS;
//	建立變數
	private int width; //寬(預設 8)
	private int height; //高(預設 8)
	
	private int Attacker; //攻擊方棋子顏色
	private int Defender = 2; //防守方棋子顏色
	
	private int[] tryDirections = new int[9]; //判斷八個方向是否可行走: 假設可以走，索引值對應內容值 。0:無路
	
	private int[][] infCheckerboard; //棋盤上資料
	private int[][] reversiChess = new int[8][8];
//	建構子
	public Reversi(int[][] infCheckerboard, int Attacker) {
		this.width = infCheckerboard[0].length; //寬(預設 8)
		this.height = infCheckerboard.length; //高(預設 8)
		this.infCheckerboard = infCheckerboard; //目前棋盤資料: 。0: 空 。1: 白 。2:黑
		this.Attacker = Attacker; //黑白棋顏色(判斷目前誰下棋): 。0: 空 。1: 白 。2:黑
			
		getDefender();
		
		init();
	}
//	初始化
	public void init() { //回傳攻擊方
		System.out.println(" - .init()");
//		初始化
		Arrays.fill(tryDirections, 0);
//		for(int i = 0;i<height;i++) {
//			Arrays.fill(reversiChess[i], 0);
//		}
	}
//	攻守交換
	public void ADExchange() { //回傳攻擊方
		System.out.println(" - .ADExchange()");
		int temp = Attacker;
		
		Attacker = Defender;
		Defender = temp;
		
		System.out.println("\tADExchange! ->  Attacker: " + Attacker);
	}
//	取的防守方
	public void getDefender() {
		if(Attacker == 1) {
			Defender = 2;
		}else if(Attacker == 2) {
			Defender = 1;
		}
	}
//	探索方向(連線道路)
	public void exploreDirections(int x, int y) {
		System.out.println(" - .exploreDirections()");
//		夾角系列
		if(x==0 && y==0) { //左上角
			exploreStart(x, y, 5);
			exploreStart(x, y, 7);
			exploreStart(x, y, 7);
//			printDirections();
		}else if(x==7 && y==0) { //右上角
			exploreStart(x, y, 4);
			exploreStart(x, y, 6);
			exploreStart(x, y, 7);
//			printDirections();
		}else if(x==0 && y==7) { //左下角
			exploreStart(x, y, 2);
			exploreStart(x, y, 3);
			exploreStart(x, y, 5);
//			printDirections();
		}else if(x==7 && y==7) { //右下角
			exploreStart(x, y, 1);
			exploreStart(x, y, 2);
			exploreStart(x, y, 4);
//			printDirections();
		}
//		邊緣系列
		else if(x==0 || x==1) { //左邊、中心例外
//			邊緣例外
			if(y==0 || y==1) {
				exploreStart(x, y, 7);
				exploreStart(x, y, 8);
//				tryDirections[7] = 7; //可行走道路
//				tryDirections[8] = 8;
			}else if(y==6 || y==7) {
				exploreStart(x, y, 2);
//				exploreStart(x, y, 3);
//				tryDirections[2] = 2;
//				tryDirections[3] = 3;
			}else {
				exploreStart(x, y, 2);
				exploreStart(x, y, 3);
				exploreStart(x, y, 7);
				exploreStart(x, y, 8);
//				tryDirections[2] = 2;
//				tryDirections[3] = 3;
//				tryDirections[7] = 7;
//				tryDirections[8] = 8;
			}
			exploreStart(x, y, 5);
//			tryDirections[5] = 5; //固定
//			printDirections();
		}else if(y==0 || y ==1) { //上邊
//			邊緣例外
			if(x==1) {
				exploreStart(x, y, 5);
				exploreStart(x, y, 8);
//				tryDirections[5] = 5; //可行走道路
//				tryDirections[8] = 8;
			}else if(x==6 || x==7) {
				exploreStart(x, y, 4);
				exploreStart(x, y, 6);
//				tryDirections[4] = 4;
//				tryDirections[6] = 6;
			}else {
				exploreStart(x, y, 4);
				exploreStart(x, y, 5);
				exploreStart(x, y, 6);
				exploreStart(x, y, 8);
//				tryDirections[4] = 4;
//				tryDirections[5] = 5;
//				tryDirections[6] = 6;
//				tryDirections[8] = 8;
			}
			exploreStart(x, y, 7);
//			tryDirections[7] = 7; //固定
//			printDirections();
		}else if(x==6 || x==7) { //右邊、中心例外
//			邊緣例外
			if(y==1) {
				exploreStart(x, y, 6);
				exploreStart(x, y, 7);
//				tryDirections[6] = 6; //可行走道路
//				tryDirections[7] = 7;
			}else if(y==6 || y==7) {
				exploreStart(x, y, 1);
				exploreStart(x, y, 2);
//				tryDirections[1] = 1;
//				tryDirections[2] = 2;
			}else {
				exploreStart(x, y, 1);
				exploreStart(x, y, 2);
				exploreStart(x, y, 6);
				exploreStart(x, y, 7);
//				tryDirections[1] = 1;
//				tryDirections[2] = 2;
//				tryDirections[6] = 6;
//				tryDirections[7] = 7;
			}
			exploreStart(x, y, 4);
//			tryDirections[4] = 4; //固定
//			printDirections();
		}else if(y==7 || y==6) { //下邊
//			邊緣例外
			if(x==1) {
				exploreStart(x, y, 3);
				exploreStart(x, y, 5);
//				tryDirections[3] = 3; //可行走道路
//				tryDirections[5] = 5;
			}else if(x==6) {
				exploreStart(x, y, 1);
				exploreStart(x, y, 4);
//				tryDirections[1] = 1;
//				tryDirections[4] = 4;
			}else {
				exploreStart(x, y, 1);
				exploreStart(x, y, 3);
				exploreStart(x, y, 4);
				exploreStart(x, y, 5);
//				tryDirections[1] = 1;
//				tryDirections[3] = 3;
//				tryDirections[4] = 4;
//				tryDirections[5] = 5;
			}
			exploreStart(x, y, 2);
//			tryDirections[2] = 2; //固定
//			printDirections();
		}
//		中心
		else {
			for(int i = 1;i<9;i++) {
				exploreStart(x, y, i);
//				tryDirections[i] = i;
			}
//			printDirections();
		}
		reversiColor(); //探索開始
	}
//	探索開始
	public void exploreStart(int x, int y, int tryDirections) { //exploreStart: 探索開始
		System.out.println(" -- .exploreStart()");
//		建立變數
		int tX; //tX、tY: 暫存黑白棋位置
		int tY;
		int minValue;
//		getTryDirections();
//		對八個方向進行探索		
		switch(tryDirections){
//			上左
			case 1:		
				System.out.println("case 1");			
//				暫存黑白棋位置，省略 exploreDirections 以判斷過位置
				tX = x -1;
				tY = y -1;
//				取的與邊界的最短距離
				minValue = Math.min(x, y);
				
				if(infCheckerboard[tY][tX] != Defender) break;
				
				for(int j = 0;j<minValue;j++) {
					if(infCheckerboard[tY][tX] == Defender) {
						reversiChess[tY][tX] = Defender;
						tX--;
						tY--;
					}else if(infCheckerboard[tY][tX] == Attacker) {
						System.out.println("case 1");
						infCheckerboard[y][x] = Attacker;
						break;
					}else {
						initReversiChess();
						break;
					}
				}
			break;
//			上中
			case 2:
				System.out.println("case 2");
				tX = x;
				tY = y-1; 
				
				if(infCheckerboard[y-1][x] != Defender) break;
				
				while(tY >= 0) {
					if(infCheckerboard[tY][tX] == Defender) {
//							System.out.println("case 2 - continue");
						reversiChess[tY][tX] = Defender;
						tY--;
					}else if(infCheckerboard[tY][tX] == Attacker) { //--------------------------------
						System.out.println("case 2");
						infCheckerboard[y][x] = Attacker;
						break;
					}else {
						initReversiChess();
						break;
					}
				}
			break;
//				上右
			case 3:
				System.out.println("case 3");
				tX = x +1;
				tY = y -1;
//					取的與邊界的最短距離
				minValue = Math.min(7-x, y);
				
				if(infCheckerboard[y-1][x+1] != Defender) break;
				
				for(int j = 0;j<minValue;j++) {
					if(infCheckerboard[tY][tX] == Defender) {
						reversiChess[tY][tX] = Defender;
						tX++;
						tY--;
					}else if(infCheckerboard[tY][tX] == Attacker) {
						System.out.println("case 3");
						infCheckerboard[y][x] = Attacker;
						break;
					}else {
						initReversiChess();
						break;
					}
				}
			break;
//				中左
			case 4:
				System.out.println("case 4");
				tX = x -1;
				tY = y;
				
				if(infCheckerboard[tY][tX] != Defender) {
					System.out.println("1");
					break;
				}
				
				while(tX >= 0) {
					if(infCheckerboard[tY][tX] == Defender) {
						System.out.println("2");
						reversiChess[tY][tX] = Defender;
						tX--;
					}else if(infCheckerboard[tY][tX] == Attacker) {
						System.out.println("case 4");
						System.out.println("3");
						infCheckerboard[y][x] = Attacker;
						printInfCheckerboard();
						break;
					}else {
						System.out.println("4");
						initReversiChess();
						break;
					}
				}

			break;
//				中右
			case 5:
				System.out.println("case 5");
				tX = x +1;
				tY = y;
				
				if(infCheckerboard[tY][tX] != Defender) {
//					System.out.println("1");
					break;
				}
				
				while(tX <= 7) {
					if(infCheckerboard[tY][tX] == Defender) {
//						System.out.println("2");
						reversiChess[tY][tX] = Defender;
						tX++;
					}else if(infCheckerboard[tY][tX] == Attacker) {
						System.out.println("case 5");
						System.out.println("3");
						infCheckerboard[y][x] = Attacker;
						break;
					}else {
//						System.out.println("4");
						initReversiChess();
						break;
					}
				}
			break;
//				下左
			case 6:
				System.out.println("case 6");
				tX = x -1;
				tY = y +1;
//					取的與邊界的最短距離
				minValue = Math.min(x, 7-y);
				
				if(infCheckerboard[y+1][x-1] != Defender) break;
				
				for(int j = 0;j<minValue;j++) {
					if(infCheckerboard[tY][tX] == Defender) {
						reversiChess[tY][tX] = Defender;
						tX--;
						tY++;
					}else if(infCheckerboard[tY][tX] == Attacker) {
						System.out.println("case 6");
						infCheckerboard[y][x] = Attacker;
						break;
					}else {
						initReversiChess();
						break;
					}
				}

			break;
//				下中
			case 7:
				System.out.println("case 7");
				tX = x;
				tY = y +1;
	
				if(infCheckerboard[y+1][x] != Defender) break;
				
				while(tY <= 7) {
					if(infCheckerboard[tY][tX] == Defender) {
						reversiChess[tY][tX] = Defender;
						tY++;
					}else if(infCheckerboard[tY][tX] == Attacker) {
						System.out.println("case 7");
						infCheckerboard[y][x] = Attacker;
						break;
					}else {
						initReversiChess();
						break;
					}
				}
			break;
//				下右
			case 8:
				System.out.println("case 8");
				tX = x +1;
				tY = y +1;
//				取的與邊界的最短距離
				minValue = Math.min(7-x, 7-y);
				
				if(infCheckerboard[y+1][x+1] != Defender) break;
				
				for(int j = 0;j<minValue;j++) {
					if(infCheckerboard[tY][tX] == Defender) {
						reversiChess[tY][tX] = Defender;
						tX++;
						tY++;
					}else if(infCheckerboard[tY][tX] == Attacker) {
						System.out.println("case 8");
						infCheckerboard[y][x] = Attacker;
						break;
					}else {
						initReversiChess();
						break;
					}
				}
			break;
			default:
				System.out.println("Error");
			break;
		}	
		
	}
//	輸出可探索方向 - 沒有 tryDirections
//	public void printDirections(){
//		System.out.println(" -- .printDirections()");
//		for(int i = 1;i<tryDirections.length;i++) {
//			if(i==1 || i==4 || i==6) {
//				System.out.print("\t");
//			}
//			System.out.print("  " + tryDirections[i]);
//			if(i==3 || i==5) {
//				System.out.println();
//			}else if(i == 4) {
//				System.out.print("   ");
//			}
//		}
//		System.out.println();
//	}
//	反轉黑白棋顏色
	public void reversiColor() {
		System.out.println(" --- .reversiColor()");
		
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				if(reversiChess[i][j] == 0) {
					continue;
				}
				infCheckerboard[i][j] = Attacker;
				System.out.println(Attacker);
			}
		}
		printInfCheckerboard();
		printReversiChess();
	}
//	輸出翻轉棋子
	public void printReversiChess() {
		int value = 0;
		System.out.println(" ---- .printReversiChess()");
		for(int i = 0;i<height;i++) {
			System.out.print("\t"); //-------------------
			for(int j = 0;j<width;j++) {
				value++;
				if(infCheckerboard[i][j] == 0 || infCheckerboard[i][j] != Attacker) {
					System.out.print("0\t"); //-------------------
					continue;
				}else{
					System.out.print((value-1) + "\t"); //-------------------
				}
			}
			System.out.println(); //-------------------
		}
	}
//	輸出可行走到路
//	public void getTryDirections() {
//		System.out.println(" ---- .getTryDirections()");
//		for(int i = 0;i<height;i++) {
//			System.out.print(Arrays.toString(tryDirections));
//		}
//	}
//	取的進攻者顏色
	public int getAttacker() {
		return Attacker;
	}
//	取的棋盤上資料
//	public int[][] getInfCheckerboard() {
//		return infCheckerboard;
//	}
//	輸出棋盤上的值
	public void printInfCheckerboard() {
		System.out.println("   0  1  2  3  4  5  6  7");
		for(int i = 0;i<height;i++) {
			System.out.print(i + " " + Arrays.toString(infCheckerboard[i]));
			System.out.println();
		}
	}
//	初始化暫存翻轉棋
	public void initReversiChess() {
		for(int i = 0;i<height;i++) {
			Arrays.fill(reversiChess[i], 0);
		}
	}
}
