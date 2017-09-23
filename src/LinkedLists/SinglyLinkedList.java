package LinkedLists;

import LinkedLists.ILinkedList;

/**
 * SLL implementation.
 * @author H
 *
 */
public class SinglyLinkedList implements ILinkedList {

	/**
	 * Head of the list.
	 */
	private SLNode head;
	/**
	 * Tail of the list.
	 */
	private SLNode tail;
	/**
	 * Size of the list.
	 */
	private int size;

	/**
	 * Constructor. Probably useless.
	 */
	public SinglyLinkedList() {
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
			final SLNode firstNode = new SLNode(element, null);
			head = firstNode;
			tail = firstNode;
			size++;
			return;
		}
		if (index == 0) {
			final SLNode newHead = new SLNode(element, head);
			head = newHead;
			size++;
			return;
		}
		if (index == size) {
			add(element);
			return;
		}
		final SLNode newNode = new SLNode(element, null);
		final SLNode previous = getNode(index - 1);
		newNode.setNext(previous.getNext());
		previous.setNext(newNode);
		size++;
	}

	@Override
	public void add(final Object element) {
		if (isEmpty()) {
			final SLNode firstNode = new SLNode(element, null);
			head = firstNode;
			tail = firstNode;
			size++;
			return;
		}
		final SLNode lastNode = new SLNode(element, null);
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
		SLNode searcher = head;
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
	 * Gets the head.
	 * @return head of the list.
	 */
	public SLNode getHead() {
		return head;
	}

	/**
	 * Gets the node at specified index.
	 * @param index	given index.
	 * @return	that node.
	 */
	public SLNode getNode(final int index) {

		SLNode iterator = head;

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
	 * @return size of the list.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Getter.
	 * @return tail of the list.
	 */
	public SLNode getTail() {
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
		final SLNode previous = getNode(index - 1);
		previous.setNext(previous.getNext().getNext());
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
	 * Setter.
	 * @param headI	input head.
	 */
	public void setHead(final SLNode headI) {
		this.head = headI;
	}

	/**
	 * Setter.
	 * @param sizeI	input size.
	 */
	public void setSize(final int sizeI) {
		this.size = sizeI;
	}

	/**
	 * Setter.
	 * @param tailI	input tail.
	 */
	public void setTail(final SLNode tailI) {
		this.tail = tailI;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ILinkedList sublist(final int fromIndex, final int toIndex) {
		if (fromIndex > size || toIndex > size
				|| fromIndex < 0 || toIndex < 0
				|| fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		SLNode nodeToCopy = getNode(fromIndex);
		final SinglyLinkedList sublist = new SinglyLinkedList();

		for (int i = 0; i < toIndex - fromIndex + 1; i++) {
			sublist.add(nodeToCopy.getElement());
			nodeToCopy = nodeToCopy.getNext();
		}
		return sublist;
	}

}
