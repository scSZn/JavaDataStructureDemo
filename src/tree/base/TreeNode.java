package tree.base;
/**
 * 链式存储树的节点类
 * @author liuxin
 *
 */
public class TreeNode {
	private int value;			// 值域
	private TreeNode left;		// 左子节点类
	private TreeNode right;		// 右子节点类
	
	public TreeNode(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	/**
	 * 先序遍历
	 */
	public void preorderTraversal() {
		System.out.println(this);			// 先输出该结点
		
		if(this.left!=null) 				// 左结点不为空时，先序遍历左子树
			this.left.preorderTraversal();
		
		if(this.right != null) 				// 右结点不为空时，先序遍历右子树
			this.right.preorderTraversal();
	}
	
	/**
	 * 中序遍历
	 */
	public void inorderTraversal() {
		if(this.left!=null) 				// 左结点不为空时，中序遍历左子树
			this.left.inorderTraversal();
		
		System.out.println(this);			// 输出该结点
		
		if(this.right != null) 				// 右结点不为空时，中序遍历右子树
			this.right.inorderTraversal();
	}
	
	/**
	 * 后序遍历
	 */
	public void postorderTraversal() {
		if(this.left!=null) 				// 左结点不为空时，后序遍历左子树
			this.left.postorderTraversal();
		
		if(this.right != null) 				// 右结点不为空时，后序遍历右子树
			this.right.postorderTraversal();
		
		System.out.println(this);			// 输出该结点
	}

	@Override
	public String toString() {
		return "TreeNode [value=" + value + "]";
	}

}
