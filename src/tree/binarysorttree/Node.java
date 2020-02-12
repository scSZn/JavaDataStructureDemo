package tree.binarysorttree;

/**
 * 二叉排序树的树结点
 * @author liuxin
 *
 */
public class Node implements Comparable<Node>{
	public int data;
	public Node left;
	public Node right;
	
	public Node(int data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}

	@Override
	public int compareTo(Node o) {
		return this.data - o.data;
	}
	
	/**
	 * 添加结点
	 * @param node
	 */
	public void add(Node node) {
		if(this.compareTo(node)>0) {	// node 结点小于当前结点
			if(this.left == null)		// 左子结点为空时，将新结点加入到当前结点的左子结点
				this.left = node;
			else						// 左子结点不为空时，递归在左子树中添加结点
				this.left.add(node);
		}else {							// node 结点大于或等于当前结点
			if(this.right == null)		// 右子结点为空
				this.right = node;
			else
				this.right.add(node);
		}
	}
	
	/**
	 * 中序遍历树
	 */
	public void inorderTraversal() {
		if(this.left != null) {
			this.left.inorderTraversal();
		}
		System.out.println(this);
		if(this.right != null) {
			this.right.inorderTraversal();
		}
	}
	
	/**
	 * 按照data找到结点，返回第一个
	 * @param data
	 * @return
	 */
	public Node findNodeByData(int data) {
		if(this.data == data)
			return this;
		
		if(this.left!=null && this.data > data)
			return this.left.findNodeByData(data);
		
		if(this.right != null && this.data <= data)
			return this.right.findNodeByData(data);
		
		return null;
	}
	
	/**
	 * 找到data的父结点
	 * @param data
	 * @return
	 */
	public Node findParent(int data) {
		if((this.left!=null&&this.left.data==data) || (this.right!=null && this.right.data == data)) {
			return this;
		}else {
			if(this.left!=null && data<this.data)
				return this.left.findParent(data);
			if(this.right!=null && data>=this.data)
				return this.right.findParent(data);
		}
		return null;
	}
}
