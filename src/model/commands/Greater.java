package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Greater implements Command {
	private static final long serialVersionUID = 1L;
	private Command x1;
	private Command x2;
	
	public Greater(Command x1, Command x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if(x1.execute(t, commands, variables) > x2.execute(t, commands, variables)) return 1;
		return 2;
	}

}
