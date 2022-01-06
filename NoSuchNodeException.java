/**
 * @author Austin Andersen
 * NoSuchNodeException to be used with DoubleLinkedList.java
 * Thrown when the node you are searching for does not exist
 */
public class NoSuchNodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchNodeException(String s) {
		super(s);
	}
	
}
