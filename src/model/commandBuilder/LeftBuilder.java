package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.Left;

public class LeftBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) {
		return new Left(dispenser.getNextCommand());
	}

}
