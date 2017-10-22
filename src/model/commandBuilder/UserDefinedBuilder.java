package model.commandBuilder;

import java.util.HashMap;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.UserDefinedCommand;

public class UserDefinedBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		//Substitute first parameter***
		return new UserDefinedCommand(new HashMap<String, Command>(), dispenser.getNextCommandList());
	}

}
