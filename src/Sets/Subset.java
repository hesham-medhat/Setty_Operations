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
	 * Constructor in case we already have the list built.
	 *
	 * @param list
	 *            of elements in the subset.
	 */
	public Subset(final SinglyLinkedList list, final boolean[] setBoolIn) {
		super(list);
		setBool = setBoolIn;
	}

	/**
	 * Constructor in case the boolean array is ready.
	 *
	 * @param universeIn
	 *            universe
	 * @param setBoolIn
	 *            the readysetBool
	 */
	public Subset(final Universe universeIn, final boolean[] setBoolIn) {
		super(universeIn, setBoolIn);
		this.universe = universeIn;
	}

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

	@Override
	public Set complement() {
		boolean isUniverse = true;
		final boolean[] boolSet = new boolean[this.getSetBool().length];
		SLNode universeNode = this.universe.getSetList().getHead();
		for (int i = 0; i < this.setBool.length; i++) {
			if (!setBool[i]) {
				isUniverse = false;
				boolSet[i] = true;
			}
			universeNode = universeNode.getNext();
		}
		if (!isUniverse) {
			final Subset complementSet = new Subset(this.universe, boolSet);
			return complementSet;
		} else {
			return null;
		}
	}

	@Override
	public Set difference(final Set other) {
		final boolean[] otherBool = ((Subset) other).getSetBool();
		if (other instanceof Universe) {
			return null;
		} else {
			final boolean[] diffArr = new boolean[this.setBool.length];
			for (int i = 0; i < setBool.length; i++) {
				if (setBool[i] && !otherBool[i]) {
					diffArr[i] = true;
				}
			}
			final Subset difference = new Subset(this.universe, diffArr);
			return difference;
		}
	}

	/**
	 * Getter for setBool.
	 *
	 * @return setBool
	 */
	public boolean[] getSetBool() {
		return setBool;
	}

	@Override
	public Set intersection(final Set other) {
		if (other instanceof Universe) {
			return this;
		} else {
			boolean intersected = false;
			final boolean[] otherBool = ((Subset) other).getSetBool();
			if (otherBool.length != this.setBool.length) {
				throw new RuntimeException("Error: Different universes.");
			}
			final boolean[] newSetBool = new boolean[setBool.length];
			for (int i = 0; i < this.setBool.length; i++) {
				if (setBool[i] && otherBool[i]) {
					newSetBool[i] = true;
					intersected = true;
				} else {
					newSetBool[i] = false;
				}
			}
			if (intersected) {
				final Subset intersectionSet = new Subset(universe, newSetBool);
				return intersectionSet;
			} else {
				return null;
			}
		}
	}

	/**
	 * Constructs the setBool to be ready for operations.
	 *
	 * @param universe
	 *            in a SLL form.
	 * @param set
	 *            in a SLL form.
	 */
	private void makeBoolSet(final SinglyLinkedList universe, final SinglyLinkedList set) {
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
	public Set union(final Set other) {
		if (other instanceof Universe) {
			return this.universe;
		} else {
			boolean isEmpty = true;
			final boolean[] otherBool = ((Subset) other).getSetBool();
			if (otherBool.length != this.setBool.length) {
				throw new RuntimeException("Error: Different universes.");
			}
			final boolean[] newSetBool = new boolean[setBool.length];
			for (int i = 0; i < this.setBool.length; i++) {
				if (setBool[i] || otherBool[i]) {
					isEmpty = false;
					newSetBool[i] = true;
				} else {
					newSetBool[i] = false;
				}
			}
			if (!isEmpty) {
				final Subset unionSet = new Subset(universe, newSetBool);
				return unionSet;
			} else {
				return null;
			}
		}
	}
}
