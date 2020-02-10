package tree.base;

public class ArrayBinaryTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {2,3,1,5,0};
		ArrayBinaryTree tree = new ArrayBinaryTree(array);
		System.out.println("先序遍历：");
		tree.preorderTraversal(0);
		
		System.out.println("中序遍历：");
		tree.inorderTraversal(0);
		
		System.out.println("后序遍历：");
		tree.postorderTraversal(0);
	}

}

/**
 * 顺序存储树
 * @author liuxin
 *
 */
class ArrayBinaryTree{
	private int[] array;
	
	public ArrayBinaryTree(int[] array) {
		this.array = array;
	}
	
	/**
	 * 先序遍历
	 */
	public void preorderTraversal(int index) {
		if(index<0 || index>=array.length)	// 防止数组越界
			return;
		System.out.println(array[index]);	// 输出结点
		preorderTraversal(2*index+1);		// 先序遍历左子树
		preorderTraversal(2*index+2);		// 先序遍历右子树
	}
	
	/**
	 * 中序遍历
	 */
	public void inorderTraversal(int index) {
		if(index<0 || index>=array.length)	// 防止数组越界
			return;
		
		inorderTraversal(2*index+1);		// 中序遍历左子树
		System.out.println(array[index]);	// 输出结点
		inorderTraversal(2*index+2);		// 中序遍历右子树
	}
	
	/**
	 * 后序遍历
	 */
	public void postorderTraversal(int index) {
		if(index<0 || index>=array.length)	// 防止数组越界
			return;
		
		postorderTraversal(2*index+1);		// 后序遍历左子树
		postorderTraversal(2*index+2);		// 后序遍历右子树
		System.out.println(array[index]);	// 输出结点
	}
}
