import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements Iterable<T>, List<T> {

	public class MyListIterator implements ListIterator<T> {

		Node<T> previous;
		Node<T> next;
		Node<T> lastNode;
		
		boolean didPrevious;
		boolean didNext;
		
		int tempCount;
		int position;
		
		public MyListIterator() {
			previous = null;
			next = head;
			lastNode = null;
			
			
			didPrevious = false;
			didNext = false;
		
			int tempCount = count;
			int position = 0;
		}
		
		@Override
		public boolean hasNext() {
			
			return (next.getNext() != null);
			
		}

		@Override
		public T next() {
			
			if(!hasNext()) {
				throw new NoSuchElementException("List does not have a next element");
			} else if(tempCount != count) {
				throw new ConcurrentModificationException("List has been modified");
				//don't think i need this for this section. Need it for remove and add functions
			} else {
				previous = next;
				next = next.getNext();
				
				didPrevious = false;
				didNext = true;
				
				tempCount = count;
				position++;
				
				//I think I can get rid of if statement. hasNext already checks if next element is null
				if(lastNode != null) {
					return lastNode.getElement();
				} else {
					return null;
				}
			}
		}

		@Override
		public boolean hasPrevious() {
			
			return (next.getPrevious() != null);
		
		}

		@Override
		public T previous() {
			
			if(!hasPrevious()) {
				throw new NoSuchElementException("List does not have a previous element");
			} else {
				next = previous;
				previous = previous.getPrevious();
				
				didNext = false;
				didPrevious = true;
				
				tempCount = count;
				position--;
				
				//Again, I think I can get rid of this if statement. Need to write it out on paper and check the logic
				if(lastNode != null) {
					return lastNode.getElement();
				} else {
					return null;
				}
			}
		}

		@Override
		public int nextIndex() {
			
			if(!hasNext()) {
				return count;
			} else {
				return position;
			}
			
		}

		@Override
		public int previousIndex() {
			
			if(!hasPrevious()) {
				return -1;
			} else {
				return position - 1;
			}
		}

		@Override
		public void remove() {
			
			if(!didPrevious && !didNext) {
				//remove
			} else if(tempCount != count){
				throw new ConcurrentModificationException("List has been modified since a call of next or previous has been made");
			}
			
		}

		@Override
		public void set(T e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void add(T e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public Node<T> head;
	public Node<T> tail;
	public int count;
	
	/**
	 * Constructor - Creates an empty Double-Linked List
	 */
	public DoubleLinkedList() {

		head = null;
		tail = null;
		count = 0;

	}
	
	/**
	 * One thing to keep in mind, some of these add methods might be good to include with the iterator,
	 * especially the AddBefore and AdddAfter
	 */
	
	//public void AddBefore(Node<T> newNode, Node<T> currentNode) {
	
	//public void AddAfter(Node<T> newNode, Node<T> currentNode) {
	
	//public void AddAtPostition(Node<T> newNode, int position) {
	
	/**
	 * Adds a new node to the back. If there are no nodes, it adds it and sets it to both head and tail
	 * @param newNode to be added to the back
	 */
	public void addToBack(Node<T> newNode) {
		if(count == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
		}
		
		count++;
	}
	
	/**
	 * Adds a new node to the front. If there are no nodes, it adds it and sets it to both head and tail
	 * @param newNode New node to be added to the front
	 */
	public void addToFront(Node<T> newNode) {
		if(count == 0) {
			head = newNode;
			tail = newNode;
		} else {
			head.setPrevious(newNode);
			newNode.setNext(head);
			head = newNode;
		}
		
		count++;
		
	}
	
	//needs work
	public void removeNode(Node<T> node) {
		DoubleLinkedList<String> t;
		count--;
	}
	
	/**
	 * Removes the head node and sets it to the next
	 * @throws NoSuchNodeException if there is no head node
	 */
	public void removeHead() throws NoSuchNodeException {
		if(count != 0) {
			Node<T> newHead = head.getNext();
			head.setNext(null);
			if(count > 1) {
				newHead.setPrevious(null);
			}
			head = newHead;
			count--;
		} else {
			throw new NoSuchNodeException("Cannot remove head since head does not exist");
		}
		
	}
	
	/**
	 * Removes the tail node and sets it to the previous
	 * @throws NoSuchNodeException  if there is no tail node
	 */
	public void removeTail() throws NoSuchNodeException {
		if(count != 0) {
			Node<T> newTail = tail.getPrevious();
			tail.setPrevious(null);
			if(count > 1) {
				newTail.setNext(null);
			}
			tail = newTail;
			count--;
		} else {
			throw new NoSuchNodeException("Cannot remove tail since tail does not exist");
		}
		
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return(count == 0);
	}
	
	public T getHead() throws NoSuchNodeException{
		if(count == 0) {
			throw new NoSuchNodeException("Head node does not exist");
		} else {
			return head.getElement();
		}
		
	}
	
	public void setHead(T newElement) {
		if(count == 0 || count == 1) {
			tail.setElement(newElement);
		}
		head.setElement(newElement);
	}
	
	public T getTail() throws NoSuchNodeException{
		if(count == 0) {
			throw new NoSuchNodeException("Tail node does not exist");
		} else {
			return tail.getElement();
		}
		
	}
	
	public void setTail(T newElement) {
		if(count == 0 || count == 1) {
			head.setElement(newElement);
		}
		tail.setElement(newElement);
	}

	//Ask dad if this will work - if comparing an object to a generic type works
	@Override
	public boolean contains(Object targetElement) {
		
		Node<T> currentNode = head;
		
		if(count == 0) {
			return false;
		} else {
			while(currentNode != null) {
				if(currentNode.getElement() == targetElement) {
					return true;
				} else {
					currentNode = currentNode.getNext();
				}
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T e) {
		
		if(e == null) {
			throw new NullPointerException("Element that is being added is null");
		} else if(contains(e)) {
			return false; //should I not allow duplicates
		} else {
			Node<T> newNode = new Node<T>(e);
			if(count == 0) {
				head = newNode;
				tail = newNode;
			} else {
				tail.setNext(newNode);
				newNode.setPrevious(tail);
				tail = newNode;
			}
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 1;
		for (T e : this) {
			hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
		}
		
		return hashCode;
	}

	@Override
	public void clear() {
		if(count == 0) {
			throw new NoSuchElementException("Could not clear the list since the list is empty");
		} else if(count == 1) {
			head = null;
			tail = null;
		} else {
			Node<T> currentNode = head;
			while(currentNode != tail) {
				currentNode = currentNode.getNext();
				currentNode.getPrevious().setNext(null);
				currentNode.setPrevious(null);
			}
			head = null;
			tail = null;
		}
	}

	@Override
	public T get(int index) {
		
		if(index < 0 || index > count) throw new IndexOutOfBoundsException();
		
		Node<T> currentNode = head;
		
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		
		return currentNode.getElement();
	}

	@Override
	public T set(int index, T element) {
		if(index < 0 || index > count) throw new IndexOutOfBoundsException();
		
		Node<T> currentNode = head;
		
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		
		T returnElement = currentNode.getElement();
		currentNode.setElement(element);
		return returnElement;
		
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
