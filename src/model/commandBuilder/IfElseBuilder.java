package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.IfElse;

public class IfElseBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) {
		return new IfElse(dispenser.getNextCommand(), dispenser.getNextCommandList(), dispenser.getNextCommandList());
	}

}
