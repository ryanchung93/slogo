package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Repeat;

public class RepeatBuilder implements CommandDef {
	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Repeat(dispenser.getNextCommand(), dispenser.getNextCommandList());
	}
}
