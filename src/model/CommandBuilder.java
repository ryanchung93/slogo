package model;

import java.util.ArrayList;
import java.util.List;

import model.commands.CommandList;

public interface CommandBuilder {
	public Command build(TokenDispenser dispenser) throws SLogoException;
	
	public default Command buildGroup(TokenDispenser dispenser) throws SLogoException{
		List<Command> commands = new ArrayList<>();
		while(!dispenser.peek().matches(Parser.SYNTAX.getString("GroupEnd"))) {
			commands.add(build(dispenser));
		}
		return new CommandList(commands);
	}
}
