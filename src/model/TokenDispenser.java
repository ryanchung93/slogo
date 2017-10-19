package model;

public interface TokenDispenser {
	public String getNextToken();
	public String peek();
	public Command getNextCommand();
}
