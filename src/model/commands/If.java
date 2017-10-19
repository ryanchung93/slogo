package model.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class If implements Command {

	private Command expr;
	private List<Command> input;
	
	public If(Command expr, List<Command> input) {
		this.expr = expr;
		this.input = input;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		List<Command> falseCommands = new ArrayList<Command>();
		falseCommands.add(new NumberCommand(0));
		IfElse useIfElse = new IfElse(expr, input, falseCommands);
		return useIfElse.execute(t, commands, variables);
	}

	
	
	
}
