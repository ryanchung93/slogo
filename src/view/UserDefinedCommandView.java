package view;

import java.util.Map;

import javafx.scene.Parent;
import model.CommandDef;
import view.API.UserDefinedCommandDisplay;

/**
 * Class allowing users to see defined functions.
 * 
 * @author DavidTran
 *
 */
public class UserDefinedCommandView implements UserDefinedCommandDisplay {

	@Override
	public void changedMap(Map<String, CommandDef> newMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearCommands() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return null;
	}

}
