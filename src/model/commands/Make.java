package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;
import model.VariableManager;

public class Make implements Command {

	private String variable;
	private Command value;
	
	public Make(String name, Command input) {
		variable = name;
		value = input;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, VariableManager variables) {
		variables.setValue(variable, value.execute(t, commands, variables));
		return value.execute(t, commands, variables);
	}

}
