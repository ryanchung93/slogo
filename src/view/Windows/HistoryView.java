package view.Windows;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HistoryView {

	private Button clearButton;
	private Text text;
	private VBox myHistory;
	private ScrollPane scrollPane;
	private GridPane view;

	private Consumer<String> myCommandConsumer;

	public HistoryView(double height, Consumer<String> commandConsumer) {
		myCommandConsumer = commandConsumer;

		view = new GridPane();
		view.setMinHeight(height);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(80);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(10);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(100);

		view.getRowConstraints().addAll(row1, row2, row3);
		view.getColumnConstraints().addAll(col1);
		// ta = createTA(height);
		// ta = createTA(1);

		text = new Text("HistoryView");
		text.setFill(Color.WHITE);

		myHistory = new VBox();
		scrollPane = new ScrollPane();
		scrollPane.setContent(myHistory);

		clearButton = makeButton("Clear History", e -> clear());

		view.add(text, 0, 0);
		view.add(scrollPane, 0, 1);
		view.add(clearButton, 0, 2);
	}

	/*************************** PUBLIC METHODS ********************************/

	// add to interface
	public void updateHistory(String newCode) {
		Hyperlink t = new Hyperlink(newCode);
		t.setOnAction(e -> myCommandConsumer.accept(newCode));
		myHistory.getChildren().addAll(t);
		// ta.appendText("\n" + newCode);
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
		myHistory.getChildren().clear();
	}
}
