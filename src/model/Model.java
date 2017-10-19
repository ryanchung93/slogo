package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.API.StringListener;
import view.API.TurtleListener;
import view.API.VariableListener;

public class Model {
	
	private List<TurtleListener> turtleListeners;
	private List<VariableListener> variableListeners;
	private List<StringListener> commandListeners;
	
	private Map<String, CommandDef> commands;
	private Map<String, Double> variables;
	
	private Turtle turtle;
	
	public Model() {
		
		turtleListeners = new ArrayList<TurtleListener>();
		variableListeners = new ArrayList<VariableListener>();
		commandListeners = new ArrayList<StringListener>();
		
		commands = new HashMap<String, CommandDef>();
		variables = new HashMap<String, Double>();
		
		turtle = new Turtle(0, 0, 0);
		
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
		Parser parser = new Parser(code, commands);
		while(parser.hasNextCommand()) {
			parser.getNextCommand().execute(turtle, commands, variables);
		}
	}

}
