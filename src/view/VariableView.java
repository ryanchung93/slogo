package view;

import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import view.API.VariableDisplay;

public class VariableView implements VariableDisplay {

	TextArea ta;
	ScrollPane sp;
	public VariableView(double width, double height) {

		TextArea ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText("VariableView");
		// this.setPrefWidth(width);
		// this.setPrefHeight(height);
		sp = new ScrollPane();
		sp.setVisible(true);
		sp.setContent(ta);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	@Override
	public void changedMap(Map<String, Double> vars) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearVariables() {
		// TODO Auto-generated method stub

	}

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return sp;
	}

}
