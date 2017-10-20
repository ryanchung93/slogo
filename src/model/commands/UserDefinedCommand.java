package model.commands;

import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class UserDefinedCommand implements Command {

	private Map<String, Command> parameters;
	private List<Command> commandList;
	
	public UserDefinedCommand(Map<String, Command> parameters, List<Command> commands) {
		this.parameters = parameters;
		this.commandList = commands;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {		
		for(String var : parameters.keySet())
			variables.put(var, parameters.get(var).execute(t, commands, variables));
		
		double answer = 0;
		for(Command command : commandList) {
			answer = command.execute(t, commands, variables);
		}
		return answer;
	}
}
