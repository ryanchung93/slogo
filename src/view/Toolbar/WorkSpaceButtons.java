package view.Toolbar;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.ErrorWindow;
import view.SubcomponentViewAPI;

public class WorkSpaceButtons implements SubcomponentViewAPI {

	private static final String WORKSPACE_DIRECTORY = "src/resources/data/";
	private Button newButton;
	private Button saveButton;
	private Button loadButton;
	private VBox view;
	private Runnable newWorkspace;
	private Consumer<String> saveConsumer;
	private Consumer<String> loadConsumer;
	private ChoiceBox<String> cb;
	private List<String> fileList;

	public WorkSpaceButtons(Runnable newWorkspace, Consumer<String> saveConsumer, Consumer<String> loadConsumer) {

		view = new VBox(new Label("WorkSpace Manager"));
		HBox panel = new HBox();
		newButton = makeButton("New", e -> newFile());
		saveButton = makeButton("Save", e -> saveFile());
		loadButton = makeButton("Load", e -> loadFile());

//		setupChoiceBox();

		panel.getChildren().addAll(newButton, saveButton, loadButton);
		view.getChildren().add(panel);
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
		if (result.isPresent())
			saveConsumer.accept(WORKSPACE_DIRECTORY + result.get());

	}

	private void loadFile() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Load Workspace Dialog");
		dialog.setHeaderText("Load Workspace");
		dialog.setContentText("Enter file name of workspace to load:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
			try {
				loadConsumer.accept(WORKSPACE_DIRECTORY + result.get());
			} catch (Exception e) {
				new ErrorWindow(e.getMessage());
			}
	}

	@Override
	public Parent getParent() {
		return view;
	}
	
	private List<String> createFileList(String path, String ext) {

		List<String> ret = new ArrayList<String>();

		File file = new File(path);
		File[] files = file.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name.toLowerCase().endsWith(ext)) {
					return true;
				} else {
					return false;
				}
			}
		});
		for (File f : files) {
			ret.add(f.getName());
		}
		
		for (String s : ret)
			s.replaceAll("_+$", "");
		return ret;
	}

}
