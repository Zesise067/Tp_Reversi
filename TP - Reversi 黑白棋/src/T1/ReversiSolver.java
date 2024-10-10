package T1;

public class ReversiSolver {
	
	private int height;
	private int width;
    private int[][] maze;
    private int[][] solution;
    private int path;
    /*
     * 建構子
     */
    public ReversiSolver(int[][] maze) {
    	height = maze.length;
    	width = maze[0].length;
        this.maze = maze;
        this.solution = new int[height][width];
    }
    /*
     * 攻略迷宮
     */
    public void solveMaze() {
    	initialize(); //初始化solution及path
        solve(0, 0); //(遞迴)攻略迷宮
        
        solution[0][0] = 3; //標記起點
        
        //輸出solution、標記道路、計算步數
        System.out.println("MazeSolver --> solveMaze\n Result\n  Maze");
    	for (int j = 0; j < height; j++) { //y軸
    		System.out.print("    ");
			for (int i = 0; i < width; i++) { //x軸
					
					//計算步數
					if(solution[j][i] == 4) {
						path++;
					}
				
					//標記道路
					if(maze[j][i] == 1) {
						solution[j][i] = 1;
					}
					
					//輸出solution
//					System.out.print(solution[j][i]);
			}
//			System.out.println();
		}
//		System.out.println();
    }
    /*
     * 初始化solution及path
     */
    private void initialize() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	solution[y][x] = 0;
            	path = 0;
            }
        }
    }
    /*
     * (遞迴)攻略迷宮
     */
    private boolean solve(int x, int y) {
    	
    	//輸出Path
//        System.out.println("MazeSolver --> solveMaze\n Calculation Process\n  Path:"); //Calculation Process: 計算過程
//    	for (int j = 0; j < height; j++) { //y軸
//    		System.out.print("    ");
//			for (int i = 0; i < width; i++) { //x軸
//					System.out.print(solution[j][i]);
//			}
//			System.out.println();
//		}
//		System.out.println();
    	
        // 檢查是否超出迷宮邊界或是遇到牆壁
        if (x < 0 || x >= maze[0].length || y < 0 || y >= maze.length || maze[y][x] == 1) {
            return false;
        }
        
        // 檢查是否已經訪問過該位置
        if (solution[y][x] != 0) {
            return false;
        }

        // 檢查是否已經到達終點
        if (maze[y][x] == 2) {
            solution[y][x] = 2;
            return true;
        }
        
        // 標記當前位置已訪問
        solution[y][x] = 4;

        // 嘗試向四個方向移動
        if (solve(x + 1, y) || solve(x - 1, y) || solve(x, y + 1) || solve(x, y - 1)) {
            return true;
        }

        // 若四個方向都無法到達終點，將該位置標記為錯誤道路
        solution[y][x] = 5;

        return false;
    }
    /*
     * 回傳攻略解
     */
    public int[][] getSolution() {
        return solution;
    }
    /*
     * 回傳步數
     */
    public int getPath() {
    	return path;
    }
}

/*
 * 0: 道路
 * 1: 牆壁
 * 2: 終點
 * 3: 起點
 * 4: 攻略路徑
 * 5: 錯誤路徑
 */
