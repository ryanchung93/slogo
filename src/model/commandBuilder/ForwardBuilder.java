package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.Forward;

public class ForwardBuilder implements CommandDef {
	@Override
	public Command build(TokenDispenser dispenser) {
		return new Forward(dispenser.getNextCommand());
	}
}
