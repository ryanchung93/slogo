package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;
import model.VariableManager;

public class SetHeading implements Command {

	private Command input;
	
	public SetHeading(Command par1) {
		input = par1;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, VariableManager variables) {
		double result = input.execute(t, commands, variables);
		t.setHeading(result);
		return Math.abs(t.getHeading() - result);
	}

}
