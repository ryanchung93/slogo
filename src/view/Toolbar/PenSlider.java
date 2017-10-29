package view.Toolbar;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import view.SubcomponentViewAPI;
import view.Animation.TextPromptView;

public class PenSlider implements SubcomponentViewAPI {

	private static ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private static final String PROMPT = "PenThicknessPrompt";
	private Label prompt;
	private Slider sl;
	private VBox view;
	private TextPromptView tp;

	public PenSlider() {
		view = new VBox();
		sl = makeSlider();
		prompt = new Label(myResources.getString(PROMPT));
		view.getChildren().addAll(prompt, sl);
		view.setAlignment(Pos.CENTER);
		
		sl.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    tp.runCommand("SetPenSize", Integer.toString(new_val.intValue()));
            }
        });
	}

	@Override
	public Parent getParent() {
		return view;
	}

	private Slider makeSlider() {
		Slider ret = new Slider(0, Integer.parseInt(myResources.getString("MaxThickness")), 1);
		ret.setShowTickLabels(true);
		ret.setShowTickMarks(true);
		ret.setMajorTickUnit(1);
		ret.setMinorTickCount(1);
		return ret;
	}

	public void addTextPrompt(TextPromptView myTextPrompt) {
		tp = myTextPrompt;
	}

}
