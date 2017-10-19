package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import model.commandBuilder.ForwardBuilder;
import model.commandBuilder.RepeatBuilder;
import view.API.StringListener;
import view.API.TurtleListener;
import view.API.VariableListener;

public class Model {
	
	private List<TurtleListener> turtleListeners;
	private List<VariableListener> variableListeners;
	private List<StringListener> commandListeners;
	
	private Map<String, CommandDef> commands;
	private Map<String, Double> variables;
	
	private List<Turtle> turtles;
	
	public Model() {
		
		turtleListeners = new ArrayList<TurtleListener>();
		variableListeners = new ArrayList<VariableListener>();
		commandListeners = new ArrayList<StringListener>();
		
		commands = new HashMap<String, CommandDef>();
		variables = new HashMap<String, Double>();
		
		commands.put("fd", new ForwardBuilder());
		commands.put("repeat", new RepeatBuilder());
		
		turtles = new ArrayList<Turtle>();
	
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
	
	public void addTurtle(Turtle t, TurtleListener tL) {
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
