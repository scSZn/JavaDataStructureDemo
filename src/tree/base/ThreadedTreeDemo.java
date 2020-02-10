package tree.base;

/**
 * 线索二叉树实现
 * @author liuxin
 *
 */
public class ThreadedTreeDemo {
	public static void main(String[] args) {
		ThreadedTreeNode root = new ThreadedTreeNode(0);
		ThreadedTreeNode node1 = new ThreadedTreeNode(1);
		ThreadedTreeNode node2 = new ThreadedTreeNode(2);
		ThreadedTreeNode node3 = new ThreadedTreeNode(3);
		ThreadedTreeNode node4 = new ThreadedTreeNode(4);
		ThreadedTreeNode node5 = new ThreadedTreeNode(5);
		
		root.setLeft(node1);
		node1.setRight(node3);
		node3.setLeft(node4);
		node4.setRight(node5);
		root.setRight(node2);
		ThreadedTree tree = new ThreadedTree(root);
//		tree.preorderThreaded(root);
//		tree.preorderThreadedTraversal();
		
//		tree.inorderThreaded(root);
//		tree.inorderThreadedTraversal();
		
		tree.postorderThreaded(root);
		
	}
}


class ThreadedTree{
	private ThreadedTreeNode root;
	private ThreadedTreeNode pre;
	

	public ThreadedTree(ThreadedTreeNode root) {
		super();
		this.root = root;
	}
	
	/**
	 * 将本树先序线索化
	 */
	public void preorderThreaded(ThreadedTreeNode node) {
		if(node.getLeft()==null) {		// 如果当前结点的左结点为空，则将其指向前置结点
			node.setLeft(pre);
			node.setLeftType(1);		// 更新左结点类型
		}
		
		if(pre!=null) {					// 前置结点不为空时
			if(pre.getRight()==null) {	// 如果前置结点的右子结点为空，则将其指向本结点
				pre.setRight(node);
				pre.setRightType(1);
			}
		}
		// 更新 pre 指向
		pre = node;						// 前置结点下移
		
		// 线索化左子树
		if(node.getLeft()!=null && node.getLeftType() == 0)		// 当前结点存在左子树
			preorderThreaded(node.getLeft());
		
		
		// 线索化右子树					
		if(node.getRight()!=null && node.getRightType() == 0)	// 当前结点存在右子树
			preorderThreaded(node.getRight());
	}
	
	/**
	 * 遍历先序线索化二叉树
	 */
	public void preorderThreadedTraversal() {
		if(root== null)
			return;
		ThreadedTreeNode curr = root;		// 当前结点
		while(curr != null) {				// 只要当前结点不为空，就一直遍历
			System.out.println(curr);		// 先序遍历，所以先输出该结点
			
			while(curr.getLeftType() == 0) {		// 如果如果左结点的类型是数据结点
				curr = curr.getLeft();		// 当前结点左移
				System.out.println(curr);	// 输出结点，先序遍历
			}
			
			curr = curr.getRight();			// 当左结点的类型不是数据结点时，当前结点右移
		}
	}
	
	/**
	 * 将树中序线索化
	 * @param node
	 */
	public void inorderThreaded(ThreadedTreeNode node) {
		// 线索化左子树
		if(node.getLeft()!=null) {		// 如果存在左子树，则将左子树线索化
			inorderThreaded(node.getLeft());
		}
		
		// 线索化node结点
		if(node.getLeft() == null) {		// 如果node结点的左子树为空，则将其指向前一个结点
			node.setLeft(pre);
			node.setLeftType(1); // 更换结点类型
		}
		
		if(pre!=null) {					// 如果前置结点不为空，且其右子树为空，则将其指向当前结点
			if(pre.getRight() == null) {
				pre.setRight(node);
				pre.setRightType(1);
			}
		}
		// 移动pre指向当前结点
		pre=node;
			
		// 线索化右子树
		if(node.getRight()!=null) {		// 存在右子结点时才去线索化
			inorderThreaded(node.getRight());
		}
	}
	
	/**
	 * 中序线索化二叉树的遍历
	 */
	public void inorderThreadedTraversal() {
		if(root == null)		// 先排除空树的情况
			return;
		ThreadedTreeNode curr = root;
	
		while(curr!=null) {
			// 先向左找到一个左子结点为空或者左子结点为线索结点的结点
			while(curr.getLeft()!=null && curr.getLeftType()==0) {
				curr = curr.getLeft();
			}
			System.out.println(curr);		// 输出结点
			
			while(curr.getRightType()==1) {	// 说明当前结点的右子结点是后续结点
				curr = curr.getRight();		// 结点右移
				System.out.println(curr);
			}
			// 当找到一个结点的右子结点不为后续结点时，重复过程
			curr = curr.getRight();
		}
	}
	
	/**
	 * 后序线索化树
	 * @param node
	 */
	public void postorderThreaded(ThreadedTreeNode node) {
		// 线索化左子树
		if(node.getLeft()!=null) {	// 左子树不为空时线索化左子树
			postorderThreaded(node.getLeft());
		}
		
		// 线索化右子树
		if(node.getRight()!=null) {	//右子树不为空时线索化右子树
			postorderThreaded(node.getRight());
		}
		
		// 线索化当前结点
		if(node.getLeft()==null) {	// 当前结点的左子树为空时
			node.setLeft(pre);
			node.setLeftType(1);
		}
		if(pre!=null) {	// 前序结点不为空时
			if(pre.getRight()==null) {	// 前序结点的右子树为空时
				pre.setRight(node);
				pre.setRightType(1);
			}
		}
		// 移动 pre 结点
		pre = node;
	}
	
//	/**
//	 * 后序线索化二叉树的遍历
//	 */
//	public void postorderThreadedTraversal() {
//		if(root == null)		// 避免空树的情况
//			return;
//		ThreadedTreeNode curr = root;
//		// 找到第一个结点
//		while(curr.getLeft()!=null || curr.getRightType()!=1) {
//			// 先一直向左移动到某个结点没有左孩子时
//			while(curr.getLeft()!=null && curr.getLeftType()==0) {
//				curr = curr.getLeft();	// 一直左移
//			}
//			
//			// 再一直向右移动到某个结点没有右孩子时
//			while(curr.getRight()!=null && curr.getRightType() == 0) {
//				curr = curr.getRight();	// 一直右移
//			}
//		}
//		
//		// 根结点一定是最后一个，所以只要当前结点不是根节点，那么就还没有遍历完
//		while(curr!=root) {
//			while(curr.getLeftType()!=1 || curr.getRightType()!=1) {
//				// 先一直向左移动到某个结点没有左孩子时
//				while(curr.getLeft()!=null && curr.getLeftType()==0) {
//					curr = curr.getLeft();	// 一直左移
//				}
//				
//				// 再一直向右移动到某个结点没有右孩子时
//				while(curr.getRight()!=null && curr.getRightType() == 0) {
//					curr = curr.getRight();	// 一直右移
//				}
//			}
//			// 输出当前结点
//			System.out.println(curr);
//			
//			while(curr.getRightType() == 1) {	// 如果右子结点是后续结点时
//				curr = curr.getRight();
//				System.out.println(curr);
//			}
//			
//			curr = curr.getRight();
//		}
//		
//		System.out.println(curr);
//	}
}