package T2;

import java.awt.BorderLayout;
import java.util.Arrays;

public class Dominator {
//	建立物件
	private Platform platform; //JFrame
	private Board board; //JPanel
//	建立變數
	private int CELL_SIZE;
	private int width;
	private int height;
	private int chessColor; //。0: 空 。1: 白 。2: 黑
//	---
	private int [][]checkerboard; //。0:空 。1:白 。2:黑
//	建構子
	public Dominator() {
//		初始化變數
		CELL_SIZE = 65;
		width = 8;
		height = 8;
		
		chessColor = 1;
		
		checkerboard = new int[height][width];
		
		init();
	}
//	初始化
	private void init() {
//		棋盤初始化
		for(int i = 0;i<height;i++) {
			Arrays.fill(checkerboard[i], 0);
		}
		checkerboard[3][3] = 1;
		checkerboard[3][4] = 2;
		checkerboard[4][3] = 2;
		checkerboard[4][4] = 1;
//		初始化物件
		board = new Board(CELL_SIZE, width, height, checkerboard, chessColor); //JPanel
		platform = new Platform(CELL_SIZE, width, height, checkerboard); //JFrame
//		版面設定
		platform.add(board, BorderLayout.CENTER);
	}
}
