package view;

import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import model.CommandDef;
import view.API.StringListener;

/**
 * Class allowing users to see a history of commands.
 * 
 * @author DavidTran
 *
 */
public class HistoryView implements StringListener {

	TextArea ta;
	ScrollPane sp;
	public HistoryView() {

		TextArea ta = new TextArea();
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
	public Parent getParent() {
		// TODO Auto-generated method stub
		return sp;
	}

	@Override
	public void changedMap(Map<String, CommandDef> newMap) {
		// TODO Auto-generated method stub
		
	}

}
