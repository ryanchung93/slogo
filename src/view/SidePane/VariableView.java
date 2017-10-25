package view.SidePane;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import view.API.SidePane.VariableDisplay;

/**
 * Class allowing users to see values of current variables
 * 
 * @author DavidTran
 *
 */
public class VariableView implements VariableDisplay {

	private TextArea ta;
	private VBox view;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	public VariableView(double height) {
		view = new VBox();
		view.setMinHeight(height);

		ta = createTA(height);
		view.getChildren().addAll(ta);
	}

	/*************************** PUBLIC METHODS ********************************/

	@Override
	public void changedMap(Map<String, Double> vars) {
		clear();
		for (String key : vars.keySet()) {
			ta.appendText(key + " : " + vars.get(key) + "\n");
		}
	}

	@Override
	public void clearVariables() {
		clear();
	}

	public Parent getParent() {
		return view;
	}

	/*************************** PRIVATE METHODS ********************************/

	private TextArea createTA(double height) {
		TextArea ret = new TextArea();
		ret.setMinHeight(height);
		ret.setWrapText(true);
		ret.setEditable(false);
		ret.appendText(myResources.getString("VariableView") + "\n");
		return ret;
	}

	private void clear() {
		ta.clear();
		ta.appendText(myResources.getString("VariableView") + "\n");
	}
}
