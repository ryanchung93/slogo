package model.commands;

import java.util.Map;

import model.Command;
import model.Turtle;
import model.commandBuilder.CommandDef;

public class NumberCommand implements Command {

	private double input;
	
	public NumberCommand(double num) {
		input = num;
	}

	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		return input;
	}
	
}
