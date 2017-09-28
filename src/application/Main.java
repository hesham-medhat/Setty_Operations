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
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class Main extends Application {

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

	private static Stage primarySharedStage;

	private static Universe mUniverse;
	private static SinglyLinkedList allSets;

	@Override
	public void start(Stage primaryStage) {
		primarySharedStage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Setty Operations");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * @param nextClicked
	 */
	public void nextClicked(ActionEvent nextClicked) {
		try {
			FXMLLoader loader = new FXMLLoader(
			        getClass().getResource("application.fxml")
			);
			loader.setController(this);
			Parent root = (Parent) loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("application.fxml"));
			Scene scene = new Scene(root);
			SetsListText.appendText("• Universe\n" + mUniverse.getSetList().toString() + "\n");
			primarySharedStage.setScene(scene);
			primarySharedStage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @param addSet
	 */
	public void addSet(ActionEvent addSet) {
		Subset subset;
		try {
			feedbackAS.setText("");
			subset = new Subset(mUniverse, getSetList(newSetText));
			allSets.add(subset);
			SetsListText.appendText("• Set #" + (allSets.getSize() - 1) + "\n" + subset.getSetList().toString() + "\n");
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
					allSets.getSize() - 1, 0, 1);
			Set1.setValueFactory(valueFactory);
			SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
					allSets.getSize() - 1, 0, 1);
			Set2.setValueFactory(valueFactory2);
			newSetText.setText("");
		} catch (RuntimeException e) {
			feedbackAS.setTextFill(Paint.valueOf("RED"));
			feedbackAS.setText(e.getMessage());
		}
	
	}

	/**
	 * @param addUniverse
	 */
	public void addUniverse(ActionEvent addUniverse) {
		String[] setInput = getSetList(universeText);
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
	 * This method scan user's input and reformat it into a SLL representing set
	 * of elements.
	 * 
	 * @param scan
	 *            Scanner used to scan input inserted by the user.
	 * @return SLL of the inserted list.
	 */
	private static String[] getSetList(TextField textInput) {
		String inputString = textInput.getText();
		textInput.setText("");
		inputString.trim();
		if (inputString.length() > 0) {
			String[] setString = inputString.split(",");
			return setString;
		} else {
			return null;
		}
	}

	/**
	 * @param e
	 */
	public void onClickButton(ActionEvent e) {
		Set firstSet;
		Set secondSet;

		if (e.getSource() == complement1) {
			firstSet = (Set) allSets.get(Set1.getValue());
			firstSet = firstSet.complement();
			output.setText("First set's complement:\n");
			displaySet(firstSet);
			
		}else if (e.getSource() == complement2) {
			secondSet = (Set) allSets.get(Set2.getValue());
			secondSet = secondSet.complement();
			output.setText("Second set's complement:\n");
			displaySet(secondSet);
			
		} else if (e.getSource() == union) {
			firstSet = (Set) allSets.get(Set1.getValue());
			secondSet = (Set) allSets.get(Set2.getValue());
			firstSet = firstSet.union(secondSet);
			output.setText("Union of first set and second set:\n");
			displaySet(firstSet);
			
		} else if (e.getSource() == intersection) {
			firstSet = (Set) allSets.get(Set1.getValue());
			secondSet = (Set) allSets.get(Set2.getValue());
			firstSet = firstSet.intersection(secondSet);
			output.setText("Intersection of first set and second set:\n");
			displaySet(firstSet);
			
		} else { // difference
			firstSet = (Set) allSets.get(Set1.getValue());
			secondSet = (Set) allSets.get(Set2.getValue());
			firstSet = firstSet.difference(secondSet);
			output.setText("Difference between first set and second set:\n");
			displaySet(firstSet);
		}
	}
	
	/**
	 * @param set
	 */
	private void displaySet(Set set) {
		if(set != null) {
			output.appendText(set.getSetList().toString());
		} else {
			output.appendText("Phi - Empty set.");
		}
	}
}
