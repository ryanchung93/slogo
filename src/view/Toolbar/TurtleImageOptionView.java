package view.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Class that allows users to select a canvas color from a choice box.
 * 
 * @author DavidTran
 *
 */
public class TurtleImageOptionView extends OptionView implements TurtleImageOptionAPI {

	private List<TurtleImageOptionListener> listeners = new ArrayList<>();
	private List<String> imageNameList;
	private static final String PROMPT = "TurtleImagePrompt";

	public TurtleImageOptionView() {
		super(PROMPT);

		imageNameList = new ArrayList<String>(new ArrayList<String>(
				Arrays.asList(myResources.getString("TurtleImages").replaceAll("\\s+", "").split(","))));

		for (String imageName : imageNameList) {
			cb.getItems().add(imageNameList.indexOf(imageName) + ". " + imageName);
		}

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				for (TurtleImageOptionListener listener : listeners)
					listener.imageChange(newValue.intValue());
			}
		});
	}

	@Override
	public void addTurtleImageListener(TurtleImageOptionListener l) {
		listeners.add(l);
	}

}
