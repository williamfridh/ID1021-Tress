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

	public data_type pop() {
		if (this.first == null)
			return null;
		data_type tmp = this.first.val;
		this.first = this.first.nxt;
		return tmp;
	}
	
}

