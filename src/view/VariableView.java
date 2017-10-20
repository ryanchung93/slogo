package view;

import java.util.Map;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import view.API.VariableDisplay;

public class VariableView extends ScrollPane implements VariableDisplay {

	public VariableView(double width, double height) {

		TextArea ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText("Test");
		// this.setPrefWidth(width);
		// this.setPrefHeight(height);
		this.setVisible(true);
		this.setContent(ta);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	@Override
	public void changedMap(Map<String, Double> vars) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearVariables() {
		// TODO Auto-generated method stub

	}

}
