package Sets;

import LinkedLists.SinglyLinkedList;

/**
 * @author H This class represents sets.
 */
public abstract class Set {

	/**
	 * List of items in the set in SLL form.
	 */
	private SinglyLinkedList setList;

	/**
	 * Getter for setList.
	 * 
	 * @return setList as SLL.
	 */
	public SinglyLinkedList getSetList() {
		return setList;
	}

	/**
	 * Constructor.
	 * 
	 * @param universe
	 *            in a SLL form.
	 * @param setList
	 *            in a SLL form.
	 */
	public Set(final String[] setInput) {
		this.setList = makeSLL(setInput);
	}

	/**
	 * Creates a SinglyLinkedList of the given array.
	 * @param setArr any array of any object type.
	 * Usually, the param is a String array.
	 * @return SLL generated that has the same elements as the
	 * input array.
	 */
	private SinglyLinkedList makeSLL(final Object[] setArr) {
		SinglyLinkedList setSLL = new SinglyLinkedList();
		for (int i = 0; i < setArr.length; i++) {
			setSLL.add(setArr[i]);
		}
		return setSLL;
	}

	/**
	 * Gets the union of this set and another.
	 * @param other input set.
	 * @return union set.
	 */
	public abstract Set union(Set other);

	/**
	 * Gets the intersection of this set and another.
	 * @param other input set.
	 * @return intersection set.
	 */
	public abstract Set intersection(Set other);

	/**
	 * Finds the rest of the elements in the universe
	 * not existing in the set.
	 * @return complement of a set
	 */
	public abstract Set complement();

}
