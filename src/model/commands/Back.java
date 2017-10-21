package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;
import model.VariableManager;

public class Back implements Command {

	private Command input;

	public Back(Command par1) {
		input = par1;
	}

	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, VariableManager variables) {
		Forward fd = new Forward(input);
		double result = -1 * fd.execute(new Turtle(0, 0, 0), commands, variables);
		Forward bk = new Forward(new NumberCommand(result));
		return bk.execute(t, commands, variables);
	}
}
