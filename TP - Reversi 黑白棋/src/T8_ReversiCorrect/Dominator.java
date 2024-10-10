package T8_ReversiCorrect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dominator extends JFrame {
//	建立物件
	private Checkerboard checkerboard = new Checkerboard();; //JPanel
//	建立變數
	private int CELL_SIZE = 65; //單元格大小
	private int width = 8;
	private int height = 8;
	
	private JLabel jlabel = new JLabel();
	private JButton nextButton = new JButton("Next Round"); //下一局
	private JButton nextButton2 = new JButton("Next Round2"); //下一局

	private JButton nextButton3 = new JButton("Next Round3"); //下一局
//	建構子
	public Dominator() {
		init();
	}
//	初始化
	private void init() {
//		JPanel FunctionNORTH = new JPanel(new FlowLayout()); //功能視窗
//		JPanel FunctionEAST = new JPanel(new FlowLayout()); //功能視窗
////		FunctionEAST.setLayout(new BoxLayout(FunctionEAST, BoxLayout.X_AXIS));
//		JPanel FunctionSOUTH = new JPanel(new BorderLayout()); //功能視窗
//		JPanel FunctionIN = new JPanel(); //功能視窗
//		FunctionIN.setLayout(new BoxLayout(FunctionIN, BoxLayout.Y_AXIS));
//		
//		nextButton2.setBounds(50, 50, 100, 30);
//		
//		jlabel.setText("Attacker： " + "黑" + "                                                           "); //---------------------------------
//		jlabel.setFont(new Font("Label.font", Font.PLAIN, 20));
//		jlabel.setForeground(Color.white);
//		
////		FunctionSOUTH.add(jlabel);		
//		FunctionIN.add(nextButton);	
//		FunctionIN.add(nextButton2);
//		FunctionIN.add(nextButton3);
//		FunctionEAST.add(FunctionIN);
//		FunctionIN.add(jlabel);
//		FunctionIN.add(nextButton3);
//		FunctionEAST.add(FunctionIN);
//		物件加入視窗
//		add(FunctionNORTH, BorderLayout.NORTH);
//		add(FunctionEAST, BorderLayout.EAST);
		add(checkerboard, BorderLayout.CENTER);
//		add(FunctionSOUTH, BorderLayout.SOUTH);
//		視窗設定
		setTitle("Reversi 黑白棋");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false); // 設置不可調整大小
        setSize(width*CELL_SIZE +17, height*CELL_SIZE +77);
//        setSize(width*CELL_SIZE +17, height*CELL_SIZE +77 +36);
        setBackground(Color.black);
//        setLayout(new BorderLayout());
        setVisible(true);    
	}
}
