package T7;

public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] checkerboard = new int [3][3];
		int x = 2;
		int y = 2;
		int pX = 3;
		int pY = 3;
		
//		int [][]infCheckerboard = new int[8][18]; //棋盤訊息。0:空 。1:白 。2:黑
//		System.out.println(infCheckerboard.length);
		
		test2 t = new test2(x); 
		
		System.out.println("TEST -> " + x);
		System.out.println();
				
//		System.out.println(x[0-y]);
//		x = checkerboard[pY][pX] != (checkerboard[pY-1][pX-1] == null?checkerboard[pY-1][pX-1]:0)?count(0):0;
//		x = checkerboard[2][3] != checkerboard[0-3][0-3]?1:0;
//		System.out.println(x);
//		try {
//			System.out.println(y + " y "+x);
//			while(checkerboard[y--][x--] == 0) {
//				System.out.println(y + "  "+x);
//			}
//		} catch (ArrayIndexOutOfBoundsException e) {
//            // 這裡不執行任何操作，略過這個異常
//        }
		
//		try {
//			System.out.println("123");
//			x = checkerboard[pY][pX] != (checkerboard[pY-1][pX-1] > 0 ?checkerboard[pY-1][pX-1]:0)?count(pX, pY, 0):0; //上左
//			System.out.println(x);
//			x = checkerboard[pY][pX] != (checkerboard[pY-1][pX] > 0 ?checkerboard[pY-1][pX]:0)?count(pX, pY, 1):0; //上中
//			System.out.println(x);
//			x = checkerboard[pY][pX] != (checkerboard[pY-1][pX+1] > 0 ?checkerboard[pY-1][pX+1]:0)?count(pX, pY, 2):0; //上右
//			System.out.println(x);
//			x = checkerboard[pY][pX] != (checkerboard[pY][pX-1] > 0 ?checkerboard[pY][pX-1]:0)?count(pX, pY, 3):0; //中左
//			x = checkerboard[pY][pX] != (checkerboard[pY][pX+1] > 0 ?checkerboard[pY][pX+1]:0)?count(pX, pY, 4):0; //中右
//			
//			x = checkerboard[pY][pX] != (checkerboard[pY+1][pX-1] > 0 ?checkerboard[pY+1][pX-1]:0)?count(pX, pY, 5):0; //下左
//			x = checkerboard[pY][pX] != (checkerboard[pY+1][pX] > 0 ?checkerboard[pY+1][pX]:0)?count(pX, pY, 6):0; //下中
//			x = checkerboard[pY][pX] != (checkerboard[pY+1][pX+1] > 0 ?checkerboard[pY+1][pX+1]:0)?count(pX, pY, 7):0; //下右
//			System.out.println("123");
//
//		} catch (ArrayIndexOutOfBoundsException e) {
//            // 這裡不執行任何操作，略過這個異常
//        }
//		System.out.println("123");

	}

//	public static int count(int pX, int pY, int z) {
//		int x = pX*pY;
//		return x;
//	}
}
