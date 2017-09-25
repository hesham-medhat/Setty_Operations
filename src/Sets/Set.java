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
	private SinglyLinkedList setList;

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
	 * Constructor in case we know the boolean set.
	 * 
	 * @param universeIn
	 *            universe of the set.
	 * @param setBoolIn
	 *            boolean array of existance of elements from universe.
	 */
	public Set(final Universe universeIn, final boolean[] setBoolIn) {
		SinglyLinkedList setListBuilder = new SinglyLinkedList();
		SLNode SLNUI = universeIn.getSetList().getHead();
		for (int i = 0; i < setBoolIn.length; i++) {
			if (setBoolIn[i]) {
				setListBuilder.add(SLNUI.getElement());
			}
			SLNUI = SLNUI.getNext();
		}
		this.setList = setListBuilder;
	}

	/**
	 * Constructor in case the list is ready.
	 * 
	 * @param list
	 *            previously built set.
	 */
	public Set(SinglyLinkedList list) {
		this.setList = list;
	}

	/**
	 * Getter for setList.
	 * 
	 * @return setList as SLL.
	 */
	public SinglyLinkedList getSetList() {
		return setList;
	}

	/**
	 * Creates a SinglyLinkedList of the given array.
	 * 
	 * @param setArr
	 *            any array of any object type. Usually, the param is a String
	 *            array.
	 * @return SLL generated that has the same elements as the input array.
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
	 * 
	 * @param other
	 *            input set.
	 * @return union set.
	 */
	public abstract Set union(Set other);

	/**
	 * Gets the intersection of this set and another.
	 * 
	 * @param other
	 *            input set.
	 * @return intersection set.
	 */
	public abstract Set intersection(Set other);

	/**
	 * Finds the rest of the elements in the universe not existing in the set.
	 * 
	 * @return complement of a set
	 */
	public abstract Set complement();

	/**
	 * Finds the set difference with another set.
	 * 
	 * @param other
	 *            input set.
	 * @return the difference.
	 */
	public abstract Set difference(Set other);

}
