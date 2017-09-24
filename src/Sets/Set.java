package Sets;

import LinkedLists.SinglyLinkedList;
import LinkedLists.SLNode;

/**
 * @author H
 * This class represents sets.
 */
public abstract class Set {

	/**
	 * List of items in the set in SLL form.
	 */
	private SinglyLinkedList setList;

	/**
	 * Getter for setList.
	 * @return setList as SLL.
	 */
	public SinglyLinkedList getSetList() {
		return setList;
	}

	/**
	 * Constructor.
	 * @param universe in a SLL form.
	 * @param setList in a SLL form.
	 */
	public Set(final String[] setInput) {
		this.setList = makeSLL(setInput);
	}
	
	private SinglyLinkedList makeSLL(final String[] setArr) {
		SinglyLinkedList setSLL = new SinglyLinkedList();
		for (int i = 0; i < setArr.length; i++) {
			setSLL.add(setArr[i]);
		}
		return setSLL;
	}

}
