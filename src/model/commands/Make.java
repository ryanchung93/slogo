package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class Make implements Command {

	private String variable;
	private Command value;
	
	public Make(String name, Command input) {
		variable = name;
		value = input;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		variables.put(variable, value.execute(t, commands, variables));
		return value.execute(t, commands, variables);
	}

}
