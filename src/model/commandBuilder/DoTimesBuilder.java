package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.TokenDispenser;
import model.commands.DoTimes;

public class DoTimesBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) {
		return new DoTimes(dispenser.getNextToken(), Integer.parseInt(dispenser.getNextToken()), dispenser.getNextCommandList());
	}

}
