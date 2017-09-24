package Sets;

/**
 * This class represents the universe.
 * @author H
 */
public class Universe extends Set {

	public Universe(final String[] setInput) {
		super(setInput);
	}

	@Override
	public Set union(Set other, boolean secondCall) {
		return this;
	}

	@Override
	public Set intersection(Set other, boolean secondCall) {
		return other;
	}

	@Override
	public Set complement() {
		return null;
	}
	
}
