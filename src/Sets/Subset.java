package Sets;

import LinkedLists.SLNode;
import LinkedLists.SinglyLinkedList;

/**
 * @author H This class represents a subset of a universe.
 */
public class Subset extends Set {

	/**
	 * Acts as a bit map for the existence of the elements in this set in the
	 * universe that it belongs to.
	 */
	private boolean[] setBool;

	/**
	 * The universe that this subset belongs to.
	 */
	private Universe universe;

	/**
	 * Constructor that calls the super "Set" constructor to build the SLL of
	 * set.
	 * 
	 * @param universeIn
	 *            universe as object.
	 * @param setInput
	 *            set content input as string array.
	 */
	public Subset(final Universe universeIn, final String[] setInput) {
		super(setInput);
		makeBoolSet(universeIn.getSetList(), this.getSetList());
		this.universe = universeIn;
	}

	/**
	 * @param universeIn
	 * @param setBoolIn
	 */
	public Subset(final Universe universeIn, final boolean[] setBoolIn) {
		super(universeIn, setBoolIn);
		this.universe = universeIn;
	}

	/**
	 * Getter for setBool.
	 * 
	 * @return setBool
	 */
	public boolean[] getSetBool() {
		return setBool;
	}

	/**
	 * Constructs the setBool to be ready for operations.
	 * 
	 * @param universe
	 *            in a SLL form.
	 * @param set
	 *            in a SLL form.
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

	@Override
	public Set union(Set other) {
		if (other instanceof Universe) {
			return this.universe;
		} else {
			boolean isEmpty = true;
			boolean[] otherBool = ((Subset) other).getSetBool();
			if (otherBool.length != this.setBool.length) {
				throw new RuntimeException("Error: Different universes.");
			}
			boolean[] newSetBool = new boolean[setBool.length];
			for (int i = 0; i < this.setBool.length; i++) {
				if (setBool[i] || otherBool[i]) {
					isEmpty = false;
					newSetBool[i] = true;
				} else {
					newSetBool[i] = false;
				}
			}
			if (!isEmpty) {
				Subset unionSet = new Subset(universe, newSetBool);
				return unionSet;
			} else {
				return null;
			}
		}
	}

	@Override
	public Set intersection(Set other) {
		if (other instanceof Universe) {
			return this;
		} else {
			boolean intersected = false;
			boolean[] otherBool = ((Subset) other).getSetBool();
			if (otherBool.length != this.setBool.length) {
				throw new RuntimeException("Error: Different universes.");
			}
			boolean[] newSetBool = new boolean[setBool.length];
			for (int i = 0; i < this.setBool.length; i++) {
				if (setBool[i] && otherBool[i]) {
					newSetBool[i] = true;
					intersected = true;
				} else {
					newSetBool[i] = false;
				}
			}
			if (intersected) {
				Subset intersectionSet = new Subset(universe, newSetBool);
				return intersectionSet;
			} else {
				return null;
			}
		}
	}

	@Override
	public Set complement() {
		boolean isUnivers = true;
		boolean[] boolSet = new boolean[this.getSetBool().length];
		SLNode universeNode = this.universe.getSetList().getHead();
		for (int i = 0; i < this.setBool.length; i++) {
			if (!setBool[i]) {
				isUnivers = false;
				boolSet[i] = true;
			}
			universeNode = universeNode.getNext();
		}
		if (!isUnivers) {
			Subset complementSet = new Subset(this.universe, boolSet);
			return complementSet;
		} else {
			return null;
		}
	}
}
