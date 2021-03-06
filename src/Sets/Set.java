package Sets;

import LinkedLists.SLNode;
import LinkedLists.SinglyLinkedList;

/**
 * @author H This class represents sets.
 */
public abstract class Set {

	/**
	 * List of items in the set in SLL form.
	 */
	private final SinglyLinkedList setList;

	/**
	 * Constructor in case the list is ready.
	 *
	 * @param list
	 *            previously built set.
	 */
	public Set(final SinglyLinkedList list) {
		this.setList = list;
	}


	/**
	 * Main constructor when reading input.
	 * @param setInput the input array of strings.
	 */
	public Set(final String[] setInput) {
		this.setList = makeSLL(setInput);
	}

	/**
	 * Constructor in case we know the boolean set.
	 *
	 * @param universeIn
	 *            universe of the set.
	 * @param setBoolIn
	 *            boolean array of existence of elements from universe.
	 */
	public Set(final Universe universeIn, final boolean[] setBoolIn) {
		final SinglyLinkedList setListBuilder = new SinglyLinkedList();
		SLNode SLNUI = universeIn.getSetList().getHead();
		for (final boolean element : setBoolIn) {
			if (element) {
				setListBuilder.add(SLNUI.getElement());
			}
			SLNUI = SLNUI.getNext();
		}
		this.setList = setListBuilder;
	}

	/**
	 * Finds the rest of the elements in the universe not existing in the set.
	 *
	 * @return complement of a set Returns null if the output set is empty.
	 */
	public abstract Set complement();

	/**
	 * Finds the set difference with another set.
	 *
	 * @param other
	 *            input set.
	 * @return the difference. Returns null if the output set is empty.
	 */
	public abstract Set difference(Set other);

	/**
	 * Getter for setList.
	 *
	 * @return setList as SLL.
	 */
	public SinglyLinkedList getSetList() {
		return this.setList;
	}

	/**
	 * Gets the intersection of this set and another.
	 *
	 * @param other
	 *            input set.
	 * @return intersection set. Returns null if the output set is empty.
	 */
	public abstract Set intersection(Set other);

	/**
	 * Auxiliary function used for detecting whether the input element is unique
	 * or a duplicate before adding it to the SLL.
	 *
	 * @param element
	 *            to be added to the list.
	 * @param setSLL
	 *            the list.
	 * @return true if it is unique and false otherwise.
	 */
	private boolean isUnique(final Object element, final SinglyLinkedList setSLL) {
		SLNode iteratorNode = setSLL.getHead();
		while (iteratorNode != null) {
			if (iteratorNode.getElement().equals(element)) {// Found duplicate
				return false;
			} else {
				iteratorNode = iteratorNode.getNext();
			}
		}
		return true;// No duplicates found.
	}

	/**
	 * Creates a SinglyLinkedList of the given array.
	 *
	 * @param setArr
	 *            any array of any object type. Usually, the param is a String
	 *            array.
	 * @return SLL generated that has the same elements as the input array.
	 */
	private SinglyLinkedList makeSLL(final String[] setArr) {
		final SinglyLinkedList setSLL = new SinglyLinkedList();
		for (String element : setArr) {
			element = element.trim();
			if (isUnique(element, setSLL) && !element.equals("")) {// Checks if
																	// it is
																	// unique
																	// first.
				setSLL.add(element);
			}
		}
		return setSLL;
	}

	/**
	 * Gets the union of this set and another.
	 *
	 * @param other
	 *            input set.
	 * @return union set. Returns null if the output set is empty.
	 */
	public abstract Set union(Set other);

}
