package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.SetXY;

public class SetXYBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new SetXY(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
