package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Right;

public class RightBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Right(dispenser.getNextCommand());
	}

}
