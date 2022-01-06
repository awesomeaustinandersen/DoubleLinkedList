/**
 * @author Austin Andersen
 * Node class, which creates the object type Node to be used in the Double-Linked List
 * @param <T> Uses a generic type
 */
public class Node<T> {
	
	private T element; //Element to be contained in the node
	private Node<T> previous; //previous node in the double-linked list
	private Node<T> next; //next node in the double-linked list
	
	/**
	 * Constructor - creates a new node
	 * @param element of generic type T
	 */
	public Node(T element) {
		setElement(element);
		setPrevious(null);
		setNext(null);
	}
	
	/**
	 * @return element from the node
	 */
	public T getElement() {
		return element;
	}
	
	/**
	 * @param element that will replace the element in the node
	 */
	public void setElement(T element) {
		element = this.element;
	}
	
	/**
	 * @return next node from the double-linked list
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * @param next node from the current node
	 */
	public void setNext(Node<T> next) {
		next = this.next;
	}
	
	/**
	 * @return previous node from the double-linked list
	 */
	public Node<T> getPrevious() {
		return previous;
	}
	
	/**
	 * @param previous node from the current node
	 */
	public void setPrevious(Node<T> previous) {
		previous = this.previous;
	}
}
