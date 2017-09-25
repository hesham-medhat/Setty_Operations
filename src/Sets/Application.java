package Sets;

import java.util.InputMismatchException;
import java.util.Scanner;

import LinkedLists.SinglyLinkedList;

/**
 * Contains the main method and runs the application.
 * @author Merit Victor
 *
 */
public class Application {

	/**
	 * 
	 */
	private static Universe mUnivers;
	
	/**
	 * 
	 */
	private static int numOfSubsets;
	
	/**
	 * 
	 */
	private static Set[] allsets;
	
	/**
	 * 
	 */
	private static int scannedInt = -2;
	
	/**
	 * Main method for the application.
	 * @param args arguments for main.
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the universe space-separated in one line.");
		
		mUnivers = new Universe(getSetList(in));
		
		System.out.println("Enter number of subsets.");

		try {
			numOfSubsets = in.nextInt();
			allsets = new Set[numOfSubsets + 1];
			allsets[0] = mUnivers;
		} catch (InputMismatchException exception) {
			System.out.println(exception.getMessage());
		}
		
		String[] sll = new String[mUnivers.getSetList().size()];
		for(int i = 1; i < allsets.length; i++) {
			System.out.println("Enter subset #" + (i) + " in one line.");
			sll = getSetList(in);
			allsets[i] = new Subset(mUnivers, sll);
		}
		
		Set set1;
		Set set2;
		
		int index = getSetIndex(1, in);
		if(index != -2) {
			set1 = allsets[index];
		} else {
			System.err.println("Error occured.");
			System.exit(1);
		}

		index = getSetIndex(2, in);
		if(index != -2) {
			set2 = allsets[index];
		} else {
			System.err.println("Error occured.");
			System.exit(1);
		}
		
		
		
		in.close();
		
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
	
	private static int getSetIndex(int setNum, final Scanner in) {
		System.out.println("Enter number of #" + setNum + " set, 0 for universe, -1 to end");
		try {
			scannedInt = in.nextInt();
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			getSetIndex(setNum, in);
		}
		
		if(scannedInt != -1 &&  0 <= scannedInt && scannedInt <= numOfSubsets) {
			return scannedInt;
		} else if (scannedInt == -1 ) {
			System.out.println("Bye bye :D");
			System.exit(0);
		} else {
				System.out.println("Invalid input.");
				getSetIndex(setNum, in);
			}
		return -2;
		} 
}
