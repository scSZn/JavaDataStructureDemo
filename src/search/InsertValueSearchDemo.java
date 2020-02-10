package search;

/**
 * 插值查找算法，是二分查找的一个扩展，通过改进mid的值
 * 适用于分布均匀的有序数组
 * @author liuxin
 *
 */
public class InsertValueSearchDemo {
	static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[100];
		for(int i=0; i<array.length; i++)
			array[i] = i+1;
		System.out.println(insertValueSearch(array, 0, array.length-1, 24));
		System.out.println("查找次数为"+count);
	}

	public static int insertValueSearch(int[] array, int left, int right, int findVal) {
		count++;
		// 优化代码且可以防止数组越界
		if(left>right || findVal<array[left] || findVal > array[right]) {
			return -1;
		}
		
		int mid = left+(findVal-array[left])*(array[right]-array[left])/right-left;
		if(findVal > array[mid]) {
			return insertValueSearch(array, mid+1, right, findVal);
		}else if(findVal < array[mid]) {
			return insertValueSearch(array, left, mid-1, findVal);
		}else {
			return mid;
		}
	}
}
