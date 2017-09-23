package Sets;

import LinkedLists.SinglyLinkedList;

/**
 * @author A2HP11
 *
 */
public class SetOperations {
	
	private SinglyLinkedList setsList;
	
	private SinglyLinkedList universeList;
	
	public SetOperations (String[] universe) {
		for (int i = 0; i < universe.length; i++) {
			universeList.add(universe[i]);
		}
	}
	
	public void addSet() {
		
	}
	
	public SinglyLinkedList getSetsList() {
		return setsList;
	}

	public SinglyLinkedList getUniverseList() {
		return universeList;
	}

	
}
