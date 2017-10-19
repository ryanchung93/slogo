package model.commands;

import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class DoTimes implements Command {
	
	private String varName;
	private int lim;
	private List<Command> commandList;
	
	public DoTimes(String variable, int limit, List<Command> input) {
		varName = variable;
		lim = limit;
		commandList = input;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		Command useFor = new For(varName, 1, lim, 1, commandList);
		return useFor.execute(t, commands, variables);
	}

}
