package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class Sum implements Command {

	private Command input1;
	private Command input2;
	
	public Sum(Command x1, Command x2) {
		input1 = x1;
		input2 = x2;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		return input1.execute(t, commands, variables) + input2.execute(t, commands, variables);
	}

}
