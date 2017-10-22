package model.commands;

import java.util.List;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Repeat implements Command {
	public static final String REP_COUNT = ":repcount";

	private Command numTimes;
	private List<Command> commandList;

	public Repeat(Command numTimes, List<Command> commands) {
		this.numTimes = numTimes;
		this.commandList = commands;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double times = numTimes.execute(t, commands, variables);
		double result = 0;
		for(int i = 0; i < times; i++) {
			variables.setValue(REP_COUNT, i);
			for(Command c : commandList) {
				result = c.execute(t, commands, variables);
			}
		}
		
		return result;
	}

}
