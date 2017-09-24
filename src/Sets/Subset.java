package Sets;

import LinkedLists.SLNode;
import LinkedLists.SinglyLinkedList;

/**
 * @author H
 * This class represents a subset of a universe.
 */
public class Subset extends Set {

	/**
	 * Acts as a bit map for the existance of the elements in this set
	 * in the universe that it belongs to.
	 */
	private boolean[] setBool;
	
	/**
	 * Getter for setBool.
	 * @return setBool
	 */
	public boolean[] getSetBool() {
		return setBool;
	}

	private Universe universe;
	
	
	/**
	 * @return the universe
	 */
	public Universe getUniverse() {
		return universe;
	}

	/**
	 * Constructs the setBool to be ready for operations.
	 * @param universe in a SLL form.
	 * @param set in a SLL form.
	 */
	private void makeBoolSet(SinglyLinkedList universe, SinglyLinkedList set) {
		SLNode iSet = set.getHead();
		int index;
		setBool = new boolean[universe.getSize()];
		while (iSet != null) {
			index = universe.contains(iSet.getElement());
			if (index == -1) {
				throw new RuntimeException("An element in the set doesn't exist in the universe!");
			} else {
				setBool[index] = true;
				iSet = iSet.getNext();
			}
		}
	}
	
	public Subset(final Universe universeIn, final String[] setInput) {
		super(setInput);
		this.universe = universeIn;
	}
	
}
