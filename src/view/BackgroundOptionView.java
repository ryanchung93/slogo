package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import view.API.BackgroundOptionDisplay;
import view.API.BackgroundOptionListener;

public class BackgroundOptionView implements BackgroundOptionDisplay {

	private HBox optionView;
	private Label prompt;
	private ChoiceBox<String> cb;
	private List<String> colorList;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private BackgroundOptionListener listener;

	public BackgroundOptionView() {

		optionView = new HBox();
		
		prompt = new Label(myResources.getString("BackgroundPrompt"));
		
		colorList = new ArrayList<String>(
				Arrays.asList(myResources.getString("BackgroundColors").replaceAll("\\s+", "").split(",")));

		cb = new ChoiceBox<String>(FXCollections.observableArrayList(colorList));
		cb.setTooltip(new Tooltip("Select the background color"));
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(newValue.intValue());
				listener.backgroundColorChange(Color.valueOf((colorList.get(newValue.intValue()))));

			}
		});
		
		optionView.getChildren().addAll(prompt, cb);
	}

	@Override
	public Parent getParent() {
		return optionView;
	}

	@Override
	public void addBackgroundOptionListener(BackgroundOptionListener l) {
		listener = l;

	}

}
