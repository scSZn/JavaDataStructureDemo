package hash;

/**
 * 哈希表的简单实现，用链表的方式处理冲突
 * @author liuxin
 *
 */
public class HashTableDemo<E> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable ht = new HashTable(9);
		ht.add(new Student(2,"我日"));
		ht.add(new Student(11,"我日"));
		ht.add(new Student(20,"我日"));
		ht.add(new Student(29,"我日"));
		ht.add(new Student(38,"我日"));
		ht.add(new Student(47,"我日"));
		ht.add(new Student(56,"我日"));
		ht.add(new Student(65,"卧槽"));
		ht.list();
	}

}

/**
 * 哈希表
 * @author liuxin
 *
 */
class HashTable{
	private Element[] elements;
	private int size;
	
	/**
	 * 构造方法，size指定HashTable中数组的大小
	 * @param size
	 */
	public HashTable(int size) {
		this.size = size;
		elements = new Element[size];
		for(int i=0; i<size; i++)
			elements[i] = new Element();
	}
	
	/**
	 * 哈希函数，根据传入的id值返回相应的hash值，这里使用取余的方式
	 * @param id
	 * @return
	 */
	private int hash(int id) {
		return id%size;
	}
	
	/**
	 * 添加一个学生到哈希表中
	 * @param student
	 */
	public void add(Student student) {
		if(student == null)
			throw new RuntimeException("student对象为空");
		int index = hash(student.id);
		elements[index].add(student);
	}
	
	/**
	 * 添加一个学生到哈希表中，要求按照id号有序插入链表
	 * @param student
	 */
	public void addByOrder(Student student) {
		if(student == null)
			throw new RuntimeException("student对象为空");
		int index = hash(student.id);
		elements[index].addByOrder(student);
	}
	
	/**
	 * 显示哈希表
	 */
	public void list() {
		for(Element element : elements) {
			element.list();
		}
	}
}

/**
 * 是一个链表
 * @author liuxin
 *
 */
class Element{
	public Student head;		// 头部
	public Student tail;
	public int count = 0;
	
	/**
	 * 添加一个学生到该链表尾部
	 * @param student
	 */
	public void add(Student student) {
		if(head == null) {		// 如果添加的是该链表的第一个节点
			head = student;
		}else {					// 添加位置在其他节点
			tail.next = student;
		}
		tail = student;
		count++;
	}
	
	/**
	 * 将新节点按照ID号顺序的添加到链表中
	 * @param student
	 */
	public void addByOrder(Student student) {
		if(head == null) {
			head = student;
			tail = student;
		}else {
			// 先找到新添加节点该插入的位置
			Student prev = null;			// 前一个节点
			Student curr = head;			// 当前节点
			while(curr!=null && curr.id < student.id) {
				prev = curr;
				curr = curr.next;
			}
			if(prev==null) {		// 如果prev为空，则说明新添加节点是头节点
				student.next = head;
				head = student;
			}else {					// 否则说明新添加节点应在prev后面
				student.next = prev.next;
				prev.next = student;
				if(prev == tail) {	// 如果prev和tail指向同一个引用，说明新添加的节点在链表尾部，说明tail应该向后移动
					tail = student;
				}
			}
		}
		count++;
	}
	
	/**
	 * 打印本链表中所有的元素
	 */
	public void list() {
		Student student = head;
		StringBuilder sb = new StringBuilder("");
		while(student!=null) {
			sb.append(student);
			student = student.next;
		}
		System.out.println(sb.toString());
	}
}

/**
 * 元素类
 * @author liuxin
 *
 */
class Student{
	public int id;
	public String name;
	public Student next;
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
	
}
