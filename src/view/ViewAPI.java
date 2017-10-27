package view;

import java.util.function.Consumer;

import model.SLogoException;
import view.Animation.TurtleListener;
import view.Toolbar.LanguageListener;
import view.Windows.StringListener;
import view.Windows.VariableListener;

public interface ViewAPI {

	/**
	 * Creates and displays the simulation window.
	 * 
	 * @param commandConsumer
	 */
	public void start(Consumer<String> commandConsumer);

	/**
	 * @return VariableListener that detects changes to current variables.
	 */
	public VariableListener getVariableListener();

	/**
	 * @return StringListener that detects changes to list of commands.
	 */
	public StringListener getCommandListener();

	/**
	 * @return StringListener that detects changes to list of user commands.
	 */
	public StringListener getUserDefinedCommandListener();
	
	/**
	 * @param e
	 *            A SLogoException produced when the code is executed
	 */
	public void display(SLogoException e);

	TurtleListener getTurtleListener(int id);


	
}
