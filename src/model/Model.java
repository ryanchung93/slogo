package model;

import view.API.CommandIOAPI.TurtleListener;
import view.API.SidePane.StringListener;
import view.API.SidePane.VariableListener;
import view.API.ToolbarAPI.LanguageListener;

public class Model {

	private CommandManager commands;
	private VariableManager variables;
	private TurtleManager turtles;
	
	public Model(CommandManager commands) {
		this.commands = commands;
		variables = new VariableManager();
		turtles = new TurtleManager();
	}
	
	public void setLanguage(String language) {
		commands.setLanguage(language);
	}
	
	public void addTurtleListener(TurtleListener tL) {
		turtles.addTurtleViewManager(tL);
	}
	
	public void addVariableListener(VariableListener vL) {
		variables.addListener(vL);
	}
	
	public void addCommandListener(StringListener sL) {
		commands.addListener(sL);
	}
	
	public void addTurtle(Turtle t, TurtleListener... tL) {
		turtles.addTurtle(t);
		for(TurtleListener listener : tL) {
			t.addTurtleListener(listener);
		}
	}
	
//	public void execute(String code) throws SLogoException {
//		for (Turtle turtle : turtles.getTurtles()) {
//			turtle.setParser(new Parser(code, commands));
//			while(turtle.getParser().hasNextCommand()) {
//			    // must implement if turtle is toggled - discuss
//				turtle.getParser().getNextCommand().execute(turtle, commands, variables);
//				variables.notifyListeners();
//			}
//		}
//	}
	
	public void execute(String code) throws SLogoException {
		Parser parser = new Parser(code, commands);
		while(parser.hasNextCommand()) {
			Command command = parser.getNextCommand();
			for(Turtle turtle : turtles.getTurtles()) {
				command.execute(turtle, commands, variables);
				variables.notifyListeners();
			}
		}
	}
}
