package T2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Platform extends JFrame {
//	建立變數
	private int CELL_SIZE;
	private int width;
	private int height;
//	---
	private int [][]checkerboard;
//	建立物件
//	---
	private JPanel northbutton = new JPanel(true);
	private JButton pvpButton = new JButton("PVP"); //玩家對弈
	private JButton pvcButton = new JButton("PVC"); //對戰電腦
	private JButton cvcButton = new JButton("CVC"); //模擬演練
//	---
	private JPanel southbutton = new JPanel(true);
	private JButton hintButton = new JButton("Hint"); //提示
	private JButton historyButton = new JButton("History"); //歷史紀錄
	private JButton nextButton = new JButton("Next Round"); //下一局
//	建構子
	public Platform(int CELL_SIZE, int width, int height, int[][] checkerboard) {
		this.CELL_SIZE = CELL_SIZE;
		this.width = width;
		this.height = height;
		this.checkerboard = checkerboard;
//		主視窗的基本設定
//		super("Reversi 黑白棋"); //用來設定新建立的 JFrame 物件的標題(title)，其為主要視窗。

		
		init();
	}
//	初始化
	private void init() {
		createWindow(width*CELL_SIZE +17, height*CELL_SIZE +77 +36);
		
		northbutton.setBackground(Color.black);
		southbutton.setBackground(Color.black);
		
		northbutton.add(pvpButton);
		northbutton.add(pvcButton);
		northbutton.add(cvcButton);
		southbutton.add(hintButton);
		southbutton.add(historyButton);
		southbutton.add(nextButton);
		
		add(northbutton, BorderLayout.NORTH);
		add(southbutton, BorderLayout.SOUTH);
//		createWindow();
		
//		GS.setPreferredSize(new Dimension(width*CELL_SIZE, height*CELL_SIZE));
//		GS.setPreferredSize(new Dimension(200, 300));
		
//		create the action listener for the radio buttons
//		nodeSelecterListener nodelistener = new nodeSelecterListener();

//		create the node slecter radio buttons
//		buttonpath.addActionListener(nodelistener);
//		buttonwall.addActionListener(nodelistener);
//		buttonstart.addActionListener(nodelistener);
//		buttonfinish.addActionListener(nodelistener);

//		create a group for the radio buttons
//		buttonselecter = new ButtonGroup();
//		buttonselecter.add(buttonwall);
//		buttonselecter.add(buttonpath);
//		buttonselecter.add(buttonstart);
//		buttonselecter.add(buttonfinish);
//		
//		buttonpanel1.setBackground(Color.black);
//		buttonpanel2.setBackground(Color.black);
//		add stuff to button panel
//		buttonpanel1.add(pvpButton);
//		buttonpanel1.add(pvcButton);
//		buttonpanel1.add(cvcButton);
//		buttonpanel2.add(nextButton);
//		buttonpanel2.add(hintButton);
//		buttonpanel2.add(historyButton);
//		buttonpanel.add(buttonpath);
//		buttonpanel.add(buttonwall);
//		buttonpanel.add(buttonstart);
//		buttonpanel.add(buttonfinish);

		
//		createWindow(width*CELL_SIZE +17, height*CELL_SIZE +77 +36);
		
//		add(GS, BorderLayout.CENTER);
//		add(buttonpanel1, BorderLayout.NORTH);
//		add(buttonpanel2, BorderLayout.SOUTH);
	}
//	建立視窗
	public void createWindow(int width, int height) {
		setTitle("Reversi 黑白棋");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // 設置不可調整大小
        setSize(width, height);
//        getContentPane().setBackground(Color.black);
        setBackground(Color.black);
        setLayout(new BorderLayout());
        setVisible(true);
    }
//	取的鼠標位置
	public void getTheMousePosition(int mX, int mY) { //mX、mY: 滑鼠座標
		int cX = mX / CELL_SIZE; //cX、cY: 棋盤座標
		int cY = (mY -60) / CELL_SIZE;
		System.out.printf("%d  %d  %d  %d\n",mX,mY,cX,cY);
//		if(j.judge(cX, cY)) {
//			j = new Re(cX, cY);
//		}
	}
//	sets all selecter nodes to false
//	public void allFalse() {
//		path = false;
//		wall = false;
//		start = false;
//		end = false;
//	}
//	the action listener for the radio buttons
//	class nodeSelecterListener implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			if (e.getSource() == buttonpath) {
//				allFalse();
//				path = true;
//			} else if (e.getSource() == buttonwall) {
//				allFalse();
//				wall = true;
//			} else if (e.getSource() == buttonstart) {
//				allFalse();
//				start = true;
//			} else if (e.getSource() == buttonfinish) {
//				allFalse();
//				end = true;
//			}
//		}
//	}

}
