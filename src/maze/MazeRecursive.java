package maze;
/**
 * 递归解决迷宫问题
 * @author liuxin
 *
 */
public class MazeRecursive {
	/**
	 * 地图变量，0表示可移动，1表示不可移动，2表示正确路径，3表示不可到达终点
	 */
	private int[][] map;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
	public MazeRecursive(int[][] map, int startX, int startY, int endX, int endY) {
		this.map = map;
		if(map[startX][startY]!=0 || map[endX][endY]!=0) {
			throw new RuntimeException();
		}
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	public static void main(String[] args) {
		int[][] map = new int[][] {
			{1,1,1,1,1,1,1,1},
			{1,0,0,1,1,1,1,1},
			{1,1,0,0,0,0,1,1},
			{1,1,0,1,0,1,1,1},
			{1,1,0,0,0,1,1,1},
			{1,1,0,1,0,0,0,1},
			{1,1,0,1,0,1,0,1},
			{1,1,1,1,1,1,1,1},
		};
		
		MazeRecursive test = new MazeRecursive(map, 1, 1, 6, 6);
		System.out.println(test.findWay(map, test.startX, test.startY));
		test.print(test.map);
	}

	/**
	 * 找路径，尝试策略下，右，上，左
	 * @param map		地图
	 * @param currX		当前点横坐标
	 * @param currY		当前点纵坐标
	 * @return			是否可到达终点
	 */
	public boolean findWay(int[][] map, int currX, int currY) {
		// 是否到达终点
		if(currX==endX && currY==endY) {
			map[currX][currY] = 2;
			return true;
		}
		if(map[currX][currY] == 0) {		// 当前位置可移动
			map[currX][currY] = 2;
			if(findWay(map, currX+1, currY)			// 尝试向下移动
				|| findWay(map, currX, currY+1)		// 尝试向右移动
				|| findWay(map, currX-1,currY)		// 尝试向上移动
				|| findWay(map, currX, currY-1))	// 尝试向左移动
				return true;
			map[currX][currY] = 3;			// 没有找到到达终点的路径时
			return false;
		}else
			return false;
	}
	
	/**
	 * 打印二维数组 map
	 */
	public void print(int map[][]) {
		for (int[] row : map) {
			for (int value : row) {
				System.out.print(value+" ");
			}
			System.out.println();
		}
	}
}
