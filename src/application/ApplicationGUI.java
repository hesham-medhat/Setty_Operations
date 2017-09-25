package application;

import java.util.Scanner;

import LinkedLists.SinglyLinkedList;
import Sets.Subset;
import Sets.Universe;

public class ApplicationGUI {

	private Universe mUniverse;
	private Scanner in = new Scanner(System.in);
	private SinglyLinkedList allSets;
	

	public void addUniverse() {
		this.mUniverse = new Universe(getSetList(in));
		allSets.add(mUniverse);
		//stop this button.
	}
	
	public void addSet() {
	    Subset subset = new Subset(mUniverse, getSetList(in));
		allSets.add(subset);
		//if successfully done, display button (Set #1)
	}
	
	
	
	/**
	 * This method scan user's input and reformat it into a SLL representing set of elements. 
	 * @param scan Scanner used to scan input inserted by the user.
	 * @return SLL of the inserted list.
	 */
	private static String[] getSetList(final Scanner scan) {
		String sInput = readFullLine(scan);
		String[] setString = sInput.split(" ");
		return setString;
	}
	
	/**
	 * This method to enable scanning the whole sentence.
	 * @param scan scanner
	 * @return line
	 */
	private static String readFullLine(final Scanner scan) {
		String line = "";
		line += scan.next();
		line += scan.nextLine();
		return line;
	}
	
	
}
