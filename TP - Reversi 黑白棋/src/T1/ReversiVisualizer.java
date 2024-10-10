package T1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ReversiVisualizer extends JPanel {
	
    private int[][] maze;
    private int[][] fMaze; //finsh Maze
    private boolean[][] TMaze = new boolean[8][8];
    
    private int width;
    private int height;
    private int path = 0; //手動步數
    private String solverPath; //解答步數
    
//    private int number = 0;
    
    private boolean myWay = false; //判斷是否為自己攻略迷宮
    private boolean B_solveMaze = false; //判斷是否點擊solveMaze按鈕
    private boolean Start = true;
    
	private static final int CELL_SIZE = 65; // 单元格大小
    /*
     * 建構子
     */
	public ReversiVisualizer(int[][] maze, int[][] fMaze, int path) {
		this.maze = maze;
		this.fMaze = fMaze;
        this.width = maze[0].length; //x
        this.height = maze.length; //y
        solverPath = String.valueOf(path);
        
//        Arrays.fill(TMaze, true);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	TMaze[y][x] = true;
            }
        }
		
        //(最底)一個自定義的滑鼠監聽器類別，它實現了滑鼠事件的相關方法。
		mazeMouseListener mlistener = new mazeMouseListener();
				//首先，這裡創建了一個名為 mazeMouseListener 的 mazeMouseListener 物件。
				//這個物件是一個自定義的滑鼠監聽器，它實現了滑鼠事件的相關方法。
				//這些方法可以根據滑鼠的行為來觸發相應的事件。
		
		//當滑鼠在元件上進行點擊操作時，mlistener 物件中相應的方法將被調用。
		addMouseListener(mlistener);
				//接下來，使用 addMouseListener 方法將 mazeMouseListener 物件添加為滑鼠監聽器。
				//這意味著當滑鼠在這個元件（可能是一個圖形或遊戲視窗）上進行點擊操作時，
				//mazeMouseListener 物件中相應的方法將被調用。
		
		//當滑鼠在元件上移動時，mlistener 物件中相應的方法將被調用。
		addMouseMotionListener(mlistener);
				//同樣地，使用 addMouseMotionListener 方法將 mazeMouseListener 物件添加為滑鼠移動監聽器。
				//這意味著當滑鼠在元件上移動時，mazeMouseListener 物件中相應的方法將被調用。
		
				//總而言之，這些程式碼用於創建並設置滑鼠監聽器，
				//以便在迷宮遊戲中捕捉滑鼠事件（如點擊和移動），
				//並根據這些事件來執行相應的操作。
		
		Window();
	}
	/*
	 * 啟動視窗
	 */
	public void Window() {
/*
 * 視窗一 --------------------------------------------------------------------
 */
		JFrame frame1 = createWindow("Maze");
		frame1.setSize(new Dimension(width*CELL_SIZE + 17, height*CELL_SIZE + 40)); //.setSize(x, y); 設定大小
		frame1.setLocation(10, 10); //.setLocation(x, y); 設定視窗位置
		frame1.getContentPane().add(this);
			//將這個物件(MazeVisualizer)新增到視窗
			//因為MazeVisualizer有繼承JPanel，因此如同新增到玻璃板一樣
/*
 * 視窗二 --------------------------------------------------------------------
 */
//        JFrame frame2 = createWindow("Function");
//        frame2.setSize(new Dimension(306,170));
//        frame2.setLocation(800, 10);
//        frame2.setLayout(null);
/*
 * JLabel
 */
//        JLabel label1 = new JLabel(); //Short Path: 步數
//        label1.setBounds(10, 130, 270, 30);
//        frame2.add(label1);
//        
//        JLabel label2 = new JLabel(); //字串: Congratulations, Error!
//        frame2.add(label2);
//        
//        JLabel label3 = new JLabel(); //Path: 步數
//        label3.setBounds(10, 150, 270, 30);
//        frame2.add(label3);
/*
 * JButton("11*11")
 */
//        JButton _11 = new JButton("11*11");
//        _11.setBounds(10, 10, 80, 30); //.setBounds(x位置, y位置, x大小, y大小)
//        _11.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(height != 11 && width != 11) {
//					height = width = 11;
//					newMaze();
//					frame1.setSize(new Dimension(width*CELL_SIZE + 16, height*CELL_SIZE + 39)); //.setSize(x, y); 設定大小
//					frame2.setSize(new Dimension(306,170));
//					repaint();
//					System.out.println("MazeVisualizer --> Window --> JButton(\"11*11\")\n Change Size: 11*11");
//				}
//			}
//		});
//		frame2.add(_11);
/*
 * JButton("21*21")
 */
//		JButton _21 = new JButton("21*21");
//		_21.setBounds(105, 10, 80, 30); //.setBounds(x位置, y位置, x大小, y大小)
//		_21.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(height != 21 && width != 21) {
//					height = width = 21;
//					newMaze();
//					frame1.setSize(new Dimension(width*CELL_SIZE + 16, height*CELL_SIZE + 39)); //.setSize(x, y); 設定大小
//					frame2.setSize(new Dimension(306,170));
//					repaint();
//					System.out.println("MazeVisualizer --> Window --> JButton(\"21*21\")\n Change Size: 21*21");
//				}
//			}
//		});
//		frame2.add(_21);
/*
 * JButton("25*25")
 */
//		JButton _25 = new JButton("25*25");
//		_25.setBounds(200, 10, 80, 30); //.setBounds(x位置, y位置, x大小, y大小)
//		_25.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(height != 25 && width != 25) {
//					height = width = 25;
//					newMaze();
//					frame1.setSize(new Dimension(width*CELL_SIZE + 16, height*CELL_SIZE + 39)); //.setSize(x, y); 設定大小
//					frame2.setSize(new Dimension(306,170));
//					repaint();
//					System.out.println("MazeVisualizer --> Window --> JButton(\"21*21\")\n Change Size: 25*25");
//				}
//			}
//		});
//		frame2.add(_25);
/*
 * JButton("Generate Maze")
 */
//        JButton generateMaze = new JButton("Generate Maze");
//        generateMaze.setBounds(10, 50, 130, 30);
//        generateMaze.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				newMaze();
//				frame2.setSize(new Dimension(306,170));
//		        repaint();
//     			System.out.println("MazeVisualizer --> Window --> JButton(\"Generate Maze\")");
//			}
//        });
//        frame2.add(generateMaze);
/*
 * JButton("Solve Maze")
 */
//        JButton solveButton = new JButton("Solve Maze");
//        solveButton.setBounds(150, 50, 130, 30);
//        solveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//				System.out.println("MazeVisualizer --> Window --> JButton(\"Solve Maze\")");
//                frame2.setSize(new Dimension(306,170));
//            	B_solveMaze = true;
//            	maze = fMaze.clone();
//                repaint();
//            }
//        });
//        frame2.add(solveButton);
/*
 * JButton("Finish")
 * 	- 用於手動攻略迷宮，確認答案用
 * 	- 在沒有點擊solveMaze按鈕的情況執行
 */
//        JButton checkMaze = new JButton("Finish");
//        checkMaze.setBounds(10, 90, 270, 30);
//        checkMaze.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//				System.out.println("MazeVisualizer --> Window --> JButton(\"Finish\")");
//
//            	boolean correct = true;
//            	
//            	if(!B_solveMaze) { //判斷是否點擊solveMaze按鈕
//            		frame2.setSize(new Dimension(306,270)); //調整視窗，用於顯示Path(步數)及結果
//            		for(int y = 0;y<height;y++) {
//            			for(int x = 0;x<width;x++){
//            				if(fMaze[y][x] != 4) { //判斷解答是否正確
//            					continue;
//            				}else {
//            					if(maze[y][x] != 4) {
//            						correct = false;
//            					}
//            				}
//            			}
//            		}
//            		label1.setText("Short Path: " + solverPath);
//            		label3.setText("My Path: " + String.valueOf(path));
//            		if(correct) {
//            			label2.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 30)); // 設定字體為 Arial，加粗且斜體字體風格，大小為 30
//            			label2.setBounds(30, 180, 270, 45);
//            			label2.setText("Congratulations");
//            		}else {
//            			label2.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 60)); // 設定字體為 Arial，加粗且斜體字體風格，大小為 30
//            			label2.setBounds(110, 160, 270, 45);
//            			label2.setText("Error!");
//            		}
//            		
//            	}
//            }
//        });
//        frame2.add(checkMaze);
	}
/*
 * 生成心迷宮
 */
	public void newMaze() {
		path = 0; //(重設)步數
        myWay = false; //(重設)判斷是否點擊solveMaze按鈕
        B_solveMaze = false; //(重設)判斷是否為自己攻略迷宮
		
		maze = new int[height][width]; //設定新空間
        
		//生成迷宮
		ReversiGenerator generator = new ReversiGenerator(height, width);
		generator.generateMaze();
		maze = generator.getMaze().clone();
		
		//攻略迷宮
		ReversiSolver mazeSolver = new ReversiSolver(maze);
    	mazeSolver.solveMaze();
    	fMaze = mazeSolver.getSolution().clone();
    	solverPath = String.valueOf(mazeSolver.getPath());
    	
	}
/*
 * 繪製圖形
 */
	/**
	 *
	 */
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //繪製迷宮
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	if(TMaze[y][x]) {
            		int startX = x * CELL_SIZE;
		            int startY = y * CELL_SIZE;
		            
		            if(maze[y][x] == 4) {
		            	g.setColor(Color.black);
		                g.fillRect(startX, startY, CELL_SIZE, CELL_SIZE);
		            	Graphics2D g2d = (Graphics2D) g;
		            	Ellipse2D circle = new Ellipse2D.Double(x*CELL_SIZE +5, y*CELL_SIZE +5, CELL_SIZE -10, CELL_SIZE -10); // (x, y, width, height)
		            	g2d.setColor(Color.blue);
		                g2d.fill(circle);
		                g2d.draw(circle);
		            }else {
		            	Color cellColor = getCellColor(maze[y][x]);
		                g.setColor(cellColor);
		                g.fillRect(startX, startY, CELL_SIZE, CELL_SIZE);
		            }
            	}
            }
        }
        
        //繪製格線
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                g.setColor(Color.green);
				g.drawRect(y * CELL_SIZE, x * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
        
        // 繪製數字
        int value = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	g.setColor(Color.gray);
                g.setFont(new Font("Arial", Font.PLAIN, 50));
                if(value > 9) {
                	String numberString = String.valueOf(value++);
                    g.drawString(numberString, x*CELL_SIZE +5, y*CELL_SIZE +51);
                }else {
                	String numberString = String.valueOf(value++);
                	g.drawString(numberString, x*CELL_SIZE +20, y*CELL_SIZE +51);
                }
                
            }
        }
        
        //遊戲初始化
        if(Start) {
        	//圓形
            Graphics2D g2d = (Graphics2D) g;
            //左上
        	Ellipse2D circle = new Ellipse2D.Double(3*CELL_SIZE +5, 3*CELL_SIZE +5, CELL_SIZE -10, CELL_SIZE -10); // (x, y, width, height)
        	g2d.setColor(Color.white);
            g2d.fill(circle);
            g2d.draw(circle);
            //右上
            circle = new Ellipse2D.Double(3*CELL_SIZE +5, 4*CELL_SIZE +5, CELL_SIZE -10, CELL_SIZE -10); // (x, y, width, height)
        	g2d.setColor(new Color(255, 255, 180));
            g2d.fill(circle);
            g2d.draw(circle);
            //左下
            circle = new Ellipse2D.Double(4*CELL_SIZE +5, 3*CELL_SIZE +5, CELL_SIZE -10, CELL_SIZE -10); // (x, y, width, height)
        	g2d.setColor(new Color(255, 255, 180));
            g2d.fill(circle);
            g2d.draw(circle);
            //右下
            circle = new Ellipse2D.Double(4*CELL_SIZE +5, 4*CELL_SIZE +5, CELL_SIZE -10, CELL_SIZE -10); // (x, y, width, height)
        	g2d.setColor(Color.white);
            g2d.fill(circle);
            g2d.draw(circle);
            Start = !Start;
            TMaze[3][3] = false;
            TMaze[4][3] = false;
            TMaze[3][4] = false;
            TMaze[4][4] = false;
        }
//     // 進行旋轉
//        AffineTransform rotateTransform = new AffineTransform();
//        rotateTransform.rotate(Math.toRadians(45), 0 +5, 0 +5); // 旋轉45度，以(100, 100)為中心點
//        g2d.setTransform(rotateTransform);
//
//        // 繪製旋轉後的矩形
//        g2d.setColor(Color.RED);
//        g2d.fill(circle);
    }
/*
 * 獲取單元格顏色
 */
	private Color getCellColor(int cellValue) {
        switch (cellValue) {
//            case 0: //道路
//                return Color.WHITE;
            case 1: //墙壁
                return Color.black;
//            case 2: //终点
//                return Color.white;
//            case 3: //起点
//                return Color.gray;
//            case 4: //路徑   
//            	return Color.gray; //较淡的黄色
//            case 4: //路徑
//                return new Color(255, 255, 180); //较淡的黄色
            default:
                return Color.black;
        }
    }
/*
 * myWay
 * 	- 轉換鼠標坐標，得到所指的圖形位置
 * 	- 重新繪圖(maze)
 */
	public void myWay(int mx, int my) {
		int x = mx / CELL_SIZE;
		int y = my / CELL_SIZE;
		
//		if(x == 0 && y == 0) { //maze[0][0]為啟動點
//			myWay = true;
//		}
		
//		if(myWay) {
//			if(maze[y][x] == 0) {
//				maze[y][x] = 4;
//				path++;
//				repaint();
//			}
//		}
		
		
		
		if(maze[y][x] == 1) {
			TMaze[y][x] = false;
			maze[y][x] = 4;
//			path++;
			repaint();
		}
	}
/*
 * 主程式
 * 	- 生成及攻略預設迷宮
 * 	- 
 */
	public static void main(String[] args) {
		int width = 8;
		int height = 8;
		int[][] Maze;
		int[][] fMaze;
		int path;
		
		//生成預設迷宮
		ReversiGenerator generator = new ReversiGenerator(height, width);
		generator.generateMaze();
		Maze = generator.getMaze();
		
		//攻略預設迷宮
		ReversiSolver mazeSolver = new ReversiSolver(Maze);
    	mazeSolver.solveMaze();
    	fMaze = mazeSolver.getSolution().clone();
    	path = mazeSolver.getPath();
       
		SwingUtilities.invokeLater(() -> {
			ReversiVisualizer myPanel = new ReversiVisualizer(Maze, fMaze, path);
		});
	}
/*
 * 創造視窗
 * 	- 無大小
 * 	- 無位置
 * 	- 無佈局編輯器
 */
	private static JFrame createWindow(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false); // 設置不可調整大小
        frame.setVisible(true);
        return frame;
    }
/*
 * 迷宮鼠標監聽器
 */
	class mazeMouseListener implements MouseListener, MouseMotionListener {
		private boolean mouseclicked = false; //判斷是否點擊點擊鼠標
		
		//當滑鼠按下按鈕時觸發此事件
		public void mousePressed(MouseEvent e) { 
			mouseclicked = true;
			myWay(e.getX(), e.getY());
				//e.getX(): 取的滑鼠x軸位置
				//e.getY(): 取的滑鼠y軸位置
		}
		
		//當滑鼠釋放按鈕時觸發此事件
		public void mouseReleased(MouseEvent e) {
			mouseclicked = false;
		}
		
		//當滑鼠拖曳時觸發此事件
		public void mouseDragged(MouseEvent e) {
//			if (mouseclicked) {
//				myWay(e.getX(), e.getY());
//			}
		}
		
		//當滑鼠移動時觸發此事件
		public void mouseMoved(MouseEvent e) {
		}
		
		//當滑鼠進入面板時觸發此事件
		public void mouseEntered(MouseEvent e) {
		}
		
		//當滑鼠離開面板時觸發此事件
		public void mouseExited(MouseEvent e) {
			mouseclicked = false;
		}
		
		//當滑鼠點擊時觸發此事件
		public void mouseClicked(MouseEvent e) {
		}
	}
}

//補充說明: SwingUtilities.invokeLater(() -> {};

	//在Java GUI编程中，
	//使用SwingUtilities.invokeLater(() -> {})的时机是在需要更新Swing组件的界面上。
	//
	//Swing是Java的标准GUI工具包，用于创建图形用户界面。在Swing中，
	//所有的GUI组件都应该在事件调度线程(Event Dispatch Thread)上进行操作。
	//这是因为Swing是单线程的，所有的GUI更新都必须在同一个线程中完成，以避免并发访问的问题。
	//
	//SwingUtilities.invokeLater(() -> {})是一个静态方法，
	//它接受一个Runnable对象作为参数。当调用invokeLater时，
	//传递给它的代码块将被封装成Runnable对象，并在事件调度线程上执行。
	//这意味着通过invokeLater方法提交的任务将在事件队列中等待被处理，
	//然后在合适的时机被执行。
	//
	//你应该在以下情况下使用SwingUtilities.invokeLater(() -> {})：
	//	在应用程序启动时初始化GUI组件：
	//		当你的应用程序启动时，需要创建和初始化GUI组件。
	//		这些初始化操作应该在事件调度线程中完成，以确保界面的正确显示。
	//		你可以使用SwingUtilities.invokeLater(() -> {})来封装初始化代码，
	//		以便在事件调度线程上执行。
	//		
	//	在事件处理方法中更新GUI组件：
	//		当你在响应某个事件时需要更新GUI组件，例如按钮点击事件、菜单选择事件等，
	//		你应该在事件处理方法内部使用SwingUtilities.invokeLater(() -> {})来更新相关的GUI组件。
	//		这样可以确保更新操作在事件调度线程中执行，避免与其他GUI操作的并发访问问题。
	//
	//总之，
	//SwingUtilities.invokeLater(() -> {})的使用时机是在需要更新Swing组件的界面上，
	//包括初始化GUI组件和在事件处理方法中更新GUI组件。它确保了GUI操作在事件调度线程中执行，
	//避免了并发访问问题，并提供了良好的用户界面响应性。

	//	优点：
	//		线程安全：
	//			Swing组件是非线程安全的，意味着在多线程环境下直接更新GUI组件可能导致并发访问问题。
	//			SwingUtilities.invokeLater(() -> {})确保所有GUI更新都在事件调度线程上执行，
	//			避免了并发访问问题，提供了线程安全的GUI操作。
	//		
	//		避免阻塞事件调度线程：
	//			事件调度线程负责处理GUI事件和刷新界面，如果长时间的任务直接在事件调度线程上执行，
	//			会导致界面无响应或卡顿。
	//			使用SwingUtilities.invokeLater(() -> {})将任务放入事件队列中，
	//			确保它们在合适的时机被执行，避免了阻塞事件调度线程，保持了界面的流畅性和响应性。
	//		
	//		规范代码结构：
	//			使用SwingUtilities.invokeLater(() -> {})可以将GUI更新的代码块封装成Runnable对象，
	//			提高了代码的可读性和可维护性。它也使得GUI更新的操作变得明确，易于跟踪和理解。
	//	
	//	缺点：
	//	可能引起延迟：
	//		使用SwingUtilities.invokeLater(() -> {})将任务放入事件队列中，
	//		而不是立即执行。这意味着任务的实际执行时间取决于事件队列中其他任务的数量和优先级。
	//		如果事件队列中有大量任务等待执行，可能会导致任务的延迟执行。
	//	
	//	不适用于耗时操作：
	//		SwingUtilities.invokeLater(() -> {})适用于轻量级的GUI更新操作，
	//		但不适合执行耗时的任务。如果任务需要大量计算或涉及阻塞I/O操作，
	//		将其放入事件队列中可能会导致界面的卡顿和不响应。对于这些情况，
	//		应考虑使用多线程或后台任务来执行任务，以保持界面的流畅性。
	//
	//综上所述，SwingUtilities.invokeLater(() -> {})提供了一种安全、规范的方式来更新Swing组件，
	//并确保在事件调度线程上执行。它在大多数情况下是很有用的，特别是对于轻量级的GUI操作。
	//但在处理耗时操作或需要即时响应的情况下，可能需要考虑其他的执行方式。
