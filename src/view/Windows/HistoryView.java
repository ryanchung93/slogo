package view.Windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.SLogoException;
import model.SaverLoader;
import view.ErrorWindow;

public class HistoryView {

	private static final String DELIMITER = "&&&";
	private Button clearButton;
	private Button undoButton;
	private String lastCommand;
	private List<String> historyList;
	private Text text;
	private VBox myHistory;
	private ScrollPane scrollPane;
	private GridPane view;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	private Consumer<String> myCommandConsumer;
	private Runnable myReset;

	public HistoryView(double height, Consumer<String> commandConsumer, Runnable reset) {
		historyList = new ArrayList<>();

		myCommandConsumer = commandConsumer;
		myReset = reset;

		view = new GridPane();
		view.setMinHeight(height);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(70);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(10);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(10);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(100);

		view.getRowConstraints().addAll(row1, row2, row3);
		view.getColumnConstraints().addAll(col1);
		// ta = createTA(height);
		// ta = createTA(1);

		text = new Text(myResources.getString(("HistoryView")));
		text.setFill(Color.WHITE);

		myHistory = new VBox();
		myHistory.setMinHeight(height);
		myHistory.setId("var-VBox");
		scrollPane = new ScrollPane();
		scrollPane.setContent(myHistory);

		clearButton = makeButton("Clear History", e -> clear());
		undoButton = makeButton("Undo", e -> undo());

		view.add(text, 0, 0);
		view.add(scrollPane, 0, 1);
		view.add(clearButton, 0, 2);
		view.add(undoButton, 0, 3);
	}

	/*************************** PUBLIC METHODS ********************************/

	// add to interface
	public void updateHistory(String newCode) {
		lastCommand = newCode;
		historyList.add(lastCommand);
		Hyperlink t = new Hyperlink(newCode);
		t.setOnAction(event -> {
			try {
				myCommandConsumer.accept(newCode);
			} catch (SLogoException e) {
				new ErrorWindow(e.getMessage());
			}
		});
		myHistory.getChildren().addAll(t);
		// ta.appendText("\n" + newCode);
	}

	public Parent getParent() {
		return view;
	}

	public void clear() {
		myHistory.getChildren().clear();
	}

	public void save(String filePath) {
		StringBuilder sb = new StringBuilder();
		for (String s : historyList)
			sb.append(s + " " + DELIMITER + " ");
		SaverLoader.save(sb.toString(), filePath);
	}

	public void load(String filePath) {
		historyList = new ArrayList<String>(Arrays.asList(((String) SaverLoader.load(filePath)).split("\\s" + DELIMITER + "\\s")         ));
		myHistory.getChildren().removeAll(myHistory.getChildren());
		for (String s : historyList) {
			Hyperlink t = new Hyperlink(s);
			t.setOnAction(event -> {
				try {
					myCommandConsumer.accept(s);
				} catch (SLogoException e) {
					new ErrorWindow(e.getMessage());
				}
			});
			myHistory.getChildren().add(t);
		}
	}

	/*************************** PRIVATE METHODS ********************************/

	private Button makeButton(String name, EventHandler<ActionEvent> e) {
		Button ret = new Button(name);
		ret.setOnAction(e);
		return ret;
	}

	private void undo() {
		// TODO make redo, store undone commands in a separate Stack, throw error/do
		// nothing when undoing too many times
		clear();
		int size = historyList.size();
		if (size != 0) {
			historyList.remove(size - 1);
		}
		myReset.run();
		for (String command : historyList) {
			myCommandConsumer.accept(command);
		}
	}
}
