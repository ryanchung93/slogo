package model.commands;

import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;
import model.commandBuilder.CommandBuilder;

public class To implements Command {

	private String name;
	private List<String> localVariables;
	private List<Command> inputCommands;
	
	public To(String name, List<String> localVariables, List<Command> commands) {
		this.name = name;
		this.localVariables = localVariables;
		inputCommands = commands;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		commands.put(name, new CommandBuilder(localVariables, inputCommands));
		//handle variables here or in userdefinedcommand?
		return 0;
	}

}
