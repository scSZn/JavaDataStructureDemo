package tree.huffman;

/**
 * 哈夫曼编码的结点类
 * @author liuxin
 *
 */
public class Node implements Comparable<Node>{
	public Byte data;	// 存储数据
	public int weight;	// 存储权重
	public Node left;	// 左子结点
	public Node right;	// 右子结点
	
	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	// 升序排序
	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
	
}
