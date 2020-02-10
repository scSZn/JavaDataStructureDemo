package search;
/**
 * 顺序查找算法，数列有序无序均可使用
 * @author liuxin
 *
 */
public class SequenceSearchDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {2,3,5,7,9};
		System.out.println(sequenceSearch(array, 4));
	}

	/**
	 * 在数组array中查找值为value的下标，如果没有则返回 -1
	 * @param array
	 * @param value
	 * @return
	 */
	public static int sequenceSearch(int[] array, int value) {
		for(int i=0; i<array.length; i++) {
			if(array[i] == value) {		// 对比
				return i;
			}
		}
		return -1;	// 没找到的情况
	}
}
