package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.Less;

public class LessBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) {
		return new Less(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
