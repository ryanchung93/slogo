package view;

import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import model.CommandDef;
import view.API.StringListener;

/**
 * A Pane representing the basic instructions for using SLogo
 *
 */
public class ReferenceView implements StringListener {

	TextArea ta;
	ScrollPane sp;
	public ReferenceView(double width, double height) {

		ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText("ReferenceView");
		// this.setPrefWidth(width);
		// this.setPrefHeight(height);
		sp = new ScrollPane();
		sp.setVisible(true);
		sp.setContent(ta);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	@Override
	public void changedMap(Map<String, CommandDef> newMap) {
		ta.clear();
		for (String key : newMap.keySet()) {
			ta.appendText(key + "\n");// + " : " + newMap.get(key));
		}

	}

	@Override
	public Parent getParent() {
		return sp;
	}

}
