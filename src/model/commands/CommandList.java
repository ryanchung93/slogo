package model.commands;

import java.util.List;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class CommandList implements Command {

	private List<Command> commandList;

	public CommandList(List<Command> commandList) {
		this.commandList = commandList;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double result = 0;
		for(Command com : commandList)
			result = com.execute(t, commands, variables);
		return result;
	}
}
