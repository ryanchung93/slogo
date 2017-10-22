package model.commands;

import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class UserDefinedCommand implements Command {

	private Map<String, Command> parameters;
	private List<Command> commandList;
	
	public UserDefinedCommand(Map<String, Command> parameters, List<Command> commands) {
		this.parameters = parameters;
		this.commandList = commands;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		variables.enterLocalScope();
		for(String var : parameters.keySet())
			variables.setLocalValue(var, parameters.get(var).execute(t, commands, variables));
		double answer = 0;
		for(Command command : commandList) {
			answer = command.execute(t, commands, variables);
		}
		variables.leaveLocalScope();
		return answer;
	}
}