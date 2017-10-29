package model;

public abstract class TurtleManagerSpecificCommand implements Command{

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if(t instanceof TurtleManager)
			return executeOnManager((TurtleManager)t, commands, variables);
		throw new SLogoException("NestedCommand");
	}

	protected abstract double executeOnManager(TurtleManager t, CommandManager commands, VariableManager variables);
	
}
