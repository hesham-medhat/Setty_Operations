package Sets;

import LinkedLists.SinglyLinkedList;

/**
 * This class represents the universe.
 * 
 * @author H
 */
/**
 * @author Merit Victor
 *
 */
public class Universe extends Set {

	/**
	 * Constructor that passes the setInput as string array.
	 * 
	 * @param setInput
	 *            in the form of a string array.
	 */
	public Universe(final String[] setInput) {
		super(setInput);
	}

	/**
	 * Constructor in case the list of elements is ready.
	 * 
	 * @param list
	 *            previously built list of elements.
	 */
	public Universe(SinglyLinkedList list) {
		super(list);
	}

	@Override
	public Set union(Set other) {
		return new Universe(this.getSetList());
	}

	@Override
	public Set intersection(Set other) {
		return other;
	}

	@Override
	public Set complement() {
		return null;
	}

	/**
	 * This is an additional method that returns the difference between the
	 * universe a and another subset. if the parameter (Set) is instance of
	 * universe, the method returns nulll as "phi".
	 */
	@Override
	public Set difference(Set other) {
		if (other instanceof Universe) {
			return null;
		} else {
			return other.complement();
		}
	}

}
