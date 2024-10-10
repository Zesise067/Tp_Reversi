package T9_AlmostDone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Dominator {
//extends JFrame {
//	建立物件
	private Checkerboard checkerboard = new Checkerboard(); //JPanel
	private JFrame dominator = new JFrame();
//	建立變數
	private int CELL_SIZE = 65; //單元格大小
	private int width = 8;
	private int height = 8;
	
	private int buttonWidth = 120;
	private int buttonHeight = 50;
	
	private int buttonFontSize = 17;
	
	private JLabel labelAttacker; //目前進攻方
	private JLabel labelBlack; //目前黑棋數
	private JLabel labelWhite; //目前白棋數
	private JButton buttonRegretChess; //悔棋
	private JButton buttonHint; //提示
	private JButton buttonSurrender; //投降
	private JButton buttonSubstitute; //代打
	private JButton buttonNext; //下一局
	private JButton creator; //開發者模式
	
	private JPanel panelEAST;
	private JPanel panelInEAST;
	
	private JRadioButton buttonPvP;
	private JRadioButton buttonPvC;
	private JRadioButton buttonCvC;
	private ButtonGroup buttonSelecter;
//	建構子
	public Dominator() {		
		init();
	}
//	初始化
	private void init() {
//		區塊初始化
		panelEAST = new JPanel(true);
		panelInEAST = new JPanel(true);
		
		panelEAST.setLayout(new BoxLayout(panelEAST, BoxLayout.Y_AXIS));
		
		panelEAST.setBackground(Color.black);
		panelInEAST.setBackground(Color.black);
		
		panelInEAST.setPreferredSize(new Dimension(130, 80));
//		單選按鈕初始化
		radioSelecterListener radioListener = new radioSelecterListener();

		buttonPvP = new JRadioButton("PvP", true);
		buttonPvC = new JRadioButton("PvC", false);
		buttonCvC = new JRadioButton("CvC", false);
		buttonSelecter = new ButtonGroup();
		
		buttonPvP.addActionListener(radioListener);
		buttonPvC.addActionListener(radioListener);
		buttonCvC.addActionListener(radioListener);
		
		buttonPvP.setForeground(Color.white);		
		buttonPvC.setForeground(Color.white);		
		buttonCvC.setForeground(Color.white);
		
		buttonPvC.setBackground(Color.black);
		buttonPvP.setBackground(Color.black);
		buttonCvC.setBackground(Color.black);
		
		buttonSelecter.add(buttonPvC);
		buttonSelecter.add(buttonPvP);
		buttonSelecter.add(buttonCvC);
//		標籤初始化
		labelAttacker = new JLabel("  Attacker:");
		labelBlack = new JLabel("          - Black: 2");
		labelWhite = new JLabel("          - White: 2");
		
		labelAttacker.setForeground(Color.white);
		labelBlack.setForeground(Color.white);
		labelWhite.setForeground(Color.white);

		labelAttacker.setFont(new Font("Label.font", Font.PLAIN, 18));
		labelBlack.setFont(new Font("Label.font", Font.PLAIN, 15));
		labelWhite.setFont(new Font("Label.font", Font.PLAIN, 15));
		
		labelAttacker.setPreferredSize(new Dimension(130, 50));
		labelBlack.setPreferredSize(new Dimension(130, 20));
		labelWhite.setPreferredSize(new Dimension(130, 30));
//		按鈕初始化
		buttonRegretChess = new JButton("悔棋"); //悔棋 Regret Chess 
		buttonHint = new JButton("提示"); //提示 Hint
		buttonSurrender = new JButton("投降"); //投降 Surrender
		buttonSubstitute = new JButton("代打"); //代打 Substitute
		buttonNext = new JButton("下一局"); //下一局 Next Round
		creator = new JButton(); //開發者模式
		
		buttonRegretChess.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		buttonHint.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		buttonSurrender.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		buttonSubstitute.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		buttonNext.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		creator.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		
		buttonRegretChess.setFont(new Font("Label.font", Font.PLAIN, buttonFontSize));
		buttonHint.setFont(new Font("Label.font", Font.PLAIN, buttonFontSize));
		buttonSurrender.setFont(new Font("Label.font", Font.PLAIN, buttonFontSize));
		buttonSubstitute.setFont(new Font("Label.font", Font.PLAIN, buttonFontSize));
		buttonNext.setFont(new Font("Label.font", Font.PLAIN, buttonFontSize));
		creator.setFont(new Font("Label.font", Font.PLAIN, buttonFontSize));
		
		buttonRegretChess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				
			}
		});
		
		buttonHint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				
			}
		});
		
		buttonSurrender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				
			}
		});
		
		buttonSubstitute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				
			}
		});
		
		buttonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				
			}
		});
		
		creator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Auto-generated method stub
				
			}
		});
		
		panelInEAST.add(labelAttacker);
		panelInEAST.add(labelBlack);
		panelInEAST.add(labelWhite);
		
		panelInEAST.add(buttonRegretChess);
		panelInEAST.add(buttonHint);
		panelInEAST.add(buttonSurrender);
		panelInEAST.add(buttonSubstitute);
		panelInEAST.add(buttonNext);
		panelInEAST.add(creator);

		panelEAST.add(panelInEAST);
		
		panelEAST.add(buttonPvP);
		panelEAST.add(buttonPvC);
		panelEAST.add(buttonCvC);
		
		dominator.add(panelEAST, BorderLayout.EAST);
//		平行執行續
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while(true) {
						Thread.sleep(100);
						labelAttacker.setText("  Attacker： " + checkerboard.changeAttacker());
						labelBlack.setText("          - Black: " + checkerboard.reversi.getQuantityBlack());
						labelWhite.setText("          - White: " + checkerboard.reversi.getQuantityWhite());
					}
				}catch(InterruptedException e) {
					
				}
			}
		});
		
		thread.start();
//		物件加入視窗
		dominator.setLocation(50, 50);
		dominator.add(checkerboard, BorderLayout.CENTER);
//		視窗設定
		dominator.setTitle("Reversi 黑白棋");
		dominator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dominator.setResizable(false); // 設置不可調整大小
		dominator.setSize(width*CELL_SIZE +17 +130, height*CELL_SIZE +77 -37);
//        setSize(width*CELL_SIZE +17, height*CELL_SIZE +77 +36);
		dominator.setBackground(Color.black);
//        setLayout(new BorderLayout());
		dominator.setVisible(true);    
	}
//	
	class radioSelecterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonPvP) {
				
			}else if(e.getSource() == buttonPvC) {
				
			}else if(e.getSource() == buttonCvC) {
				
			}
		}
	}
}
