package sort;

import java.util.Arrays;

/**
 * 希尔排序
 * @author liuxin
 *
 */
public class ShellSortDemo {
	public static void main(String[] args) {
		int[] array = {5,1,7,8,9,2,4,0};
		shellSort(array);
		System.out.println(Arrays.toString(array));
	}

	/**
	 * 希尔排序的函数
	 * @param array
	 */
	public static void shellSort(int[] array) {
		int path;		// 记录步长
		int i,j;		// 循环变量
		int temp;		// 临时变量
		for(path=array.length/2; path>0; path/=2) {		// 逐步缩小步长
			for(i=path; i<array.length; i++) {		// 从第一组开始，进行排列
				temp = array[i];						// 记录当前排序的值
				for(j=i-path; j>=0&&array[j] > temp; j-=path) {
					array[j+path] = array[j];
				}
				
				if(j+path != i) {
					array[j+path] = temp;
				}
			}
		}
	}
}
