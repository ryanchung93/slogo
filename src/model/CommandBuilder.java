package model;

import java.util.ArrayList;
import java.util.List;

import model.commands.CommandList;
//TODO
/**
 * This interface defines a factory for Commands -- each subclass builds a
 * particular command. Most of the responsibilities of Parsing are delegated to
 * these subclasses by Parser.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public interface CommandBuilder {
	public Command build(TokenDispenser dispenser) throws SLogoException;

	public default Command buildGroup(TokenDispenser dispenser) throws SLogoException {
		List<Command> commands = new ArrayList<>();
		while (!dispenser.peek().matches(Parser.SYNTAX.getString("GroupEnd"))) {
			commands.add(build(dispenser));
		}
		return new CommandList(commands);
	}
}
