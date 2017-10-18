package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDefinedCommand implements Command {

	private Map<String, Command> parameters;
	private List<Command> inputCommands;
	
	public UserDefinedCommand(Map<String, Command> parameters, List<Command> commands) {
		this.parameters = parameters;
		this.inputCommands = commands;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		for(String var : parameters.keySet()) {
			variables.put(var, parameters.get(var).execute(t, commands, variables));

		}
		double ret = 0;
		for(int i = 0; i < inputCommands.size(); i++) {
			ret = inputCommands.get(i).execute(t, commands, variables);
		}
		return ret;
	}
}
