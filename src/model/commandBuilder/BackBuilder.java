package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.Back;

public class BackBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) {
		return new Back(dispenser.getNextCommand());
	}

}
