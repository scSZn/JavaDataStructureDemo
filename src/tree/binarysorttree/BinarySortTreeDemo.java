package tree.binarysorttree;

public class BinarySortTreeDemo {
	public static void main(String[] args) {
		BinarySortTree tree = new BinarySortTree();
		int[] datas = {5,3,6,1,4,8,0,2,7,9};
		for(int data : datas) {
			tree.add(new Node(data));
		}
		tree.delete(5);
		tree.delete(4);
		tree.delete(8);
		tree.inorderTraversal();
		
	}
}

/**
 * 二叉排序树的类
 * @author liuxin
 *
 */
class BinarySortTree{
	Node root;
	
	/**
	 * 新增结点
	 * @param node
	 */
	public void add(Node node) {
		if(root == null) {// 根节点为空时
			root = node;
			return;
		}
		root.add(node);
	}
	
	/**
	 * 中序遍历
	 */
	public void inorderTraversal() {
		if(root == null)
			return;
		root.inorderTraversal();
	}
	
	/**
	 * 删除结点
	 */
	public void delete(int value) {
		// root 为空时，不需要删除
		if(root == null)
			return;
		// 如果要删除的结点是root，且只有root一个结点时
		if(root.data == value && root.left==null && root.right==null) {
			root = null;
		}
		Node target = this.findNodeByData(value);	// 找到要删除的结点
		Node parent = this.findParent(value);		// 找到要删除结点的父结点
		
		// 删除的结点是叶子结点
		if(target.left==null && target.right == null) {
			if(parent.left.data == value)	// 要删除结点是父结点的左子结点时
				parent.left = null;
			else
				parent.right = null;
		}
		// 删除结点同时含有左子树和右子树时
		else if(target.left!=null && target.right!=null) {
			Node maxNode = findMax(target.left);
			target.data = maxNode.data;
		}
		// 删除结点只有左子树或者右子树时
		else {
			if(parent == null) { // 删除结点是根节点
				if(target.left!=null)
					root = target.left;
				else
					root = target.right;
			}else {
				if(parent.left.data == value) {	// 删除结点是其父结点的左子结点时
					if(target.left != null)		// 删除结点的左子树不为空
						parent.left = target.left;
					else
						parent.left = target.right;
				}else {	// 删除结点是其父结点的右子结点时
					if(target.left != null)
						parent.right = target.left;
					else
						parent.right = target.right;
				}
			}
		}
	}
	
	/**
	 * 找到并删除以node为根节点的树的最大值结点
	 * @param node
	 * @return
	 */
	private Node findMax(Node node) {
		if(node==null)
			return null;
		Node res = node;
		while(res.right!=null) {
			res = res.right;
		}
		delete(res.data);
		return res;
	}
	
	/**
	 * 根据data找到结点
	 * @param data
	 * @return
	 */
	public Node findNodeByData(int data) {
		if(this.root == null)
			return null;
		return this.root.findNodeByData(data);
	}
	
	/**
	 * 找到data对应结点的父结点
	 * @param data
	 * @return
	 */
	public Node findParent(int data) {
		if(this.root == null)
			return null;
		return this.root.findParent(data);
	}
}