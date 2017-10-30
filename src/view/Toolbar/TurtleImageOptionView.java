package view.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
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
public class TurtleImageOptionView extends OptionView {

	private List<String> imageNameList;
	private static final String PROMPT = "TurtleImagePrompt";
	private TextPromptView tp;

	public TurtleImageOptionView(List<String> imgList) {
		
		super(PROMPT);

		imageNameList = imgList;

		for (String imageName : imageNameList) {
			cb.getItems().add(imageNameList.indexOf(imageName) + ". " + imageName);
		}

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tp.runCommand("SetShape", Integer.toString(newValue.intValue()));
			}
		});
	}

	public void addTextPrompt(TextPromptView tp) {
		this.tp = tp;
	}

}
