package application;

import java.io.IOException;

import LinkedLists.SinglyLinkedList;
import Sets.Set;
import Sets.Subset;
import Sets.Universe;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Main application class.
 * 
 * @author H&M
 *
 */
public class Main extends Application {

	/**
	 * Reference for primary stage for switching scenes.
	 */
	private static Stage primarySharedStage;

	/**
	 * Universe of all sets.
	 */
	private static Universe mUniverse;

	/**
	 * List of all sets including the universe.
	 */
	private static SinglyLinkedList allSets;

	@FXML
	private Label feedbackAU;
	@FXML
	private Button next;
	@FXML
	private Button addSet;
	@FXML
	private Spinner<Integer> Set1;
	@FXML
	private Spinner<Integer> Set2;
	@FXML
	private Button addUniverse;
	@FXML
	private TextField universeText;
	@FXML
	private TextField newSetText;
	@FXML
	private TextArea SetsListText;
	@FXML
	private Label feedbackAS;
	@FXML
	private Button complement1;
	@FXML
	private Button complement2;
	@FXML
	private Button union;
	@FXML
	private Button intersection;
	@FXML
	private Button difference;
	@FXML
	private TextArea output;

	
	/**
	 * This method scan user's input and reformat it into a SLL representing set
	 * of elements.
	 *
	 * @param TextField
	 *            from which we take user's input.
	 * @return array of Strings, will be used to make the new set.
	 */
	static String[] getSetList(final TextField textInput) {
		final String inputString = textInput.getText();
		inputString.trim();
		if (inputString.length() > 0) {
			final String[] setString = inputString.split(",");
			return setString;
		} else {
			return null;
		}
	}
	
	/**
	 * Adds a set into the list of all sets.
	 * 
	 * @param addSet
	 *            event when button clicked.
	 */
	public void addSet(final ActionEvent addSet) {
		Subset subset;
		feedbackAS.setText("");
		if (!newSetText.getText().equals("")) {
			try {
				subset = new Subset(mUniverse, getSetList(newSetText));
				Main.allSets.add(subset);
				SetsListText.appendText(
						"• Set #" + (allSets.getSize() - 1) + "\n" + subset.getSetList().toString() + "\n");
				updateSpinner(0, 0);
				newSetText.setText("");
			} catch (final RuntimeException e) {
				feedbackAS.setTextFill(Paint.valueOf("RED"));
				feedbackAS.setText(e.getMessage());
			}
		} else {
			feedbackAS.setTextFill(Paint.valueOf("RED"));
			feedbackAS.setText("Empty set!");
		}
	}

	/**
	 * Adds the universe.
	 * 
	 * @param addUniverse
	 *            the event triggered
	 */
	public void addUniverse(final ActionEvent addUniverse) {
		final String[] setInput = getSetList(universeText);
		if (setInput != null) {
			mUniverse = new Universe(setInput);
			allSets = new SinglyLinkedList();
			allSets.add(mUniverse);
			feedbackAU.setTextFill(Paint.valueOf("BLUE"));
			feedbackAU.setText("Success!");
			next.setDisable(false);
		} else {
			feedbackAU.setText("Empty universe!");
		}
	}

	/**
	 * Displays the set into the output text. Shows phi if the set is empty.
	 * 
	 * @param set
	 *            to be displayed.
	 */
	private void displaySet(final Set set) {
		if (set != null) {
			allSets.add(set);
			SetsListText.appendText(
					"• Set #" + (allSets.getSize() - 1) + "\n" + set.getSetList().toString() + "\n");
			output.appendText("(Set #" + (allSets.getSize() - 1) + ") " +  set.getSetList().toString());
		} else {
			output.appendText("Phi - Empty set.");
		}
	}

	/**
	 * Switches scenes to the new one after the universe is accepted.
	 * 
	 * @param nextClicked
	 *            event triggered when Next button is clicked.
	 */
	public void nextClicked(final ActionEvent nextClicked) {
		try {
			final FXMLLoader loader = new FXMLLoader(getClass().getResource("application.fxml"));
			loader.setController(this);
			final Parent root = (Parent) loader.load();
			final Scene scene = new Scene(root);
			SetsListText.appendText("• Universe\n" + mUniverse.getSetList().toString() + "\n");
			Main.primarySharedStage.setScene(scene);
			Main.primarySharedStage.show();
		} catch (final IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Activates the specified operation on the set(s).
	 * Also adds output set to the set of lists.
	 * 
	 * @param e
	 *            event when button clicked.
	 */
	public void onClickButton(final ActionEvent e) {
		Set firstSet ;
		Set secondSet;
		
		if (e.getSource().equals(complement1)) {
			firstSet = (Set) allSets.get(Set1.getValue());
			firstSet = firstSet.complement();
			output.setText("First set's complement:\n");
			displaySet(firstSet);
			updateSpinner(Set1.getValue(), Set2.getValue());
			
		} else if (e.getSource().equals(complement2)) {
			secondSet = (Set) allSets.get(Set2.getValue());
			secondSet = secondSet.complement();
			displaySet(secondSet);
			updateSpinner(Set1.getValue(), Set2.getValue());
			
		} else if (e.getSource().equals(union)){
			firstSet = (Set) allSets.get(Set1.getValue());
			secondSet = (Set) allSets.get(Set2.getValue());
			firstSet = firstSet.union(secondSet);
			output.setText("Union of first set and second set:\n");
			displaySet(firstSet);
			updateSpinner(Set1.getValue(), Set2.getValue());
			
		} else if (e.getSource().equals(intersection)) {
			firstSet = (Set) allSets.get(Set1.getValue());
			secondSet = (Set) allSets.get(Set2.getValue());
			firstSet = firstSet.intersection(secondSet);
			displaySet(firstSet);
			updateSpinner(Set1.getValue(), Set2.getValue());
			
		} else if (e.getSource().equals(difference)) { 
			firstSet = (Set) Main.allSets.get(Set1.getValue());
			secondSet = (Set) Main.allSets.get(Set2.getValue());
			firstSet = firstSet.difference(secondSet);
			output.setText("Difference of first set from second set:\n");
			displaySet(firstSet);
			
		} else {
			return;
		}
	}
	
	public void updateSpinner(int set1Index, int set2Index) {
		final SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
				allSets.getSize() - 1, set1Index, 1);
		Set1.setValueFactory(valueFactory);
		final SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
				allSets.getSize() - 1, set2Index, 1);
		Set2.setValueFactory(valueFactory2);
	}

	@Override
	public void start(final Stage primaryStage) {
		Main.primarySharedStage = primaryStage;
		try {
			final Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
			final Scene scene = new Scene(root);
			primaryStage.setTitle("Setty Operations");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (final IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Main function.
	 * 
	 * @param args
	 *            arguments for main.
	 */
	public static void main(final String[] args) {
		Application.launch(args);
	}
}
