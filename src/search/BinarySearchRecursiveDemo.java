package search;
/**
 * 二分查找的递归实现
 * 查找的数列需要有序
 * @author liuxin
 *
 */
public class BinarySearchRecursiveDemo {

	public static void main(String[] args) {
		int[] array = {0,1,2,3,4,5,6,7};
		System.out.println(binarySearch(array, 0, array.length-1, 7  ));
	}
	
	/**
	 * 在数组array中，以left为起点，right为终点，寻找findVal值的下标，
	 * @param array
	 * @param left
	 * @param right
	 * @param findVal
	 * @return
	 */
	public static int binarySearch(int[] array, int left, int right, int findVal) {
		
		if(left > right) {
			return -1;
		}
		
		int mid = (left+right)/2;
		if(array[mid] == findVal) {
			return mid;
		}else if(array[mid] > findVal) {
			return binarySearch(array, left, mid-1, findVal);
		}else {
			return binarySearch(array, mid+1, right, findVal);
		}
		
	}
}
