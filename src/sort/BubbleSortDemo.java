package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author liuxin
 *
 */
public class BubbleSortDemo {
	static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[] {1,2,3,4,5,6,7,8,9};
		bubbleSortOptimizate(array);
		System.out.println(Arrays.toString(array));
		System.out.println("排序趟数："+count);
	}	

	/**
	 * 原始的冒泡排序方法
	 * @param array		要排序的数组
	 */
	public static void bubbleSort(int[] array) {
		int temp; // 辅助变量
		for(int i=0; i<array.length-1; i++) {	// 表示第几趟排序
			count++;
			for(int j=0; j<array.length-1-i; j++) {	// 每次比较用array[j]和array[j+1]进行比较
				if(array[j] > array[j+1]) {
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
	
	/**
	 * 优化冒泡排序，通过设置标志变量flag，如果某一趟排序中发现没有发生交换，则可以结束排序
	 * @param array
	 */
	public static void bubbleSortOptimizate(int[] array) {
		int temp; // 辅助变量
		boolean flag;	// 标志变量
		for(int i=0; i<array.length-1; i++) {	// 表示第几趟排序，排序趟数为 n-1 躺，n为数据大小
			flag = true; // 重置flag
			count++;
			for(int j=0; j<array.length-1-i; j++) {	// 每次比较用array[j]和array[j+1]进行比较
				if(array[j] > array[j+1]) {
					flag = false;
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
			if(flag) {		// 说明本趟排序没有发生交换，数组已排好序
				return;
			}
		}
	}
}
