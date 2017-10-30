package view.Toolbar;

import java.util.List;

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
	private TextPromptView tp;

	public BackgroundOptionView(List<String> colorList) {

		super(PROMPT);

		this.colorList = colorList;

		makeChoiceBox();
	}
	
	public void makeChoiceBox() {

		cb.getItems().removeAll(cb.getItems());
		
		
		for (String color : colorList)
			cb.getItems().add(colorList.indexOf(color) + ". " + color);
		
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tp.runCommand("SetBackground", Integer.toString(newValue.intValue()));
			}
		});
		
		cb.setMinWidth(cb.getWidth());
	}
	
	public void addTextPrompt(TextPromptView tp) {
		this.tp = tp;
	}
}
