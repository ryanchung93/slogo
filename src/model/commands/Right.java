package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class Right implements Command {

	private Command input;
	
	public Right(Command par1) {
		input = par1;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		double result = input.execute(t, commands, variables);
		t.setHeading(t.getHeading() - result);
		return result;
	}

}
