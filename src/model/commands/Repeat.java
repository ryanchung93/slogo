package model.commands;

import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class Repeat implements Command {

	private int num;
	private List<Command> input;
	
	public Repeat(int iterations, List<Command> commandList) {
		num = iterations;
		input = commandList;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		double ret = 0;
		for(int i = 0; i < num; i++) {
			for(int c = 0; c < input.size(); c++) {
				ret = input.get(c).execute(t, commands, variables);
			}
		}
		return ret;
	}

}
