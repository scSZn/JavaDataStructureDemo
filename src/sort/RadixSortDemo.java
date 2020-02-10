package sort;

import java.util.Arrays;

/**
 * 基数排序
 * @author liuxin
 *
 */
public class RadixSortDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,323,34,235,23,123,2351,456,-23,-45,-896};
		radixSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	public static void radixSort(int[] array) {
		int max = array[0];
		int i,j,index;
		/*
		 * 求出数组中的最大值 
		 */
		for(i=0; i<array.length; i++) {
			if(max < array[i])	max = array[i];
		}
		int loops = (max+"").length();	// 基数排序的趟数
		int[][] bucket = new int[10][array.length];	// 10个桶
		int[] bucketElementCounts = new int[10];	// 10个桶的桶中元素个数
		
		// 开始基数排序
		for(int loop=0; loop<loops; loop++) {
			// 将数组中的数放到桶中
			for(i=0; i<array.length; i++) {
				int temp = Math.abs(array[i] /((int)Math.pow(10, loop)) % 10);				// 求出每趟的余数
				bucket[temp][bucketElementCounts[temp]] = array[i];	// 将数放入桶中
				bucketElementCounts[temp] += 1;
			}
			
			index =0;// 原数组的索引
			// 从桶中依次取出数据放回原数组
			for(i=0; i<bucketElementCounts.length; i++) {	// 遍历每个桶
				// 如果桶中有数据，将其放回原数组
				if(bucketElementCounts[i] !=0) {
					for(j=0; j<bucketElementCounts[i]; j++) {	// 遍历桶中元素
						array[index++] = bucket[i][j];
					}
				}
				bucketElementCounts[i]=0;	// 重置桶中元素个数，方便下一趟排序使用
			}
		}
	}

}
