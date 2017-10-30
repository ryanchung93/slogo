package view.Toolbar;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
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

	public PenOptionView(List<String> colorList) {

		super(PROMPT);

		this.colorList = colorList;

		makeChoiceBox();

		addItem(7, "255", "254", "253");

	}

	public void addTextPrompt(TextPromptView tp) {
		this.tp = tp;
	}

	private void makeChoiceBox() {

		for (String color : colorList)
			cb.getItems().add(colorList.indexOf(color) + ". " + color);

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tp.runCommand("SetPenColor", Integer.toString(newValue.intValue()));
			}
		});
	}

	private void addItem(int index, String r, String g, String b) {
		if (index < cb.getItems().size()) {
			cb.getItems().remove(index);
			cb.getItems().add(index, "rgb(" + r + "," + g + "," + b + ")");
			Color c = Color.valueOf(cb.getItems().get(7));
			System.out.println(c);
		}
	}

}
