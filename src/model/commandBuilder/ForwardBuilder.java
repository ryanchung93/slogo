package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.TokenDispenser;
import model.commands.Forward;

public class ForwardBuilder implements CommandBuilder {
	@Override
	public Command build(TokenDispenser dispenser) {
		return new Forward(dispenser.getNextCommand());
	}
}
