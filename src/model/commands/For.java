package model.commands;

import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;
import model.VariableManager;

public class For implements Command {

	private String varName;
	private int start;
	private int end;
	private int increment;
	private List<Command> commandList;
	
	public For(String variable, int start, int end, int increment, List<Command> input) {
		varName = variable;
		this.start = start;
		this.end = end;
		this.increment = increment;
		commandList = input;
	}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, VariableManager variables) {
		double ret = 0;
		for(int i = start; i < end; i += increment) {
			variables.setValue(varName, (double)i);
			for(int c = 0; c < commandList.size(); c++) {
				ret = commandList.get(c).execute(t, commands, variables);
			}
		}
		return ret;
	}

}
