package model.commands;

import java.util.Map;

import model.Command;
import model.Turtle;
import model.commandBuilder.CommandDef;

public class SetHeading implements Command {

	private Command input;
	
	public SetHeading(Command par1) {
		input = par1;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		double result = input.execute(t, commands, variables);
		t.setHeading(result);
		return Math.abs(t.getHeading() - result);
	}

}
