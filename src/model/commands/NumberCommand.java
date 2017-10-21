package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;
import model.VariableManager;

public class NumberCommand implements Command {

	private double input;
	
	public NumberCommand(double num) {
		input = num;
	}

	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, VariableManager variables) {
		return input;
	}
	
}
