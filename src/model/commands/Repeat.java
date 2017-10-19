package model.commands;

import java.util.List;
import java.util.Map;

import model.Command;
import model.Turtle;
import model.commandBuilder.CommandDef;

public class Repeat implements Command {

	private Command numTimes;
	private List<Command> commandList;

	public Repeat(Command numTimes, List<Command> commands) {
		this.numTimes = numTimes;
		this.commandList = commands;
	}

	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		double times = numTimes.execute(t, commands, variables);
		
		double result = 0;
		for(int i = 0; i < times; i++) {
			for(Command c : commandList) {
				result = c.execute(t, commands, variables);
			}
		}
		
		return result;
	}

}
