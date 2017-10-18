package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class Number implements Command {

	private double input;
	
	public Number(double num) {
		input = num;
	}

	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		return input;
	}
	
}
