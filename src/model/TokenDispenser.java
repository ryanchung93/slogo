package model;

import java.util.List;

public interface TokenDispenser {
	public String getNextToken();
	public String peek();
	public Command getNextCommand();
	List<Command> getNextCommandList();
	List<String> getNextTokenList();
}
