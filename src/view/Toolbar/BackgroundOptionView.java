package view.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import view.Animation.TextPromptView;

/**
 * Class that allows users to select a canvas color from a choice box.
 * 
 * @author DavidTran
 *
 */
public class BackgroundOptionView extends OptionView {

	private static final String PROMPT = "BackgroundPrompt";
	private List<String> colorList;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private TextPromptView tp;

	public BackgroundOptionView() {

		super(PROMPT);

		colorList = new ArrayList<String>(
				Arrays.asList(myResources.getString("BackgroundColors").replaceAll("\\s+", "").split(",")));

		for (String color : colorList)
			cb.getItems().add(colorList.indexOf(color) + ". " + color);

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tp.runCommand("SetBackground", Integer.toString(newValue.intValue()));

			}
		});
	}
	
	public void addTextPrompt(TextPromptView tp) {
		this.tp = tp;
	}
}
