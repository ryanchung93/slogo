package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.commandBuilder.ForwardBuilder;
import model.commandBuilder.RepeatBuilder;
import view.API.StringListener;
import view.API.TurtleListener;
import view.API.VariableListener;

public class Model {
	
	private List<TurtleListener> turtleListeners;
	
	private CommandManager commands;
	private VariableManager variables;
	
	private List<Turtle> turtles;
	
	public Model() {
		
		turtleListeners = new ArrayList<TurtleListener>();
		
		commands = new CommandManager();
		commands.loadCommands("resources.builders.basicCommands", "English");
		
		variables = new VariableManager();
		
		turtles = new ArrayList<Turtle>();
	
	}
	
	public void addTurtleListener(TurtleListener tL) {
		turtleListeners.add(tL);
	}
	
	public void addVariableListener(VariableListener vL) {
		variables.addListener(vL);
	}
	
	public void addCommandListener(StringListener sL) {
		commands.addListener(sL);
	}
	
	public void addTurtle(Turtle t, TurtleListener tL) {
		turtles.add(t);
		t.addTurtleListener(tL);
		turtleListeners.add(tL);
	}
	
	public void execute(String code) {
		Parser parser = new Parser(code, commands);
		while(parser.hasNextCommand()) {
			parser.getNextCommand().execute(turtles.get(0), commands, variables);
			variables.notifyListeners();
		}
	}
}
