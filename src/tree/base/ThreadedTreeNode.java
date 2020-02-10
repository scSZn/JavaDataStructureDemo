package tree.base;

/**
 * 线索化二叉树的树节点
 * @author liuxin
 *
 */
public class ThreadedTreeNode {
	private int value;			// 值域
	private ThreadedTreeNode left;		// 左子节点类
	private ThreadedTreeNode right;		// 右子节点类
	private int leftType;		// 为1时说明该左子节点为前序节点
	private int rightType;		// 为1时说明该右子节点为后序节点 

	public ThreadedTreeNode(int value) {
		this.value = value;
	}

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ThreadedTreeNode getLeft() {
		return left;
	}

	public void setLeft(ThreadedTreeNode left) {
		this.left = left;
	}

	public ThreadedTreeNode getRight() {
		return right;
	}

	public void setRight(ThreadedTreeNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "ThreadedTreeNode [value=" + value + "]";
	}

	
}
