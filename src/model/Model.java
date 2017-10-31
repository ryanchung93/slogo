package model;

import java.util.List;
import java.util.function.Supplier;

import view.Animation.TurtleListener;
import view.Windows.StringListener;
import view.Windows.VariableListener;

/**
 * Manages the back-end and interactions from external sources. Dependent on
 * Parser, CommandManager, VariableManager, TurtleManager, Command, SaverLoader. 
 * 
 * @author Aaron Paskin
 */
public class Model {

	private static final String VAR_EXT = "_var";
	private static final String COM_EXT = "_com";
	private CommandManager commands;
	private VariableManager variables;
	private TurtleManager turtles;

	/**
	 * @param commands The CommandManager to start with. 
	 * @param listenersSupplier Supplier of listeners to be passed to TurtleManager
	 */
	public Model(CommandManager commands, Supplier<List<TurtleListener>> listenersSupplier) {
		this.commands = commands;
		variables = new VariableManager();
		turtles = new TurtleManager(listenersSupplier);
	}

	/**
	 * @param language A known language
	 */
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
		while (parser.hasNextCommand()) {
			Command command = parser.getNextCommand();
			try {
				command.execute(turtles, commands, variables);
			} catch (StackOverflowError e) {
				throw new SLogoException("StackOverflow");
			}
			variables.notifyListeners();
		}
	}

	public void save(String file) {
		variables.save(file + VAR_EXT);
		commands.save(file + COM_EXT);
		SaverLoader.addToKnown(file);
	}

	public void load(String file) {
		variables.load(file + VAR_EXT);
		commands.load(file + COM_EXT);
	}
}
