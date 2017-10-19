package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.Repeat;

public class RepeatBuilder implements CommandDef {
	@Override
	public Command build(TokenDispenser dispenser) {
		return new Repeat(dispenser.getNextCommand(), dispenser.getNextCommandList());
	}
}
