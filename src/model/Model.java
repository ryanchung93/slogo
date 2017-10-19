package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.API.StringListenerAPI;
import view.API.TurtleListenerAPI;
import view.API.VariableListenerAPI;

import view.StringListener;
import view.TurtleListener;
import view.VariableListener;

public class Model {
	
	private List<TurtleListenerAPI> turtleListeners;
	private List<VariableListenerAPI> variableListeners;
	private List<StringListenerAPI> commandListeners;
	
	private Map<String, CommandDef> commands;
	private Map<String, Double> variables;
	
	private List<Turtle> turtles;
	
	public Model() {
		
		turtleListeners = new ArrayList<TurtleListenerAPI>();
		variableListeners = new ArrayList<VariableListenerAPI>();
		commandListeners = new ArrayList<StringListenerAPI>();
		
		commands = new HashMap<String, CommandDef>();
		variables = new HashMap<String, Double>();
		
		turtles = new ArrayList<Turtle>();
		
	}
	
	public void addVariableListener(VariableListenerAPI vL) {
		variableListeners.add(vL);
	}
	
	public void addCommandListener(StringListenerAPI sL) {
		commandListeners.add(sL);
	}
	
	public void addTurtle(Turtle t, TurtleListenerAPI tL) {
		t.addTurtleListener(tL);
		turtles.add(t);
		turtleListeners.add(tL);
	}
	
	public void execute(String code) {
		Parser parser = new Parser(code, commands);
		Map<String, CommandDef> prevCommands = new HashMap<String, CommandDef>();
		prevCommands.putAll(commands);
		Map<String, Double> prevVariables = new HashMap<String, Double>();
		prevVariables.putAll(variables);
		while(parser.hasNextCommand()) {
			parser.getNextCommand().execute(turtles.get(0), commands, variables);
			if(!prevVariables.equals(variables)) {
				variableListeners.get(0).changedMap(variables);
				prevVariables.putAll(variables);
			}
			if(!prevCommands.equals(commands)) {
				commandListeners.get(0).changedMap(commands);
				prevCommands.putAll(commands);
			}
		}
	}

}
