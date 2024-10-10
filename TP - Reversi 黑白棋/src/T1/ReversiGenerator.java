package T1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReversiGenerator {
	
    private int width; //迷宮寬度
    private int height; //迷宮高度
    private int[][] maze; //迷宮[y][x]
    private boolean[][] visited; //判斷是否拜訪[y][x]
    private int[][] directions = {{-1, 0, 1}, {1, 0, 2}, {0, -1, 3}, {0, 1, 4}};
    					// 行走方向[y][x]: 1.上  2.下  3.左  4.右四个方向
    /*
     * 建構子
     */
    public ReversiGenerator(int height, int width) {
        this.width = width;
        this.height = height;
        maze = new int[height][width];
        visited = new boolean[height][width];
    }
    /*
     * 生成迷宮
     */
    public void generateMaze() {
    	initialize(); //初始化迷宫
//        DFS(0, 0); //深度優先搜索法 //起點位置(y, x)
//        
//        //輸出maze
////        System.out.println("MazeGenerator --> generateMaze\nMaze");
////        for (int j = 0; j < height; j++) { //y軸
////			for (int i = 0; i < width; i++) { //x軸
////					System.out.print(maze[j][i]);
////			}
////			System.out.println();
////		}
////		System.out.println();
//		
//        //檢測_迷宮是否正確_錯誤假設maze[height-1][width-1] = 1
//        if(maze[height-1][width-1] == 1) {
//        	generateMaze();
//        	
//        	//輸出maze
////        	System.out.println("MazeGenerator --> generateMaze\nNew Maze");
////        	for (int j = 0; j < height; j++) { //y軸
////    			for (int i = 0; i < width; i++) { //x軸
////    					System.out.print(maze[j][i]);
////    			}
////    			System.out.println();
////    		}
////    		System.out.println();
//		}
        
        //標註
//    	maze[3][3] = 3;
//    	maze[3][4] = 2;
//    	maze[4][3] = 2;
//        maze[4][4] = 3;
//        maze[0][0] = 3; //標記起點
//        maze[height-1][width-1] = 2; //標記終點
    }
    /*
     * 初始化迷宫
     */
    private void initialize() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                maze[y][x] = 1; // 将所有单元格初始化为墙壁(1)
                visited[y][x] = false; //將所有拜訪點出使化為未拜訪(false)
            }
        }
    }
    /*
     * 深度優先搜索法
     * 
     * 	   當使用DFS演算法時，我們從起始節點開始，沿著一條路徑一直遍歷到最深處。
     * 	當遇到一個節點時，我們首先檢查是否已經訪問過。如果尚未訪問，我們會標記它
     * 	為已訪問並執行相應的操作。然後，我們從該節點的相鄰節點中選擇一個尚未訪問
     * 	過的節點，將其放入堆疊中，並以該節點作為新的起點進行DFS遞迴搜索。當所有
     * 	相鄰節點都已訪問過時，我們從堆疊中彈出節點，回溯到上一個節點並繼續遍歷其
     * 	他分支。
     */
    private void DFS(int y, int x) {
        visited[y][x] = true; //設定已拜訪點
        maze[y][x] = 0; //設定道路(0)
        
        // 随机打乱四个方向
        List<int[]> directionsList = Arrays.asList(directions);
	    	/*
	    	 * - Arrays.asList(): 可以將一個陣列轉換成List對象。
	    	 * - 使用 Arrays.asList(directions) 將 directions 轉換為 List<int[]> 時，
	    	 * 		結果會是一個包含四個元素的列表，每個元素都是一個 int[] 陣列。
	    	 */
        Collections.shuffle(directionsList);
	    	/*
	    	 * - 使用Java的Collections類別的shuffle()方法來隨機重新排列directionsList中的元素。
	    	 * - 這將改變directionsList中元素的順序，使其成為一個隨機順序的列表。
	    	 * 		directions = {{-1, 0, 1}, {1, 0, 2}, {0, -1, 3}, {0, 1, 4}};
	    	 * 			-----> = {{0, -1, 3}, {0, 1, 4}, {1, 0, 2}, {-1, 0, 1}};
	    	 */
        
        //輸出directionsList
//        System.out.println("MazeGenerator --> DFS\n directionsList");
//        for (int[] directions : directionsList) {
//	        /*
//	         * - 遍歷 directionsList1 中的每個元素，並將其暫存在 directions 中。
//	         * - directions 是一個 int[] 陣列，表示 directionsList1 中的一個元素。
//	         */
//        	System.out.print("  ");
//    	    for (int direction : directions) {
//    	    	/*
//    	    	 * - 遍歷 directions 陣列中的每個元素，並將其暫存在 direction 中。
//    	    	 * - direction 是一個整數，表示 directions 陣列中的一個元素。
//    	    	 */
//    	        System.out.print(direction + " ");
//    	    }
//    	    System.out.println();
//    	}
        /*
         * - ???迴圈執行八次是因為directionsList1這個列表中有八個元素
         * - ???迴圈執行四次是因為從directions為二維陣列中有四個元素
         */
        
        //计算下一个要访问的单元格
        for (int[] direction : directionsList) {
        	/*
        	 * visited[][]
        	 * 		長寬為偶數		長寬為奇數
        	 * 		1 0 1 0		1 0 1 0 1
        	 * 		0 0 0 0		0 0 0 0 0
        	 * 		1 0 1 0 	1 0 1 0 1
        	 * 		0 0 0 0
        	 */
            int nextX = x + direction[1] * 2; // 计算下一个要访问的单元格的横坐标
            int nextY = y + direction[0] * 2; // 计算下一个要访问的单元格的纵坐标

            // 判断下一个单元格是否在迷宫范围内且未被访问过
            if ((nextX >= 0 && nextX < width) && (nextY >= 0 && nextY < height) && !visited[nextY][nextX]) {
                maze[y + direction[0]][x + direction[1]] = 0; // 将当前单元格和下一个单元格之间的墙壁打通
                
                //輸出visited及maze
//                System.out.println("MazeGenerator --> DFS\n Maze\t Visited");
//				for (int j = 0; j < height; j++) { //y軸
//					for(int r = 0;r<2;r++) { //功能選擇
//						System.out.print(" ");
//						for (int i = 0; i < width; i++) { //x軸
//						switch(r) { //選擇輸出visited還是maze
//							case 0:
//								System.out.print(maze[j][i]);
//								break;
//							case 1:
//								if(visited[j][i]) {
//									System.out.print(1);
//								}else {
//									System.out.print(0);
//								}
//								break;
//							}
//						}
//						System.out.print("\t");
//					}
//					System.out.println();
//				}
//                System.out.println();
                
                DFS(nextY, nextX); // 遞迴访问下一个单元格
            }
        }
    }
    /*
     * 回傳迷宮
     */
    public int[][] getMaze() {
        return maze;
    }
    /*
     * 主程式
     */
    public static void main(String[] args) {
        int width = 5; //迷宮寬度
        int height = 5; //迷宮高度
        int[][] maze; //迷宮
        ReversiGenerator generator = new ReversiGenerator(height, width);
        generator.generateMaze();
    }
}

/*
 * Maze[y][x]
 * 		  ----------> x
 * 		| *************
 * 		v *************
 * 		y *************
 */

/*
 * visited[][]
 * 		長寬為偶數		長寬為奇數
 * 		1 0 1 0		1 0 1 0 1
 * 		0 0 0 0		0 0 0 0 0
 * 		1 0 1 0 	1 0 1 0 1
 * 		0 0 0 0
 */

/*
 * directions[y][x] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 
 * 				行走方向:	上	     下		 左		  右四个方向
 * 		[0][1][2][3][4]....
 * [0]  		{1, 0}下
 * [1]	   			|
 * [3]{0, -1}左 ---------- {0, 1}右
 * [4]				|
 * 	.			{-1, 0}上
 *  .
 */

/*
 * Problems with MazeGenerator
 * 		- 無法生成長寬為偶數迷宮
 * 			- DFS
 *  			int nextX = x + direction[1] * 2; // 计算下一个要访问的单元格的横坐标
 *  			int nextY = y + direction[0] * 2; // 计算下一个要访问的单元格的纵坐标
 *					
 *				if ((nextX >= 0 && nextX < width) && (nextY >= 0 && nextY < height) && !visited[nextY][nextX]) {
 *					maze[y + direction[0]][x + direction[1]] = 0; // 将当前单元格和下一个单元格之间的墙壁打通
 *				}
 *				迷宮長寬若為偶數，visited將無法走訪，造成無法打通牆壁，因此右邊和下面的邊必為牆壁
 */
