public class Stack <data_type> {

	Element first;

	private class Element {
		data_type val;
		Element nxt;
		Element(data_type val, Stack<data_type> stack) {
			this.val = val;
			this.nxt = stack.first;
		}
	}

	public void push(data_type val) {
		Element new_el = new Element(val, this);
		this.first = new_el;
	}

	public data_type pop() throws Exception {
		if (this.first == null) {
			throw new Exception("Tried to pop empty stack");
		}
		data_type tmp = this.first.val;
		this.first = this.first.nxt;
		return tmp;
	}

	Stack() {
		
	}

	/*public static void main(String[] args) {
		Stack my_stack = new Stack();
		try {
			my_stack.push(1);
			my_stack.push(2);
			my_stack.push(3);
			System.out.println(my_stack.pop()); // 3
			System.out.println(my_stack.pop()); // 2
			my_stack.push(4);
			System.out.println(my_stack.pop()); // 4
			System.out.println(my_stack.pop()); // 1
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
}

