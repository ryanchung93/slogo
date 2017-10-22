package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.For;

public class ForBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) {
		return new For(dispenser.getNextToken(), Integer.parseInt(dispenser.getNextToken()),
				Integer.parseInt(dispenser.getNextToken()), Integer.parseInt(dispenser.getNextToken()),
				dispenser.getNextCommandList());
	}

}
