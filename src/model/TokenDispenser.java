package model;

import java.util.List;

public interface TokenDispenser {
	public String getNextToken();
	public String peek();
	public Command getNextCommand();
	public List<Command> getNextCommandList();
	public List<String> getNextTokenList();
}
