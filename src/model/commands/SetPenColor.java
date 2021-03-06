package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetPenColor implements Command {

	private static final long serialVersionUID = 3774158069090673735L;
	private Command index;
	
	public SetPenColor(Command index) {
		this.index = index;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double indexNum = index.execute(t, commands, variables);
		t.setPenColor((int)indexNum);
		return indexNum;
	}
	
	
	
}
