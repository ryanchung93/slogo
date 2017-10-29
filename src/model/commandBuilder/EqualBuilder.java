package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Equal;

public class EqualBuilder implements CommandBuilder {
	
	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Equal(dispenser.getNextCommand(), dispenser.getNextCommand());
	}
}
