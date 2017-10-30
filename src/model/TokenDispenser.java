package model;

import java.util.List;

/**
 * An interface implemented by Parser to hide some methods from CommandBuilders.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public interface TokenDispenser {
	public String getNextToken() throws SLogoException;

	public String getNextVariable() throws SLogoException;

	public String peek() throws SLogoException;

	public Command getNextCommand() throws SLogoException;

	public List<Command> getNextCommandList() throws SLogoException;

	public List<String> getNextTokenList() throws SLogoException;

	public List<String> getNextVariableList() throws SLogoException;

	public void defineCommand(String name, List<String> vars);
}
