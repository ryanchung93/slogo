package model;

public interface CommandBuilder {
	public Command build(TokenDispenser dispenser) throws SLogoException;
}
