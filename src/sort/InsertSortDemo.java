package sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author liuxin
 *
 */
public class InsertSortDemo {
	
	public static void main(String[] args) {
		int[] array = new int[] {2,4,1,6,5,0};
		insertSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * 插入排序的函数
	 * @param array
	 */
	public static void insertSort(int[] array){
		int temp; // 辅助变量
		int i,j;
		for(i=1; i<array.length; i++) {
			temp = array[i]; // 记录当前位置的值，方便比较
			for(j=i-1; j>=0&&array[j] > temp; j--) {
				array[j+1] = array[j];
			}
			if(i!=j+1)				// 判断是否需要赋值
				array[j+1] = temp;
		}
	}
}
