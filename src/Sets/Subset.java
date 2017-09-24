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

	/**
	 * The universe that this subset belongs to.
	 */
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
	
	/**
	 * Constructor that calls the super "Set" constructor
	 * to build the SLL of set.
	 * @param universeIn universe as object.
	 * @param setInput set content input as string array.
	 */
	public Subset(final Universe universeIn, final String[] setInput) {
		super(setInput);
		makeBoolSet(universeIn.getSetList(), this.getSetList());
		this.universe = universeIn;
	}

	public Subset(final Universe universeIn, final boolean[] setBoolIn) {
		super(universeIn, setBoolIn);
		this.universe = universeIn;
	}

	@Override
	public Set union(Set other, boolean secondCall) {
		if (!secondCall) {
			return other.union(this, true);
		} else {
			boolean[] otherBool = ((Subset) other).getSetBool();
			if (otherBool.length != this.setBool.length) {
				throw new RuntimeException("Error: Different universes.");
			}
			boolean[] newSetBool = new boolean[setBool.length];
			for (int i = 0; i < this.setBool.length; i++) {
				if (setBool[i] || otherBool[i]) {
					newSetBool[i] = true;
				} else {
					newSetBool[i] = false;
				}
			}
			Subset unionSet = new Subset(universe, newSetBool);
			return unionSet;
		}
	}

	@Override
	public Set intersection(Set other, boolean secondCall) {
		if (!secondCall) {
			return other.union(this, true);
		} else {
			boolean[] otherBool = ((Subset) other).getSetBool();
			if (otherBool.length != this.setBool.length) {
				throw new RuntimeException("Error: Different universes.");
			}
			boolean[] newSetBool = new boolean[setBool.length];
			for (int i = 0; i < this.setBool.length; i++) {
				if (setBool[i] && otherBool[i]) {
					newSetBool[i] = true;
				} else {
					newSetBool[i] = false;
				}
			}
			Subset intersectionSet = new Subset(universe, newSetBool);
			return intersectionSet;
		}
	}

	@Override
	public Set complement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
