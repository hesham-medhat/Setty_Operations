package application;

import java.io.IOException;


import LinkedLists.SinglyLinkedList;
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

	private static Stage primarySharedStage;

	@Override
	public void start(Stage primaryStage) {
		primarySharedStage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Setty Operations");
			primaryStage.setScene(scene);
			primaryStage.show();
			//next.setOnAction(e-> nextClicked(e, primaryStage));
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
			//Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}


	private Universe mUniverse;
	private SinglyLinkedList allSets;

	@FXML
	private Button addUniverse;
	@FXML
	private TextField universeText;
	@FXML
	private TextField newSetText;
	@FXML
	private TextArea SetsListText;

	public void addSet(ActionEvent addSet) {
		Subset subset = new Subset(mUniverse, getSetList(newSetText));
		allSets.add(subset);
		SetsListText.appendText("Set #" + (allSets.getSize() - 1));
	}

	public void addUniverse(ActionEvent addUniverse) {
		String[] setInput = getSetList(universeText);
		if (setInput != null) {
			this.mUniverse = new Universe(setInput);
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

	

}
