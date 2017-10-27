package view.Windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class HistoryView extends Window {

	private Button clearButton;

	public HistoryView(double height) {
		super(height);
		ta.appendText(myResources.getString("HistoryView") + "\n");
		clearButton = makeButton("Clear History", e -> clear());
		view.getChildren().addAll(clearButton);

	}

	/*************************** PUBLIC METHODS ********************************/

	// add to interface
	public void updateHistory(String newCode) {
		ta.appendText("\n" + newCode);
	}

	public Parent getParent() {
		return view;
	}

	/*************************** PRIVATE METHODS ********************************/

	private Button makeButton(String name, EventHandler<ActionEvent> e) {
		Button ret = new Button(name);
		ret.setOnAction(e);
		return ret;
	}

	private void clear() {
		ta.clear();
		ta.appendText(myResources.getString("HistoryView") + "\n");
	}
}
