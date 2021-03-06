package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.For;
import model.commands.NumberCommand;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class RepeatBuilder implements CommandBuilder {
	public static final String REP_COUNT = ":repcount";
	
	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new For(REP_COUNT, new NumberCommand(1), dispenser.getNextCommand(), new NumberCommand(1), dispenser.getNextCommandList());
	}
}
