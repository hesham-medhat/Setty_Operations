package Sets;

/**
 * This class represents the universe.
 * 
 * @author H
 */
public class Universe extends Set {

	/**
	 * @param setInput
	 */
	public Universe(final String[] setInput) {
		super(setInput);
	}

	@Override
	public Set union(Set other) {
		return this;
	}

	@Override
	public Set intersection(Set other) {
		return other;
	}

	@Override
	public Set complement() {
		return null;
	}

}
