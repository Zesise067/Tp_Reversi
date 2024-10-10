package T6_ReversiCorrect_Error;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Dominator extends JFrame {
//	建立物件
	private Checkerboard checkerboard; //JPanel
	
//	private JPanel jpanelS;
//	private JButton nextButton;
//	private JLabel jlabel;
	
	
//	建立變數
	private int CELL_SIZE; //單元格大小
	private int width;
	private int height;
	
	private int Attacker; //進攻方顏色: 。0: 空 。1: 白 。2: 黑
	
	private int [][]infCheckerboard; //棋盤訊息。0:空 。1:白 。2:黑
//	建構子
	public Dominator() {
		init();
	}
//	初始化
	private void init() {
//		初始化變數
		CELL_SIZE = 65;
		width = 8;
		height = 8;
		
//		Attacker = 1; //白方先攻
		
		infCheckerboard = new int[height][width]; //棋盤訊息。0:空 。1:白 。2:黑
//		棋盤初始化
//		for(int i = 0;i<height;i++) {
//			Arrays.fill(infCheckerboard[i], 0);
//		}
//		infCheckerboard[3][3] = 1;
//		infCheckerboard[3][4] = 2;
//		infCheckerboard[4][3] = 2;
//		infCheckerboard[4][4] = 1;
//		infCheckerboard[][] = ;
		
//		初始化物件
		checkerboard = new Checkerboard(); //JPanel
		
//		jpanelS = new JPanel(true);
//		jlabel = new JLabel();
//		nextButton = new JButton("Next Round"); //下一局

//		jpanelS.setBackground(Color.black);
		
//		版面設定
		add(checkerboard);
//		add(jpanelS, BorderLayout.SOUTH);
//		視窗設定
		setTitle("Reversi 黑白棋");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // 設置不可調整大小
        setSize(width*CELL_SIZE +17, height*CELL_SIZE +77);
//        setSize(width*CELL_SIZE +17, height*CELL_SIZE +77 +36);
        setBackground(Color.black);
//        setLayout(new BorderLayout());
        setVisible(true);    
	}
}
