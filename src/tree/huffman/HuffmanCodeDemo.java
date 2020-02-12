package tree.huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 哈夫曼编码的例子
 * @author liuxin
 *
 */
public class HuffmanCodeDemo {
	
	public static void main(String[] args) {
		String content = "This is a test";
		byte[] encode = new HuffmanCode().zip(content.getBytes());
		System.out.println(Arrays.toString(encode));
	}
}

/**
 * 哈夫曼编码的类
 * @author liuxin
 *
 */
class HuffmanCode{
	/**
	 * 压缩bytes数组
	 * @param bytes
	 * @return
	 */
	public byte[] zip(byte[] bytes) {
		// 获取哈夫曼树
		Node root = getHuffmanTree(bytes);
		// 获取哈夫曼编码
		Map<Byte,String> map = getHaffmanCode(root);
		// 根据哈夫曼编码规则，取得原始内容的编码后的二进制字符串
		byte[] result = encode(bytes, map);

		return result;
	}
	
	/**
	 * 将bytes按照map的规则解压缩，未完成
	 * @param bytes
	 * @param map
	 * @return
	 */
	public byte[] unzip(byte[] bytes, Map<Byte,String> map) {
		
		return null;
	}
	
	/**
	 * 返回bytes的二进制形式
	 * @param bytes
	 * @return
	 */
	public StringBuilder bytesToBitString(byte[] bytes) {
		int _byte;
		// 暂存压缩的编码字符串
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<bytes.length; i++) {
			if(i==bytes.length-1) {	// 最后一位不需要高位补0
				String str = Integer.toBinaryString(bytes[i]);
				if(bytes[i] < 0) {
					sb.append(str.substring(str.length()-8));
				}else {
					sb.append(str);
				}
			}else {	// 不是最后一个，需要高位补0;
				_byte = 256 | bytes[i];
				String str = Integer.toBinaryString(_byte);
				sb.append(str.substring(str.length()-8));
			}
		}
		return sb;
	}
	
	/**
	 * 获取哈夫曼树
	 * @param 	bytes 原始内容
	 * @return
	 */
	public Node getHuffmanTree(byte[] bytes){
		// 获取bytes对应的Node列表
		List<Node> nodes = getNodeList(bytes);
		
		// 构造哈夫曼树
		while(nodes.size()>1) {
			// 先给nodes按照权重从小到大排序
			Collections.sort(nodes);
			
			// 取出最小的两颗树
			Node left = nodes.remove(0);
			Node right = nodes.remove(0);
			
			// 构造新的树
			Node parent = new Node(null, left.weight+right.weight);
			parent.left = left;
			parent.right = right;
			
			// 将新的树加入到nodes中
			nodes.add(parent);
		}
		// 最后留在nodes中的结点就是哈夫曼树的根节点
		return nodes.get(0);
	}
	
	/**
	 * 将byte数组转变成List<Node>类型的数据返回
	 * @param
	 */
	public List<Node> getNodeList(byte[] bytes){
		List<Node> list = new ArrayList<Node>();
		
		// 计数，将每个byte出现的次数存储在map中
		Map<Byte, Integer> map = new HashMap<Byte, Integer>();
		for(byte _byte : bytes) {
			if(map.get(_byte)==null) {
				map.put(_byte, 1);
			}else {
				map.put(_byte, map.get(_byte)+1);
			}
		}
		
		// 遍历map，将每个键值对转变成Node存储到list中
		for(Entry<Byte, Integer> entry: map.entrySet()) {
			list.add(new Node(entry.getKey(),entry.getValue()));
		}
		return list;
	}
	
	/**
	 * 根据以node为根节点的哈夫曼树进行编码，以左0右1的规则进行编码
	 * @param node
	 * @return
	 */
	public Map<Byte,String> getHaffmanCode(Node root){
		StringBuilder sb = new StringBuilder();
		Map<Byte, String> map = new HashMap<Byte, String>();
		createHaffmanCode(root,sb,"",map);
		return map;
	}
	
	/**
	 * 创建哈夫曼编码
	 * @param node
	 * @param sb	编码的暂存处
	 * @param tail	编码
	 * @param map	编码存储
	 */
	private void createHaffmanCode(Node node, StringBuilder sb, String tail, Map<Byte,String> map) {
		// 新创建一个StringBuilder，方便回溯
		StringBuilder sb2 = new StringBuilder(sb);
		sb2.append(tail);
		// 如果node的数据域不为空，说明到了叶子结点，则停止遍历
		if(node.data != null) {	
			map.put(node.data, sb2.toString());
			return;
		}
		// 遍历左子树
		createHaffmanCode(node.left, sb2, "0", map);
		// 遍历右子树
		createHaffmanCode(node.right, sb2, "1", map);
	}
	
	/**
	 * 按照map中指定的规则，对bytes进行编码，返回编码后的byte数组
	 * @param bytes
	 * @param map
	 * @return
	 */
	public byte[] encode(byte[] bytes, Map<Byte,String> map) {
		// 存储bytes编码后的二进制字符串
		StringBuilder sb = new StringBuilder();
		for(byte _byte : bytes) {
			sb.append(map.get(_byte));
		}
		
		// 结果字节数组
		byte[] result = new byte[(sb.length()+7)/8];
		
		// 将二进制字符串转换为bytes数组
		for(int i=0, index=0; i<sb.length(); i+=8, index++) {
			if(i+8<sb.length()) {
				result[index] = (byte) Integer.parseInt(sb.substring(i, i+8), 2);
			}else {
				result[index] = (byte) Integer.parseInt(sb.substring(i), 2);
			}
		}
		return result;
	}
}