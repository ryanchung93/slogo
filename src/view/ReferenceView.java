package view;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import model.CommandDef;
import view.API.StringListener;

/**
 * A Pane representing the basic instructions for using SLogo.
 * 
 * @author DavidTran
 *
 */
public class ReferenceView implements StringListener {

	TextArea ta;
	ScrollPane sp;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	public ReferenceView() {

		ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText(myResources.getString("ReferenceView"));
		sp = new ScrollPane();
		sp.setVisible(true);
		sp.setContent(ta);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	@Override
	public void changedMap(Map<String, CommandDef> newMap) {
		ta.clear();
		ta.appendText(myResources.getString("ReferenceView") + "\n");
		for (String key : newMap.keySet()) {
			ta.appendText(key + "\n");// + " : " + newMap.get(key));
		}

	}

	@Override
	public Parent getParent() {
		return sp;
	}

}
