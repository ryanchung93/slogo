package view;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import view.API.SubcomponentViewAPI;

public class HistoryView implements SubcomponentViewAPI{

	TextArea ta;
	ScrollPane sp;
	
	public HistoryView() {

		ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText("HistoryView");
		sp = new ScrollPane();
		sp.setVisible(true);
		sp.setContent(ta);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	public void updateHistory(String newCode) {
		ta.appendText("\n" + newCode);
	}

//	@Override
//	public void addCommand(String command) {
//		System.out.print(command);
//		ta.appendText("\n" + command);
//	}

	public Parent getParent() {
		// TODO Auto-generated method stub
		return sp;
	}

	// @Override
	// public void changedMap(Map<String, CommandDef> newMap) {
	// // TODO Auto-generated method stub
	//
	// }

}
