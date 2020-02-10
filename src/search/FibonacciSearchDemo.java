package search;

import java.util.Arrays;

/**
 * 斐波那契查找算法
 * @author liuxin
 *
 */
public class FibonacciSearchDemo {
	public static int maxSize = 20;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		System.out.println(fibonacciSearch(array, 0));
	}
	/**
	 * 用非递归的方式得到一个斐波那契数列
	 * @return
	 */
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = f[1] = 1;
		for(int i=2; i<maxSize; i++) {
			f[i] = f[i-1]+f[i-2];
		}
		return f;
	}
	
	/**
	 * 斐波那契查找算法的实现
	 * @param array		待查找的有序数组
	 * @param findVal	待查找的值
	 * @return	
	 */
	public static int fibonacciSearch(int[] array, int findVal) {
		int left=0,right=array.length-1;
		// 获得斐波那契数列
		int[] f = fib();
		int k=0;
		// 获得合适的k值
		while(array.length > f[k] -1) {
			k++;
		}
		
		int[] newArray = Arrays.copyOf(array, f[k]-1);		// 构造新的符合斐波那契查找的数组
		for(int i=array.length; i<newArray.length; i++) {	// 为了保证新数组也是有序的，将填充的值替换为原数组最大值
			newArray[i] = array[array.length-1];
		}
		
		// 开始斐波那契查找
		while(left<=right) {
			int mid = left+f[k-1]-1;		// 取定黄金分割点的值
			if(newArray[mid] < findVal) {	// 说明待查找的值在数列的后半段，即f[k-2]-1这一段
				left = mid+1;				
				k-=2;
			}else if(newArray[mid] > findVal) {	// 说明待查找的值在数列的前半段，即f[k-1]-1这一段
				right = mid-1;
				k-=1;
			}else {
				return mid<=right? mid:right;	// 避免了如果查找值是最大值时，造成查找出来的索引是填充出来的情况 
			}
		}
		
		return -1;
	}
	

}
