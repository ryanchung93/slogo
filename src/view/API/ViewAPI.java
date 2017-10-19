package view.API;

public interface ViewAPI {
	
	/**
	 * @return
	 * TurtleListener that detects changes to Turtle states.
	 */
	public TurtleListener getTurtleListener();

	/**
	 * @return
	 * VariableListener that detects changes to current variables.
	 */
	public VariableListener getVariableListener();

	/**
	 * @return
	 * StringListener that detects changes to list of commands.
	 */
	public StringListener getCommandListener();
}
