package view.API;

import java.util.function.Consumer;

import model.SLogoException;
import view.API.AnimationAPI.TurtleListener;
import view.API.ToolbarAPI.LanguageListener;
import view.API.WindowsAPI.StringListener;
import view.API.WindowsAPI.VariableListener;

public interface ViewAPI {

	/**
	 * Creates and displays the simulation window.
	 * 
	 * @param commandConsumer
	 */
	public void start(Consumer<String> commandConsumer);

	/**
	 * @return TurtleListener that detects changes to Turtle states.
	 */
	public TurtleListener getTurtleListener();

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


	
}
