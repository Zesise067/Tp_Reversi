package T8_ReversiCorrect;

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
	
	int[][] infCheckerboard = new int[8][8]; //棋盤上資料
	int[][] tempInfCheckerboard = new int[8][8];
	private int[][] reversiChess = new int[8][8];
	
	boolean RST = false; //判斷是否有棋子可以翻轉 0: 沒有 1:有
	boolean GameOver = true; //判斷遊戲是否結束 0:繼續 1:遊戲結束---------------------------
	
//	String Winner = "";
	
	int quantityAttacker = 0;
	int quantityDefender = 0;
	
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
	public void exploreDirections(int x, int y) {
		System.out.println(" -> .exploreDirections()");
//		tempInfCheckerboard = infCheckerboard.clone();
//		夾角系列
		if(x==0 && y==0) { //左上角
			exploreStart(x, y, 5);
			exploreStart(x, y, 7);
			exploreStart(x, y, 8);
		}else if(x==7 && y==0) { //右上角
			exploreStart(x, y, 4);
			exploreStart(x, y, 6);
			exploreStart(x, y, 7);
		}else if(x==0 && y==7) { //左下角
			exploreStart(x, y, 2);
			exploreStart(x, y, 3);
			exploreStart(x, y, 5);
		}else if(x==7 && y==7) { //右下角
			exploreStart(x, y, 1);
			exploreStart(x, y, 2);
			exploreStart(x, y, 4);
		}
//		邊緣系列
		else if(x==0 || x==1) { //左邊、中心例外
//			邊緣例外
			if(y==0 || y==1) {
				exploreStart(x, y, 7);
				exploreStart(x, y, 8);
			}else if(y==6 || y==7) {
				exploreStart(x, y, 2);
				exploreStart(x, y, 3);
			}else {
				exploreStart(x, y, 2);
				exploreStart(x, y, 3);
				exploreStart(x, y, 7);
				exploreStart(x, y, 8);
			}
			exploreStart(x, y, 5);
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
		}
//		中心
		else {
			for(int i = 1;i<9;i++) {
				exploreStart(x, y, i);
				System.out.println("\tFinish case " + i);
			}
		}
		
//		tempInfCheckerboard = infCheckerboard.clone();
	}
//	探索開始
	public void exploreStart(int x, int y, int tryDirections) { //exploreStart: 探索開始
		System.out.println(" -> .exploreStart()");
		
//		建立變數
		int tX; //tX、tY: 暫存黑白棋位置
		int tY;
		int minValue;
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
//				第一次判斷左上是否有棋子
				if(infCheckerboard[tY][tX] != Defender) {
					System.out.println("1");
					
					break;
				}
				
				for(int j = 0;j<minValue;j++) {
					if(j == minValue-1) {
						if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
														
							break;
						}
					}else {
					
						if(infCheckerboard[tY][tX] == Defender) {
							System.out.println("2");
							reversiChess[tY][tX] = Defender;
							tX--;
							tY--;
						}else if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 1");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}
				}
			break;
//			上中
			case 2:
				System.out.println("\tcase 2");
				tX = x;
				tY = y-1; 
				
				if(infCheckerboard[tY][tX] != Defender) {
					System.out.println("1");
						
					break;
				}
				
				while(tY >= 0) {
					if(tY == 0) {
						if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}else {
						if(infCheckerboard[tY][tX] == Defender) {
							System.out.println("2");
							reversiChess[tY][tX] = Defender;
							tY--;
						}else if(infCheckerboard[tY][tX] == Attacker) { //--------------------------------
							System.out.println("\tcase 2");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
//							System.out.println("***********------------------++++++++++++++++" + GameOver);
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
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
				
				if(infCheckerboard[tY][tX] != Defender) {
					System.out.println("1");
						
					break;
				}
				
				for(int j = 0;j<minValue;j++) {
					if(j == minValue-1) {
						if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}else {
						if(infCheckerboard[tY][tX] == Defender) {
							System.out.println("2");
							reversiChess[tY][tX] = Defender;
							tX++;
							tY--;
						}else if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}
				}
			break;
//				中左
			case 4:
				System.out.println("\tcase 4");
				tX = x -1;
				tY = y;
				
				if(infCheckerboard[tY][tX] != Defender) {
					System.out.println("1");
						
					break;
				}
				
				while(tX >= 0) {
					if(tX == 0) {
						if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}else {
					
						if(infCheckerboard[tY][tX] == Defender) {
							System.out.println("2");
							reversiChess[tY][tX] = Defender;
							tX--;
						}else if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 4");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}
				}
			break;
//			中右
			case 5:
				System.out.println("\tcase 5");
//				System.out.println("\tAttacker: " + Attacker + "\n\tDefender: " + Defender); //----------------
				tX = x +1;
				tY = y;
				
				if(infCheckerboard[tY][tX] != Defender) {
					System.out.println("1");
						
					break;
				}
				
				while(tX <= 7) {
					if(tX == 7) {
						if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}else {
					
						if(infCheckerboard[tY][tX] == Defender) {
							System.out.println("2");
							reversiChess[tY][tX] = Defender;
//							printReversiChess(); //-------------------
							tX++;
						}else if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 5");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
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
				
				if(infCheckerboard[y+1][x-1] != Defender) {
					System.out.println("1");
						
					break;
				}
				
				for(int j = 0;j<minValue;j++) {
					if(j == minValue-1) {
						if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}else {
						if(infCheckerboard[tY][tX] == Defender) {
							System.out.println("2");
							reversiChess[tY][tX] = Defender;
							tX--;
							tY++;
						}else if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 6");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}
				}

			break;
//				下中
			case 7:
				System.out.println("\tcase 7");
				tX = x;
				tY = y +1;
	
				if(infCheckerboard[tY][tX] != Defender) {
					System.out.println("1");
						
					break;
				}
				
				while(tY <= 7) {
					if(tY == 7) {
						if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}else {
						if(infCheckerboard[tY][tX] == Defender) {
							System.out.println("2");
							reversiChess[tY][tX] = Defender;
							tY++;
						}else if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 7");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
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
				
				if(infCheckerboard[y+1][x+1] != Defender) {
					System.out.println("1");
						
					break;
				}
				
				for(int j = 0;j<minValue;j++) {
					if(j == minValue-1) {
						if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 3");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
					}else {
						if(infCheckerboard[tY][tX] == Defender) {
							System.out.println("2");
							reversiChess[tY][tX] = Defender;
							tX++;
							tY++;
						}else if(infCheckerboard[tY][tX] == Attacker) {
							System.out.println("\tcase 8");
							System.out.println("3");
							infCheckerboard[y][x] = Attacker;
							reversiColor(); //探索開始
							RST = true;
							GameOver = false;
							break;
						}else {
							System.out.println("4");
							System.out.println("\tCall initReversiChess()");
							initReversiChess();
								
							break;
						}
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
				if(reversiChess[i][j] == Defender) {
					infCheckerboard[i][j] = Attacker;
				}
				
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
		System.out.println("\t    0  1  2  3  4  5  6  7");
		for(int i = 0;i<height;i++) {
			System.out.print("\t" + i + "  " + Arrays.toString(reversiChess[i])); //-------------------
			
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
//	輸出站存棋盤上的值
	public void printTempInfCheckerboard() {
		System.out.println(" -> .printTempInfCheckerboard()");
		System.out.println("\t   0  1  2  3  4  5  6  7");
		for(int i = 0;i<height;i++) {
			System.out.print("\t" + i + " " + Arrays.toString(tempInfCheckerboard[i]));
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
//		infCheckerboard[][] = ;
//		infCheckerboard[7][0] = 0;
//		infCheckerboard[0][0] = 1;
//		infCheckerboard[0][1] = 1;
//		infCheckerboard[0][2] = 1;
//		infCheckerboard[0][3] = 1;
//		infCheckerboard[0][4] = 1;
//		tempInfCheckerboard = infCheckerboard.clone();
	}
//	取的棋盤資訊
	public int[][] getInfCheckerboard() {
		System.out.println(" -> .getInfCheckerboard()");
//		設定還原暫存棋盤資訊
//		tempInfCheckerboard = infCheckerboard.clone();
		
		return infCheckerboard;
	}
//	取的翻轉旗標
	public boolean getRST() {
		return RST;
	}
//	重製翻轉旗標
	public void initRST() {
		RST = false;
	}
//	取的結束旗標
	public boolean getGameOver() {
		return GameOver;
	}
//	初始化結束旗標
	public void initGameOver() {
		GameOver = true;
	}
//	判斷遊戲是否結束
	public void judgeGameOver() {
		System.out.println(" -> .judgeGameOver()");

		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
//				計算棋數
				if(infCheckerboard[i][j] == Defender) {
					quantityDefender++;
				}
//				計算棋數
				if(infCheckerboard[i][j] == Attacker) {
					quantityAttacker++;
//					GameOver = false;
				}
//				棋盤上沒有空位
				if(infCheckerboard[i][j] == 0) {
					GameOver = false;
				}
			}
		}
	}
//	取得贏家
//	public String getWinner() {
//		return Winner;
//	}
//	取的目前攻擊方棋數
	public int getQuantityAttacker() {
		return quantityDefender;
	}
//	取的目前防守方棋數
	public int getQuantityDefender() {
		return quantityDefender;
	}
//	判斷是否將死
	public void judgeCheckmate() {
		System.out.println(" ->.judgeCheckmate()");
		
		int value = 0;
		printInfCheckerboard();
		printTempInfCheckerboard();
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				System.out.print("Round: " + value++ + "\t");
//						if(value>18 && value<47) {
//							System.out.println("B.C");
//							printInfCheckerboard();
//						}		
//						if(value>18 && value<47) {
//							System.out.println("A.C");
//							printInfCheckerboard();
//						}
////				-----------				
				exploreDirections(i, j);
////				-----------	
////				-----------
				infCheckerboard = tempInfCheckerboard.clone();
////				-----------					
//						if(value>18 && value<47) {
//							System.out.println("A.E");
//							printInfCheckerboard();
//						}
//				System.out.print(tempInfCheckerboard[i][j]);
			}
			System.out.println();
		}
		int number = 0;
		for(int i = 0;i<height;i++) {
			for(int j = 0;j<width;j++) {
				System.out.print("\t" + number++);
			}
			System.out.println();
		}
		printInfCheckerboard();
		printTempInfCheckerboard();
	}
//	設定還原暫存棋盤資訊
//	public void setTempInfCheckerboard(int[][] Infcheckerboard) {
//		tempInfCheckerboard = Infcheckerboard;
//	}
}
/*
 * 遊戲結束條件 - 死棋
 */
