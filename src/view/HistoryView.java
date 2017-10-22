package view;

import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import model.CommandDef;
import view.API.NewCommandListener;
import view.API.StringListener;

/**
 * Class allowing users to see a history of commands.
 * 
 * @author DavidTran
 *
 */

public class HistoryView implements NewCommandListener {

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

	@Override
	public void addCommand(String command) {
		System.out.print(command);
		ta.appendText("\n" + command);
	}

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
