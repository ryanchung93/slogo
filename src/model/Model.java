package model;

import view.API.StringListenerAPI;
import view.API.TurtleListenerAPI;
import view.API.VariableListenerAPI;

public interface Model {
	public void addTurtleListener(TurtleListenerAPI tL);
	public void addVariableListener(VariableListenerAPI sL);
	public void addCommandListener(StringListenerAPI sL);
	public void execute(String code);
}
