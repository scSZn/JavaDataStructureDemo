package queues8;
/**]
 * 递归解决8皇后问题
 * @author liuxin
 *
 */
public class Queue8Recursive {
	/**
	 * MAX 		表示有几个皇后
	 * array 	用来模拟棋盘，下标表示第几行，元素表示第几列
	 * count 	表示有几种解法
	 */
	public static final int MAX = 8;
	public int[] array = new int[MAX];
	public static int count = 0;
	public static int checkCount = 0;
	
	public static void main(String[] args) {
		Queue8Recursive test = new Queue8Recursive();
		test.put(0);
		System.out.printf("共有 %d 种不同的解法\n", count);
		System.out.printf("一共检查了 %d 次冲突\n", checkCount);
	}
	
	/**
	 * 在第n+1行上尝试放置皇后
	 * @param n	代表第几行
	 */
	public void put(int n) {
		// 当 n==MAX 时，即超出了棋盘时，则说明已经放完了，打印结果并退出
		if(n==MAX) {		
			print();
			return;
		}
		// 尝试在该行上放置不同列的值
		for(int i=0; i<MAX; i++) {
			array[n] = i;
			if(checkConflict(n)) {		// 检查是否冲突，没有冲突则往下一行继续尝试放置
				put(n+1);
			}
		}
	}
	
	/**
	 * 判断第n+1个位置与之前的摆放是否冲突，没有冲突则返回true，冲突返回false
	 * @param n
	 * @return
	 */
	public boolean checkConflict(int n) {
		checkCount++;
		for(int i=0; i<n; i++) {
			if(array[i] == array[n] 		// 位置n与位置i在同一列
				|| Math.abs(n-i) == Math.abs(array[n]-array[i])) { // 位置n与位置i在同一斜线，参考斜率
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 将结果输出
	 */
	public void print() {
		count ++;		// 记录解法
		for (int i : array) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
}
