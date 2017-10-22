package model.commands;

import java.util.List;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

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
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		Command useFor = new For(varName, 1, lim, 1, commandList);
		return useFor.execute(t, commands, variables);
	}

}
