package calculate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 简单计算器，传入一个计算式的字符串形式，用来计算一个字符串的值
 * 可支持+,-,*,/和小括号()
 * @author liuxin
 *
 */
public class Calculation {
	/*
	 * suffixExpression：存储转化后的后缀表达式，每一个元素是一个运算符或者运算数 infixExpression：存储中缀表达式的List形式
	 * operators：Map形式的运算符及其优先级 value：运算后的值
	 * 
	 */
	private List<String> suffixExpression;
	private List<String> infixExpression;
	private HashMap<String, Integer> operators = new HashMap<>();
	private Double value;

	/**
	 * 构造器
	 * 
	 * @param string 原始的中缀表达式的字符串形式
	 */
	public Calculation(String string) {
		operators.put("+", 1);
		operators.put("-", 1);
		operators.put("*", 2);
		operators.put("/", 2);
		toInfixExpression(string);
		transform();
		caculate();
	}

	/**
	 * 将中缀表达式infixExpression转化为后缀表达式suffixExpression
	 * 
	 * @param infix
	 */
	public void transform() {
		Stack<String> operStack = new Stack<>();// 运算符的存储栈
		List<String> result = new ArrayList<>();// 结果的存储栈
		for (int i = 0; i < infixExpression.size(); i++) {
			String symbol = infixExpression.get(i);
			if (symbol.equals("(")) {// 如果当前元素是左括号 (，直接入栈
				operStack.push("(");
			} else if (symbol.equals(")")) { // 如果当前元素是右括号 )
				while (!operStack.peek().equals("(")) {
					result.add(operStack.pop());
				}
				operStack.pop(); // 将左括号 ( 出栈
			} else if (operators.keySet().contains(symbol)) {// 如果是一个运算符
				if (operStack.isEmpty() || operStack.peek().equals("(")) { // 栈空或者栈顶元素是一个左括号 ( 时则直接入栈
					operStack.push(symbol);
					continue;
				} else { // 栈顶元素也是一个运算符时
					/*
					 * 反复出栈，直到栈空或者 
					 * 栈顶元素不是一个运算符 或者
					 * 栈顶元素的优先级小于当前运算符 或者 
					 */
					while (!operStack.isEmpty() // 栈不为空
							&& operators.keySet().contains(operStack.peek())	// 栈顶元素是一个运算符
							&& operators.get(symbol) <= operators.get(operStack.peek())) {	// 当前符号的优先级小于等于栈顶元素的优先级
						result.add(operStack.pop());
					}
					operStack.push(symbol);
				}
			} else { // 如果是一个操作数，直接入栈
				result.add(symbol);
			}
		}
		while (!operStack.isEmpty() 
				&& operators.containsKey(operStack.peek())) { // 如果此时运算符栈中还有符号，且其未运算符，不为落单的左括号 (
			result.add(operStack.pop());
		}
		if(!operStack.isEmpty()) {								// 处理括号不匹配的情况
			throw new RuntimeException("表达式异常");
		}
		this.suffixExpression = result;
	}

	/**
	 * 将字符串s转化为中缀表达式的List形式，每个元素是一个运算数或运算符
	 * 
	 * @param s 中缀表达式的原始字符串
	 */
	private void toInfixExpression(String s) {
		s = s.replaceAll("\\s+", ""); // 清除表达式中的空格
		List<String> result = new ArrayList<String>(); // 放置中间结果的List
		int index = 0; // 遍历字符串s的指针
		char ch; // 存储每个字符
		String buffer = ""; // 存储数字字符串
		boolean flag = false; // 标志变量，是否已经出现了一次小数点，出现过则为true，否则为false
		while (index < s.length()) {
			ch = s.charAt(index);
			if (!Character.isDigit(ch)) { // 如果不是数字类型的字符
				if (ch == '.') { // 如果该字符是小数点，则判断该数是否有效，有效则将小数点添加到buffer中
					if (!flag) {
						flag = true;
						buffer += ("" + ch);
						index++;
						continue;
					} else {
						throw new RuntimeException("表达式有误");
					}
				}
				if (!buffer.equals("")) {// 如果此时buffer不为""，则buffer中有数字
					result.add(buffer); // 将有效数字入栈
				}
				buffer = ""; // 重新初始化buffer
				flag = false; // 重新初始化flag
				result.add("" + ch);
			} else { // 如果是数字字符的话，将其暂时存入buffer中
				buffer += ch;
			}
			index++;
		}
		if (!"".equals(buffer)) { // 如果buffer中还有未存入的数字字符，则添加到的List中
			result.add(buffer);
		}
		this.infixExpression = result;
	}

	/**
	 * 计算后缀表达式的值
	 */
	private void caculate() {
		Stack<Double> numStack = new Stack<Double>();// 存储数的值
		for (int i = 0; i < this.suffixExpression.size(); i++) {
			String symbol = suffixExpression.get(i);
			if (!operators.keySet().contains(symbol)) {// 如果当前符号是运算数，则压入操作数栈
				numStack.push(Double.parseDouble(symbol));
			} else {									// 如果当前符号是运算符，则进行运算
				Double res = null;
				Double num2 = numStack.pop();
				Double num1 = numStack.pop();
				switch (symbol) {
				case "+":
					res = num1 + num2;
					break;
				case "-":
					res = num1 - num2;
					break;
				case "*":
					res = num1 * num2;
					break;
				case "/":
					res = num1 / num2;
					break;
				default:
					break;
				}
				numStack.push(res);				// 将运算后的结果压入操作数栈
			}
		}
		if (numStack.size() != 1) {						// 如果此时栈中元素个数大于1，则抛出异常
			throw new RuntimeException("表达式有误");
		}
		this.value = numStack.pop();
	}

	public List<String> getSuffixExpression() {
		return suffixExpression;
	}

	public List<String> getInfixExpression() {
		return infixExpression;
	}

	public Double getValue() {
		return value;
	}

	public static void main(String[] args) {
		String str = "1+2-3+4-5+6";
		Calculation test = new Calculation(str);
		System.out.println(str+" = "+ test.getValue());
		// System.out.println(test.getSuffixExpression());
//		System.out.println(test.getValue());
	}

}
