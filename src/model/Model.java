package model;

import java.util.List;
import java.util.function.Supplier;

import view.Animation.TurtleListener;
import view.Windows.StringListener;
import view.Windows.VariableListener;

public class Model {

	private CommandManager commands;
	private VariableManager variables;
	private TurtleManager turtles;
	
	public Model(CommandManager commands, Supplier<List<TurtleListener>> listenersSupplier) {
		this.commands = commands;
		variables = new VariableManager();
		turtles = new TurtleManager(listenersSupplier);
	}
	
	public void setLanguage(String language) {
		commands.setLanguage(language);
	}
	
	public void addVariableListener(VariableListener vL) {
		variables.addListener(vL);
	}
	
	public void addCommandListener(StringListener sL) {
		commands.addListener(sL);
	}
	
	public void addTurtle() {
		turtles.addTurtle();
	}
	
	public void execute(String code) throws SLogoException {
		Parser parser = new Parser(code, commands);
		while(parser.hasNextCommand()) {
			Command command = parser.getNextCommand();
			try {
			command.execute(turtles, commands, variables);
			} catch (StackOverflowError e) {
				throw new SLogoException("StackOverflow");
			}
			variables.notifyListeners();
		}
	}
}
