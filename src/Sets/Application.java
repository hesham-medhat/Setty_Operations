package Sets;

import java.util.Scanner;

/**
 * Contains the main method and runs the application.
 * @author Merit Victor
 *
 */
public class Application {

	/**
	 * Main method for the application.
	 * @param args arguments for main.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the universe space-separated in one line.");
		
		Scanner uIn = new Scanner(System.in);
		String sInput = uIn.nextLine();
		String [] universe = sInput.split(" ");
		SetOperations op = new SetOperations(universe);
		uIn.close();
		
	}

}
