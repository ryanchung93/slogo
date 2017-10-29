package view.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import view.Animation.TextPromptView;

/**
 * Class that allows users to select a pen color from a choice box.
 * 
 * @author taekwhunchung
 *
 */
public class PenOptionView extends OptionView {

	private static final String PROMPT = "PenPrompt";
	private List<String> colorList;
	private TextPromptView tp;

	public PenOptionView() {
		
		super(PROMPT);
		
		colorList = new ArrayList<String>(
				Arrays.asList(myResources.getString("PenColors").replaceAll("\\s+", "").split(",")));

		for (String color : colorList)
			cb.getItems().add(colorList.indexOf(color) + ". " + color);

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tp.runCommand("SETPENCOLOR", newValue.intValue());
			}
		});

	}

	public void addTextPrompt(TextPromptView tp) {
		this.tp = tp;
	}

}
