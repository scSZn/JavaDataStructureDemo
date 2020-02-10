package sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author liuxin
 *
 */
public class SelectSortDemo {

	public static void main(String[] args) {
		int[] array = new int[] {5,6,7,0,1,4};
		selectSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * 选择排序的函数
	 * @param array
	 */
	public static void selectSort(int[] array) {
		int min;	// 每次选出最小值
		int index;	// 记录最小值的下标
		for(int i=0; i<array.length-1; i++) {
			min = array[i];
			index = i;
			for(int j=i+1; j<array.length; j++) {
				if(min > array[j]) {	// 如果出现了比当前最小值还小的数，记录其下标
					index = j;			// 记录新的最小值的下标
					min = array[j];		// 更新最小值
				}
			}
			
			if(index != i) {
				array[index] = array[i];
				array[i] = min;
			}
		}
	}
}
