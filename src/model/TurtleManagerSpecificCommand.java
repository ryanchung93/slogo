package model;

/**
 * Some commands only make sense when asked of a TurtleManager, rather than a
 * single turtle. This class encapsulates that error-checking for its
 * subclasses.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public abstract class TurtleManagerSpecificCommand implements Command {
	private static final long serialVersionUID = 4234355250566310877L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if (t instanceof TurtleManager)
			return executeOnManager((TurtleManager) t, commands, variables);
		throw new SLogoException("NestedCommand");
	}

	protected abstract double executeOnManager(TurtleManager t, CommandManager commands, VariableManager variables);

}
