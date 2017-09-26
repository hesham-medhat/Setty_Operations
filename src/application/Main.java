package application;

import java.io.IOException;

import LinkedLists.SinglyLinkedList;
import Sets.Set;
import Sets.Subset;
import Sets.Universe;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
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

	private static Stage primarySharedStage;

	private static Universe mUniverse;
	private static SinglyLinkedList allSets;

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
	private Button complement;
	@FXML
	private Button union;
	@FXML
	private Button intersection;
	@FXML
	private Button difference;
	@FXML
	private TextArea output;

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

	public void nextClicked(ActionEvent nextClicked) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("application.fxml"));
			Scene scene = new Scene(root);
			if (primarySharedStage == null) {
				System.out.println("Aywa null");
			}
			primarySharedStage.setScene(scene);
			primarySharedStage.show();
		} catch (IOException e1) {
			// Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void addSet(ActionEvent addSet) {
		Subset subset = new Subset(mUniverse, getSetList(newSetText));
		allSets.add(subset);
		SetsListText.appendText("• Set #" + (allSets.getSize() - 1) + "\n" + subset.getSetList().toString() + "\n");
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
				allSets.getSize() - 1, 0, 1);
		Set1.setValueFactory(valueFactory);
		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
				allSets.getSize() - 1, 0, 1);
		Set2.setValueFactory(valueFactory2);
	}

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
		// stop this button.
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
		inputString.trim();
		if (inputString.length() > 0) {
			String[] setString = inputString.split(",");
			return setString;
		} else {
			return null;
		}
	}

	public void onClickButton(ActionEvent e) {
		Set firstSet;
		Set secondSet;

		if (e.getSource() == complement) {
			firstSet = (Set) allSets.get(getSetIndex(Set1));
			firstSet = firstSet.complement();
			if(firstSet != null) {
				output.setText(firstSet.getSetList().toString());
			} else {
				output.setText("Phi - Empty set.");
			}
			
		} else if (e.getSource() == union) {
			firstSet = (Set) allSets.get(getSetIndex(Set1));
			secondSet = (Set) allSets.get(getSetIndex(Set2));
			
		} else if (e.getSource() == intersection) {
			firstSet = (Set) allSets.get(getSetIndex(Set1));
			secondSet = (Set) allSets.get(getSetIndex(Set2));
			
		} else { // difference
			firstSet = (Set) allSets.get(getSetIndex(Set1));
			
		}
	}

	private int getSetIndex(Spinner<Integer> set) {
		return set.getValue();
	}

}
