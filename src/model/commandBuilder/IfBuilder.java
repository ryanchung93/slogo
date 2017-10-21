package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.If;

public class IfBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) {
		return new If(dispenser.getNextCommand(), dispenser.getNextCommandList());
	}
	
}
