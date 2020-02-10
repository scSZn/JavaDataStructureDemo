package sort;

/**
 * 归并排序
 * @author liuxin
 *
 */
public class MergeSortDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[8000000];
		for(int i=0; i<8000000; i++) {
			array[i] = (int)(Math.random()*800000000);
		}
		System.out.println(System.currentTimeMillis());
		array = mergeSort(array, 0, array.length-1);
		System.out.println(System.currentTimeMillis());
	}
	
	/**
	 * 归并排序的函数
	 * @param array	待排序数组
	 * @param left	排序的起点
	 * @param right	排序的终点
	 * @return		排序后的数组
	 */
	public static int[] mergeSort(int[] array, int left, int right) {
		if(left==right) {						// 递归终点
			return new int[] {array[left]};
		}
		int mid = (left+right)/2;				// 中间点
		int[] leftArray = mergeSort(array, left, mid);		// 左边数组归并排序
		int[] rightArray = mergeSort(array, mid+1, right);	// 右边数组归并排序
		
		return merge(leftArray, rightArray);	// 合并两边的有序数组
	}
	
	/**
	 * 将两个有序数组合并成一个有序数组
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static int[] merge(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length+arr2.length];// 结果数组
		int index1=0,index2=0;			// 两个数组的下标索引
		int i;							// 结果数组的下标索引
		/*
		 * 当 i,index1,index2 同时在有效范围内时比较
		 */
		for(i=0;i<result.length && index1<arr1.length && index2 <arr2.length; i++) {
			/*
			 * 将两个数组中较小的数存入结果数组中
			 */
			if(arr1[index1]<=arr2[index2]) {
				result[i] = arr1[index1];
				index1++;
			}else {
				result[i] = arr2[index2];
				index2++;
			}
		}
		
		// 当arr1中还有剩余元素时
		while(index1 < arr1.length) {
			result[i] = arr1[index1];
			i++;
			index1++;
		}
		
		// 当arr2中还有剩余元素时
		while(index2 < arr2.length) {
			result[i] = arr2[index2];
			i++;
			index2++;
		}
		
		return result;
	}

}
