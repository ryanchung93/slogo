package view.Toolbar;

import java.util.Optional;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.ErrorWindow;
import view.SubcomponentViewAPI;

public class WorkSpaceButtons implements SubcomponentViewAPI {

	private Button newButton;
	private Button saveButton;
	private Button loadButton;
	private VBox view;
	private Runnable newWorkspace;
	private Consumer<String> saveConsumer;
	private Consumer<String> loadConsumer;
	

	public WorkSpaceButtons(Runnable newWorkspace, Consumer<String> saveConsumer, Consumer<String> loadConsumer) {
		view = new VBox(new Label("WorkSpace Manager"));
		HBox buttonPanel = new HBox();
		newButton = makeButton("New", e -> newFile());
		saveButton = makeButton("Save", e -> saveFile());
		loadButton = makeButton("Load", e -> loadFile());
		buttonPanel.getChildren().addAll(newButton, saveButton, loadButton);
		view.getChildren().add(buttonPanel);
		view.setAlignment(Pos.BASELINE_CENTER);
		
		this.newWorkspace = newWorkspace;
		this.saveConsumer = saveConsumer;
		this.loadConsumer = loadConsumer;
	}

	private Button makeButton(String name, EventHandler<ActionEvent> e) {
		Button ret = new Button(name);
		ret.setOnAction(e);
		ret.setId("toolbar-button");
		return ret;
	}

	private void newFile() {
		newWorkspace.run();
	}

	private void saveFile() {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Save Workspace Dialog");
		dialog.setHeaderText("Save Workspace");
		dialog.setContentText("File name for saved workspace:");

		Optional<String> result = dialog.showAndWait();
		try {
			saveConsumer.accept(result.toString());
		} catch (NumberFormatException e) {
			new ErrorWindow(e.getMessage());
		}

		
	}

	private void loadFile() {

	}

	@Override
	public Parent getParent() {
		return view;
	}

}
