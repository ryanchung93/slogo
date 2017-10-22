package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Make;

public class MakeBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Make(dispenser.getNextToken(), dispenser.getNextCommand());
	}

}