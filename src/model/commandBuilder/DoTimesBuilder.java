package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.DoTimes;

public class DoTimesBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new DoTimes(dispenser.getNextToken(), Integer.parseInt(dispenser.getNextToken()), dispenser.getNextCommandList());
	}

}
