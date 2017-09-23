package LinkedLists;

/**
 * Singly node class.
 * @author H
 *
 */
public class SLNode {

	/**
	 * The element in the node.
	 */
	private Object element;
	/**
	 * Next node in the list.
	 */
	private SLNode next;

	/**
	 * Constructor.
	 * @param elementI	input element.
	 * @param nextI	input next.
	 */
	public SLNode(final Object elementI, final SLNode nextI) {
		this.element = elementI;
		this.next = nextI;
	}

	/**
	 * Getter.
	 * @return element.
	 */
	public Object getElement() {
		return element;
	}

	/**
	 * Getter.
	 * @return next node.
	 */
	public SLNode getNext() {
		return next;
	}

	/**
	 * Setter.
	 * @param elementI element input.
	 */
	public void setElement(final Object elementI) {
		this.element = elementI;
	}

	/**
	 * Setter.
	 * @param nextI	next input node.
	 */
	public void setNext(final SLNode nextI) {
		this.next = nextI;
	}

}
