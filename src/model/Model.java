package model;

import view.StringListener;
import view.TurtleListener;
import view.VariableListener;

public interface Model {
	public void addTurtleListener(TurtleListener tL);
	public void addVariableListener(VariableListener sL);
	public void addCommandListener(StringListener sL);
	public void execute(String code);
}
