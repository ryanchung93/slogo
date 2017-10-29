package view.Toolbar;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.SubcomponentViewAPI;
import view.Animation.TextPromptView;

public class PenButtons implements SubcomponentViewAPI {

	private VBox view;
	private Button upButton;
	private Button downButton;
	private TextPromptView tp;
	private static final ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private static final String PROMPT = myResources.getString("PenButtonsPrompt");

	public PenButtons() {
		view = new VBox();

		Label prompt = new Label(PROMPT);
		HBox buttonPanel = new HBox();
		upButton = makeButton(myResources.getString("UpButtonCommand"), e -> tp.runCommand("PenUp", ""));
		downButton = makeButton(myResources.getString("DownButtonCommand"), e -> tp.runCommand("PenDown", ""));
		buttonPanel.getChildren().addAll(upButton, downButton);
		view.getChildren().addAll(prompt, buttonPanel);
		view.setAlignment(Pos.CENTER);
	}

	private Button makeButton(String label, EventHandler<ActionEvent> e) {
		Button ret = new Button(label);
		ret.setOnAction(e);
		return ret;
	}

	@Override
	public Parent getParent() {
		return view;
	}

	public void addTextPrompt(TextPromptView tp) {
		this.tp = tp;
	}
}
