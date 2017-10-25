package view.SidePane;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import view.API.SubcomponentViewAPI;

public class HistoryView implements SubcomponentViewAPI {

	private TextArea ta;
	private VBox view;
	private Button clearButton;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	public HistoryView(double height) {
		view = new VBox();
		view.setMinHeight(height);

		clearButton = makeButton("Clear History", e -> clear());
		ta = createTA(height);
		view.getChildren().addAll(ta, clearButton);
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

	private TextArea createTA(double height) {
		TextArea ret = new TextArea();
		ret.setMinHeight(height);
		ret.setWrapText(true);
		ret.setEditable(false);
		ret.appendText(myResources.getString("HistoryView") + "\n");
		return ret;
	}

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
