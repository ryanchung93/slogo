package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.StringListener;
import view.TurtleListener;
import view.VariableListener;

public class Model {
	
	private List<TurtleListener> turtleListeners;
	private List<VariableListener> variableListeners;
	private List<StringListener> commandListeners;
	
	private Collection commands; 		//choose type of collection
	private Map<String, Double> variables;
	
	public Model() {
		
		turtleListeners = new ArrayList<TurtleListener>();
		variableListeners = new ArrayList<VariableListener>();
		commandListeners = new ArrayList<StringListener>();
		
		//instantiate commands
		variables = new HashMap();
		
	}
	
	public void addTurtleListener(TurtleListener tL) {
		turtleListeners.add(tL);
	}
	
	public void addVariableListener(VariableListener vL) {
		variableListeners.add(vL);
	}
	
	public void addCommandListener(StringListener sL) {
		commandListeners.add(sL);
	}
	
	public void execute(String code) {
		//Parser parser = new Parser(code, commands);
		
	}
}
