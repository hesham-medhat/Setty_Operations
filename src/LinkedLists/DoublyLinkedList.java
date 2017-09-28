package LinkedLists;

/**
 * Doubly Linked List Implementation.
 * 
 * @author H
 *
 */
public class DoublyLinkedList implements ILinkedList {

	/**
	 * Head of the list.
	 */
	private DLNode head;
	/**
	 * Tail of the list.
	 */
	private DLNode tail;
	/**
	 * Size of the list.
	 */
	private int size;

	/**
	 * Constructor.
	 *
	 */
	public DoublyLinkedList() {
		super();
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public void add(final int index, final Object element) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (isEmpty()) {
			final DLNode firstNode = new DLNode(element, null, null);
			head = firstNode;
			tail = firstNode;
			size++;
			return;
		}
		if (index == 0) {
			final DLNode newHead = new DLNode(element, head, null);
			head = newHead;
			size++;
			return;
		}

		if (index == size) {
			add(element);
			return;
		}
		final DLNode newNode = new DLNode(element, null, null);
		final DLNode previous = getNode(index - 1);
		previous.getNext().setPrevious(newNode);
		newNode.setNext(previous.getNext());
		previous.setNext(newNode);
		newNode.setPrevious(previous);
		size++;
	}

	@Override
	public void add(final Object element) {
		if (isEmpty()) {
			final DLNode firstNode = new DLNode(element, null, null);
			head = firstNode;
			tail = firstNode;
			size++;
			return;
		}
		final DLNode lastNode = new DLNode(element, null, null);
		lastNode.setPrevious(tail);
		tail.setNext(lastNode);
		size++;
		tail = lastNode;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int contains(final Object o) {
		if (isEmpty()) {
			return -1;
		}
		DLNode searcher = head;
		int indexFound = 0;
		while (searcher != null) {
			if (searcher.getElement().equals(o)) {
				return indexFound;
			} else {
				indexFound++;
				searcher = searcher.getNext();
			}
		}
		return -1;
	}

	@Override
	public Object get(final int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return getNode(index).getElement();
	}

	/**
	 * Gets a node at a specified index.
	 * 
	 * @return head of the list.
	 */
	public DLNode getHead() {
		return head;
	}

	/**
	 * Gets the node at specified index.
	 * 
	 * @param index
	 *            given index.
	 * @return that node.
	 */
	public DLNode getNode(final int index) {

		DLNode iterator = head;

		if (index > size) {
			return null;
		}

		for (int i = 0; i < index; i++) {
			iterator = iterator.getNext();
		}
		return iterator;

	}

	/**
	 * Gets the size.
	 * 
	 * @return size of the list.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Getter.
	 * 
	 * @return tail of the list.
	 */
	public DLNode getTail() {
		return tail;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void remove(final int index) {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			if (head == tail) { // If there is only one element.
				clear();
				return;
			}
			head = head.getNext();
			size--;
			return;
		}
		if (index == size - 1) {
			tail = getNode(size - 2);
			tail.setNext(null);
			size--;
			return;
		}
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		final DLNode previous = getNode(index - 1);
		previous.setNext(previous.getNext().getNext());
		previous.getNext().setPrevious(previous);
		size--;
		return;
	}

	@Override
	public void set(final int index, final Object element) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		getNode(index).setElement(element);
	}

	/**
	 * Setter for head.
	 * 
	 * @param headI
	 *            input.
	 */
	public void setHead(final DLNode headI) {
		this.head = headI;
	}

	/**
	 * Setter for size.
	 * 
	 * @param sizeI
	 *            input.
	 */
	public void setSize(final int sizeI) {
		this.size = sizeI;
	}

	/**
	 * Setter for tail.
	 * 
	 * @param tailI
	 *            input.
	 */
	public void setTail(final DLNode tailI) {
		this.tail = tailI;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ILinkedList sublist(final int fromIndex, final int toIndex) {

		if (fromIndex > size || toIndex > size || fromIndex < 0 || toIndex < 0 || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		DLNode nodeToCopy = getNode(fromIndex);
		final DoublyLinkedList sublist = new DoublyLinkedList();

		for (int i = 0; i < toIndex - fromIndex + 1; i++) {
			sublist.add(nodeToCopy.getElement());
			nodeToCopy = nodeToCopy.getNext();
		}
		return sublist;
	}

}
