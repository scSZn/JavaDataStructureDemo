package sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author liuxin
 *
 */
public class QuickSortDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,1,7,8,9,2,4,0};
		quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
	}

	/**
	 * 快速排序的方法
	 * @param array	待排序数组
	 * @param left	排序起点
	 * @param right	排序终点
	 */
	public static void quickSort(int[] array, int left, int right) {
		if(left >= right)				// 递归调用的终点
			return;
		int low=left, height=right;		// low和height临时变量
		int value = array[left];		// 记录值，记录该值后，array[left]即可被覆盖，方便编码
		while(low < height) {
			
			while(height>low && array[height] >= value) {	// 从右边开始找到一个小于value的值
				height --;
			}
			array[low] = array[height];	
			
			while(height>low && array[low] <= value) {		// 从左边开始找到一个大于value的值
				low ++;
			}
			
			array[height] = array[low];
		}
		
		array[low] = value;
		quickSort(array, left, low-1);		// 左边部分递归排序
		quickSort(array, low+1,right);		// 右边部分递归排序
	}
}
