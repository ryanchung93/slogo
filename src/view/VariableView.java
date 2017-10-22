package view;

import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import view.API.VariableDisplay;

/**
 * Class allowing users to see values of current variables
 * 
 * @author DavidTran
 *
 */
public class VariableView implements VariableDisplay {

	private TextArea ta;
	private ScrollPane sp;

	public VariableView() {

		TextArea ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText("VariableView");

		sp = new ScrollPane();
		sp.setVisible(true);
		sp.setContent(ta);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	@Override
	public void changedMap(Map<String, Double> vars) {
		for (String key : vars.keySet()) {
			ta.clear();
			ta.appendText(key + " : " + vars.get(key));
		}

	}

	@Override
	public void clearVariables() {
		ta.clear();

	}

	@Override
	public Parent getParent() {
		return sp;
	}

}
