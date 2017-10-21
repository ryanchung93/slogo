package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.SetHeading;

public class SetHeadingBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) {
		return new SetHeading(dispenser.getNextCommand());
	}

}
