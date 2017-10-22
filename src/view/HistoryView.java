package view;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import view.API.SubcomponentViewAPI;

public class HistoryView implements SubcomponentViewAPI{

	private TextArea ta;
	private ScrollPane sp;
	public HistoryView(double width, double height) {

		ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText("HistoryView");
		// this.setPrefWidth(width);
		// this.setPrefHeight(height);
		sp = new ScrollPane();
		sp.setVisible(true);
		sp.setContent(ta);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	public void updateHistory(String newCode) {
		ta.appendText("\n" + newCode);
	}

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return sp;
	}

}
