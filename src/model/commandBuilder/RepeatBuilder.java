package model.commandBuilder;

import java.util.ArrayList;
import java.util.List;

import model.Command;
import model.CommandBuilder;
import model.TokenDispenser;
import model.commands.Repeat;

public class RepeatBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) {
		Command numTimes = dispenser.getNextCommand();
		if(!dispenser.getNextToken().equals("["))
			throw new RuntimeException("TO DO"); //TODO
		
		List<Command> commands = new ArrayList<Command>();
		
		while(!dispenser.peek().equals("]")) {
			commands.add(dispenser.getNextCommand());
		}
		dispenser.getNextToken();
		return new Repeat(numTimes, commands);
	}

}
