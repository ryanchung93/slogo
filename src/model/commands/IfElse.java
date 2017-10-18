package model.commands;

import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class IfElse implements Command {

	private Command expr;
	private List<Command> trueCommands;
	private List<Command> falseCommands;
	
	public IfElse(Command expr, List<Command> trueCommands, List<Command> falseCommands) {
		this.expr = expr;
		this.trueCommands = trueCommands;
		this.falseCommands = falseCommands;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		double ret = 0;
		if(expr.execute(t, commands, variables) != 0) {
			for(Command c : trueCommands) {
				ret = c.execute(t, commands, variables);
			}
		}
		else {
			for(Command c : falseCommands) {
				ret = c.execute(t, commands, variables);
			}
		}
		return ret;
	}

}
