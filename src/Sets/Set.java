package Sets;

import LinkedLists.SinglyLinkedList;
import LinkedLists.SLNode;

/**
 * @author H
 * This class represents sets.
 */
public class Set {

	private boolean[] setBool;

	/**
	 * Constructor.
	 * @param universe in a SLL form.
	 * @param setList in a SLL form.
	 */
	public Set(SinglyLinkedList universe, SinglyLinkedList setList) {
		makeBoolSet(universe, setList);
	}

	/**
	 * Getter for setBool.
	 * @return setBool
	 */
	public boolean[] getSetBool() {
		return setBool;
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

}
