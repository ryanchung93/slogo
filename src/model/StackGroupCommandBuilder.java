package model;

public abstract class StackGroupCommandBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException{
		return build(dispenser, dispenser.getNextCommand());
	}

	@Override 
	public Command buildGroup(TokenDispenser dispenser) throws SLogoException{
		Command command = build(dispenser);
		while(!dispenser.peek().matches(Parser.SYNTAX.getString("GroupEnd"))) {
			command = build(dispenser, command);
		}
		return command;
	}
	
	protected abstract Command build(TokenDispenser dispenser, Command last) throws SLogoException;
}
