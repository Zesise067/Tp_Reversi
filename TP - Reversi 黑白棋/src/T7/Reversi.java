package T7;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

public class Reversi {
//	建立變數
	private int width; //寬(預設 8)
	private int height; //高(預設 8)
	
	private int Attacker; //攻擊方棋子顏色
	private int Defender = 2; //防守方棋子顏色
	
	private int[][] infCheckerboard = new int[8][8]; //棋盤上資料
	private int[][] tempInfCheckerboard = new int[8][8]; //暫存棋盤上資料
	private int[][] reversiChess = new int[8][8];
	private int[][] tempPath = new int[8][8];
	
	private boolean exploreFlag;
//	建構子
	public Reversi() {
		this.width = infCheckerboard[0].length; //寬(預設 8)
		this.height = infCheckerboard.length; //高(預設 8)
		
		initInfCheckerboard();
	}
//	攻守交換
	public void ADExchange() { //回傳攻擊方
		System.out.println(" -> .ADExchange()");
		int temp = Attacker;
		
		Attacker = Defender;
		Defender = temp;
		
		System.out.println("\tAttacker: " + Attacker + "\n\tDefender: " + Defender);
	}
//	設定防守方
	public void setDefender() {
		System.out.println(" -> .setDefender()");
		if(Attacker == 1) {
			Defender = 2;
		}else if(Attacker == 2) {
			Defender = 1;
		}
	}
//	探索方向(連線道路)
	public void exploreDirections(int x, int y, boolean ExploreFlag) {
		System.out.println(" -> .exploreDirections()");
		
		this.exploreFlag = ExploreFlag;
		
//		夾角系列
		if(x==0 && y==0) { //左上角
			exploreStart(x, y, 5);
			exploreStart(x, y, 7);
			exploreStart(x, y, 8);
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}else if(x==7 && y==0) { //右上角
			exploreStart(x, y, 4);
			exploreStart(x, y, 6);
			exploreStart(x, y, 7);
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}else if(x==0 && y==7) { //左下角
			exploreStart(x, y, 2);
			exploreStart(x, y, 3);
			exploreStart(x, y, 5);
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}else if(x==7 && y==7) { //右下角
			exploreStart(x, y, 1);
			exploreStart(x, y, 2);
			exploreStart(x, y, 4);
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}
//		邊緣系列
		else if(x==0 || x==1) { //左邊、中心例外
//			邊緣例外
			if(y==0 || y==1) {
				exploreStart(x, y, 7);
				exploreStart(x, y, 8);
			}else if(y==6 || y==7) {
				exploreStart(x, y, 2);
			}else {
				exploreStart(x, y, 2);
				exploreStart(x, y, 3);
				exploreStart(x, y, 7);
				exploreStart(x, y, 8);
			}
			exploreStart(x, y, 5);
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}else if(y==0 || y ==1) { //上邊
//			邊緣例外
			if(x==1) {
				exploreStart(x, y, 5);
				exploreStart(x, y, 8);
			}else if(x==6 || x==7) {
				exploreStart(x, y, 4);
				exploreStart(x, y, 6);
			}else {
				exploreStart(x, y, 4);
				exploreStart(x, y, 5);
				exploreStart(x, y, 6);
				exploreStart(x, y, 8);
			}
			exploreStart(x, y, 7);
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}else if(x==6 || x==7) { //右邊、中心例外
//			邊緣例外
			if(y==1) {
				exploreStart(x, y, 6);
				exploreStart(x, y, 7);
			}else if(y==6 || y==7) {
				exploreStart(x, y, 1);
				exploreStart(x, y, 2);
			}else {
				exploreStart(x, y, 1);
				exploreStart(x, y, 2);
				exploreStart(x, y, 6);
				exploreStart(x, y, 7);
			}
			exploreStart(x, y, 4);
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}else if(y==7 || y==6) { //下邊
//			邊緣例外
			if(x==1) {
				exploreStart(x, y, 3);
				exploreStart(x, y, 5);
			}else if(x==6) {
				exploreStart(x, y, 1);
				exploreStart(x, y, 4);
			}else {
				exploreStart(x, y, 1);
				exploreStart(x, y, 3);
				exploreStart(x, y, 4);
				exploreStart(x, y, 5);
			}
			exploreStart(x, y, 2);
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}
//		中心
		else {
			for(int i = 1;i<9;i++) {
				exploreStart(x, y, i);
				System.out.println("\tFinish case " + i);
			}
//			System.out.println("\tFinish Directions");
//			-----------------------
//			printInfCheckerboard();
//			printReversiChess();
//			reversiColor(); //探索開始
//			-----------------------
		}
//		printInfCheckerboard();
//		printReversiChess();
//		reversiColor(); //探索開始
		
//		if(exploreFlag == false) {
//			printTempInfCheckerboard();
//			setPath_tempPath();
//			printTempInfCheckerboard();
//		}
	}
//	探索開始
	public void exploreStart(int x, int y, int tryDirections) { //exploreStart: 探索開始
		System.out.println(" -> .exploreStart()");
		
//		建立變數
		int tX; //tX、tY: 暫存黑白棋位置
		int tY;
		int minValue;
//		getTryDirections();
//		對八個方向進行探索		
		switch(tryDirections){
//			上左
			case 1:		
				System.out.println("\tcase 1");			
//				暫存黑白棋位置，省略 exploreDirections 以判斷過位置
				tX = x -1;
				tY = y -1;
//				取的與邊界的最短距離
				minValue = Math.min(x, y);
				
				if(infCheckerboard[tY][tX] != Defender) break;
				
				for(int j = 0;j<minValue;j++) {
					if(infCheckerboard[tY][tX] == Defender) {
						if(exploreFlag) {
							reversiChess[tY][tX] = Defender;
						}
						tX--;
						tY--;
					}else if(infCheckerboard[tY][tX] == Attacker && exploreFlag) {
						System.out.println("\tcase 1");
						infCheckerboard[y][x] = Attacker;
						reversiColor(); //探索開始
						
//						printInfCheckerboard();
						break;
					}else if(infCheckerboard[tY][tX] == 0) {
						System.out.println("\tcase 1");
						
						tempPath[tY][tX] = 3;
						setPath_tempPath();
					}else {
						if(exploreFlag) {
							System.out.println("\tCall initReversiChess() ");
							initReversiChess();
						}
						else {
							System.out.println("\tCall initTempInfCheckerboard() ");
							initTempPath();;
						}
//						break;
					}
				}
			break;
//			上中
			case 2:
				System.out.println("\tcase 2");
				tX = x;
				tY = y-1; 
				
				if(infCheckerboard[y-1][x] != Defender) break;
				
				while(tY >= 0) {
					if(infCheckerboard[tY][tX] == Defender) {
//							System.out.println("case 2 - continue");
						if(exploreFlag) {
							reversiChess[tY][tX] = Defender;
						}
						tY--;
					}else if(infCheckerboard[tY][tX] == Attacker && exploreFlag) { //--------------------------------
						System.out.println("\tcase 2");
						infCheckerboard[y][x] = Attacker;
						reversiColor(); //探索開始
						
//						printInfCheckerboard();
						break;
					}else if(infCheckerboard[tY][tX] == 0) {
						System.out.println("\tcase 2");
						
						tempPath[tY][tX] = 3;
						setPath_tempPath();
					}else {
						if(exploreFlag) {
							System.out.println("\tCall initReversiChess() ");
							initReversiChess();
						}
						else {
							System.out.println("\tCall initTempInfCheckerboard() ");
							initTempPath();;
						}
						break;
					}
				}
			break;
//				上右
			case 3:
				System.out.println("\tcase 3");
				tX = x +1;
				tY = y -1;
//					取的與邊界的最短距離
				minValue = Math.min(7-x, y);
				
				if(infCheckerboard[y-1][x+1] != Defender) break;
				
				for(int j = 0;j<minValue;j++) {
					if(infCheckerboard[tY][tX] == Defender) {
						if(exploreFlag) {
							reversiChess[tY][tX] = Defender;
						}
						tX++;
						tY--;
					}else if(infCheckerboard[tY][tX] == Attacker && exploreFlag) {
						System.out.println("\tcase 3");
						infCheckerboard[y][x] = Attacker;
						reversiColor(); //探索開始
						
//						printInfCheckerboard();
						break;
					}else if(infCheckerboard[tY][tX] == 0) {
						System.out.println("\tcase 1");
						
						tempPath[tY][tX] = 3;
						setPath_tempPath();
					}else {
						if(exploreFlag) {
							System.out.println("\tCall initReversiChess() ");
							initReversiChess();
						}
						else {
							System.out.println("\tCall initTempInfCheckerboard() ");
							initTempPath();;
						}
						break;
					}
				}
			break;
//				中左
			case 4:
				System.out.println("\tcase 4");
				tX = x -1;
				tY = y;
				
				if(infCheckerboard[tY][tX] != Defender) {
					break;
				}
				
				while(tX >= 0) {
					if(infCheckerboard[tY][tX] == Defender) {
						if(exploreFlag) {
							reversiChess[tY][tX] = Defender;
						}
						tX--;
					}else if(infCheckerboard[tY][tX] == Attacker && exploreFlag) {
						System.out.println("\tcase 4");
						infCheckerboard[y][x] = Attacker;
						reversiColor(); //探索開始
						
//						printInfCheckerboard();
						break;
					}else if(infCheckerboard[tY][tX] == 0) {
						System.out.println("\tcase 1");
						
						tempPath[tY][tX] = 3;
						setPath_tempPath();
					}else {
						if(exploreFlag) {
							System.out.println("\tCall initReversiChess() ");
							initReversiChess();
						}
						else {
							System.out.println("\tCall initTempInfCheckerboard() ");
							initTempPath();;
						}
						break;
					}
				}
			break;
//			中右
			case 5:
				System.out.println("\tcase 5");
				System.out.println("\tAttacker: " + Attacker + "\n\tDefender: " + Defender); //----------------
				tX = x +1;
				tY = y;
				
				if(infCheckerboard[tY][tX] != Defender) {
					break;
				}
				
				while(tX <= 7) {
					if(infCheckerboard[tY][tX] == Defender) {
						if(exploreFlag) {
							reversiChess[tY][tX] = Defender;
						}
//						printReversiChess(); //-------------------
						tX++;
					}else if(infCheckerboard[tY][tX] == Attacker && exploreFlag) {
						System.out.println("\tcase 5");
						
						infCheckerboard[y][x] = Attacker;
						reversiColor(); //探索開始
						
//						printInfCheckerboard();
						break;
					}else if(infCheckerboard[tY][tX] == 0) {
						System.out.println("\tcase 5");
						
						tempPath[tY][tX] = 3;
						setPath_tempPath();
						
//						printInfCheckerboard();
						break;
					}else {
						if(exploreFlag) {
							System.out.println("\tCall initReversiChess() ");
							initReversiChess();
						}
						else {
							System.out.println("\tCall initTempInfCheckerboard() ");
							initTempPath();;
						}
						break;
					}
				}
			break;
//			下左
			case 6:
				System.out.println("\tcase 6");
				tX = x -1;
				tY = y +1;
//					取的與邊界的最短距離
				minValue = Math.min(x, 7-y);
				
				if(infCheckerboard[y+1][x-1] != Defender) break;
				
				for(int j = 0;j<minValue;j++) {
					if(infCheckerboard[tY][tX] == Defender) {
						if(exploreFlag) {
							reversiChess[tY][tX] = Defender;
						}
						tX--;
						tY++;
					}else if(infCheckerboard[tY][tX] == Attacker && exploreFlag) {
						System.out.println("\tcase 6");
						infCheckerboard[y][x] = Attacker;
						reversiColor(); //探索開始
						
//						printInfCheckerboard();
						break;
					}else if(infCheckerboard[tY][tX] == 0) {
						System.out.println("\tcase 6");
						
						tempPath[tY][tX] = 3;
						setPath_tempPath();
					}else {
						if(exploreFlag) {
							System.out.println("\tCall initReversiChess() ");
							initReversiChess();
						}
						else {
							System.out.println("\tCall initTempInfCheckerboard() ");
							initTempPath();;
						}
						break;
					}
				}

			break;
//				下中
			case 7:
				System.out.println("\tcase 7");
				tX = x;
				tY = y +1;
	
				if(infCheckerboard[tY][tX] != Defender) break;
				
				while(tY <= 7) {
					if(infCheckerboard[tY][tX] == Defender) {
						if(exploreFlag) {
							reversiChess[tY][tX] = Defender;
						}
						tY++;
					}else if(infCheckerboard[tY][tX] == Attacker && exploreFlag) {
						System.out.println("\tcase 7");
						infCheckerboard[y][x] = Attacker;
						reversiColor(); //探索開始
						
//						printInfCheckerboard();
						break;
					}else if(infCheckerboard[tY][tX] == 0) {
						System.out.println("\tcase 7");
						
						tempPath[tY][tX] = 3;
						setPath_tempPath();
					}else {
						if(exploreFlag) {
							System.out.println("\tCall initReversiChess() ");
							initReversiChess();
						}
						else {
							System.out.println("\tCall initTempInfCheckerboard() ");
							initTempPath();;
						}
						break;
					}
				}
			break;
//				下右
			case 8:
				System.out.println("\tcase 8");
				tX = x +1;
				tY = y +1;
//				取的與邊界的最短距離
				minValue = Math.min(7-x, 7-y);
				
				if(infCheckerboard[y+1][x+1] != Defender) break;
				
				for(int j = 0;j<minValue;j++) {
					if(infCheckerboard[tY][tX] == Defender) {
						if(exploreFlag) {
							reversiChess[tY][tX] = Defender;
						}
						tX++;
						tY++;
					}else if(infCheckerboard[tY][tX] == Attacker && exploreFlag) {
						System.out.println("\tcase 8");
						infCheckerboard[y][x] = Attacker;
						reversiColor(); //探索開始
						
//						printInfCheckerboard();
						break;
					}else if(infCheckerboard[tY][tX] == 0) {
						System.out.println("\tcase 8");
						
						tempPath[tY][tX] = 3;
						setPath_tempPath();
					}else {
						if(exploreFlag) {
							System.out.println("\tCall initReversiChess() ");
							initReversiChess();
						}
						else {
							System.out.println("\tCall initTempInfCheckerboard() ");
							initTempPath();;
						}
						break;
					}
				}
			break;
			default:
				System.out.println("\tError!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			break;
		}	
	}
//	反轉黑白棋顏色
	public void reversiColor() {
		System.out.println(" -> .reversiColor()");
		
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				if(reversiChess[i][j] == 0) {
					continue;
				}
				infCheckerboard[i][j] = Attacker;
//				System.out.println(Attacker);
			}
		}
		printInfCheckerboard();
		printReversiChess();
	}
//	輸出翻轉棋子
	public void printReversiChess() {
		System.out.println(" -> .printReversiChess()");
		int value = 0;
		System.out.println("\t   0\t1\t2\t3\t4\t5\t6\t7");
		for(int i = 0;i<height;i++) {
			System.out.print("\t" + i + "  "); //-------------------
			for(int j = 0;j<width;j++) {
				value++;
				if(reversiChess[i][j] == 0) {
					System.out.print("0\t"); //-------------------
					continue;
				}else{
					System.out.print((value-1) + "\t"); //-------------------
				}
			}
			System.out.println(); //-------------------
		}
	}
//	取的進攻者顏色
	public int getAttacker() {
		System.out.println(" -> .getAttacker()");
		return Attacker;
	}
//	取的棋盤上資料
//	public int[][] getInfCheckerboard() {
//		return infCheckerboard;
//	}
//	輸出棋盤上的值
	public void printInfCheckerboard() {
		System.out.println(" -> .printInfCheckerboard()");
		System.out.println("\t   0  1  2  3  4  5  6  7");
		for(int i = 0;i<height;i++) {
			System.out.print("\t" + i + " " + Arrays.toString(infCheckerboard[i]));
			System.out.println();
		}
	}
//	初始化暫存翻轉棋
	public void initReversiChess() {
		System.out.println(" -> .initReversiChess()");

		for(int i = 0;i<height;i++) {
			Arrays.fill(reversiChess[i], 0);
		}
	}
//	設定先攻顏色
	public void setFirstAttacker(int color) {
		System.out.println(" -> .setFirstAttacker()");

		Attacker = color;
		setDefender();
	}
//	棋盤初始化
	public void initInfCheckerboard() {
		System.out.println(" -> .initInfCheckerboard()");
		for(int i = 0;i<height;i++) {
			Arrays.fill(infCheckerboard[i], 0);
		}
		infCheckerboard[3][3] = 1;
		infCheckerboard[3][4] = 2;
		infCheckerboard[4][3] = 2;
		infCheckerboard[4][4] = 1;
	}
//	取的棋盤資訊
	public int[][] getInfCheckerboard() {
		System.out.println(" -> .getInfCheckerboard()");

		return infCheckerboard;
	}
//	設定可行走道路
	public void setPath_InfCheckerboard() {
		System.out.println(" -> .setPath_InfCheckerboard()");
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				if(tempInfCheckerboard[i][j] == 3) {
					infCheckerboard[i][j] = tempInfCheckerboard[i][j];
				}
			}
//			Arrays.fill(tempInfCheckerboard[i], 0);
		}
		System.out.println("******************************************");
		printTempInfCheckerboard();
		System.out.println("******************************************");
	}
//	設定暫存可放置位置
	public void setPath_tempPath() {
		System.out.println(" -> .setPath_tempPath()");
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				if(tempInfCheckerboard[i][j] == 3) {
					tempInfCheckerboard[i][j] = tempPath[i][j];
				}
			}
		}
		printTempInfCheckerboard();
	}
//	輸出暫存棋盤資訊
	public void printTempInfCheckerboard() {
		System.out.println(" -> .printTempInfCheckerboard()");
		System.out.println("\t   0  1  2  3  4  5  6  7");
		for(int i = 0;i<height;i++) {
			System.out.print("\t" + i + " " + Arrays.toString(tempInfCheckerboard[i]));
			System.out.println();
		}
	}
//	清除可選位置
	public void clearPath() {
		System.out.println(" -> .clearPath()");
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				if(infCheckerboard[i][j] == 3) {
					infCheckerboard[i][j] = 0;
				}
			}
		}
	}
//	初始化暫存棋盤資訊
	public void initTempInfCheckerboard() {
		System.out.println(" -> .initTempInfCheckerboard()");

		for(int i = 0;i<height;i++) {
			Arrays.fill(tempInfCheckerboard[i], 0);
		}
	}
//	初始化暫存可選道路
	public void initTempPath() {
		System.out.println(" -> .initTempPath()");

		for(int i = 0;i<height;i++) {
			Arrays.fill(tempPath[i], 0);
		}
	}


//	
	public void explore() {
		System.out.println(" -> .explore()");
		

		clearPath();
		printInfCheckerboard();
		int value = 0;
		
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!Serial number: " + value++);
				exploreDirections(i, j, false);
				
			}
		}
	
		setPath_InfCheckerboard();
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		//reversi.printTempInfCheckerboard();		
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
//		initTempInfCheckerboard();
	}
}
