package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.For;

public class ForBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new For(dispenser.getNextToken(), Integer.parseInt(dispenser.getNextToken()),
				Integer.parseInt(dispenser.getNextToken()), Integer.parseInt(dispenser.getNextToken()),
				dispenser.getNextCommandList());
	}

}
