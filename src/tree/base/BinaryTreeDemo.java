package tree.base;

/**
 * 链式存储树的先序，中序，后序遍历
 * @author liuxin
 *
 */
public class BinaryTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(0);
		root.setLeft(node2);
		root.setRight(node4);
		node2.setLeft(node3);
		node2.setRight(node5);
		
		BinaryTree tree = new BinaryTree(root);
		System.out.println("前序遍历：");
		tree.preorderTraversal();
		
		System.out.println("中序遍历：");
		tree.inorderTraversal();
		
		System.out.println("后序遍历：");
		tree.postorderTraversal();
	}

}

/**
 * 链式存储树
 * @author liuxin
 *
 */
class BinaryTree{
	private TreeNode root;	// 根节点
	
	public BinaryTree(TreeNode root) {
		this.root = root;
	}
	
	/**
	 * 先序遍历
	 */
	public void preorderTraversal() {
		if(root==null)
			return;
		root.preorderTraversal();
	}
	/**
	 * 中序遍历
	 */
	public void inorderTraversal() {
		if(root==null)
			return;
		root.inorderTraversal();
	}
	
	/**
	 * 后序遍历
	 */
	public void postorderTraversal() {
		if(root==null)
			return;
		root.postorderTraversal();
	}
}
