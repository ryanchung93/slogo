package view.Windows;

import java.util.Map;

import view.API.WindowsAPI.VariableListener;

/**
 * Class allowing users to see values of current variables
 * 
 * @author DavidTran
 *
 */
public class VariableView extends Window implements VariableListener{

	public VariableView(double height) {
		super(height);
		ta.appendText(myResources.getString("VariableView") + "\n");
	}

	/*************************** PUBLIC METHODS ********************************/

	@Override
	public void changedMap(Map<String, Double> vars) {
		clear();
		for (String key : vars.keySet()) {
			ta.appendText(key + " : " + vars.get(key) + "\n");
		}
	}
	
	/*************************** PRIVATE METHODS ********************************/

	private void clear() {
		ta.clear();
		ta.appendText(myResources.getString("VariableView") + "\n");
	}
}
