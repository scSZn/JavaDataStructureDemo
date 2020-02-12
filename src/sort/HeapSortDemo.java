package sort;

import java.util.Arrays;

public class HeapSortDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {3,2,4,5,7,8,9};
		heapSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	public static void heapSort(int[] array) {
		int i;
		int temp;
		// 构造初始堆
		for(i=array.length/2-1; i>=0; i--) {
			adjust(array, i, array.length);
		}
		i=1;
		while(i<array.length) {
			// 交换堆顶元素和末尾元素的值，注意末尾元素每次需要向前移动
			temp = array[array.length-i];
			array[array.length-i] = array[0];
			array[0] = temp;
			
			// 调整堆
			adjust(array, 0, array.length-i);
			i++;
		}
	}
	
	/**
	 * 调整以i为结点的树
	 * @param array
	 * @param length
	 */
	public static void adjust(int[] array, int i, int length) {
		int value = array[i];					// 先将array[i]的值提取出来
		for(int k=2*i+1; k<length; k=2*k+1) {	// 从 i 的左子结点开始
			if(k+1<length && array[k]<array[k+1]) {	// 如果存在右子结点并且右子结点的值大于左子结点
				k++;
			}
			if(array[k]>value) {		// 如果存在子结点的值大于array[i]
				array[i] = array[k];	// 将父结点的值设置为最大子节点的值
				i=k;					// 父结点下移
			}else
				break;
		}
		array[i] = value;
	}

}

