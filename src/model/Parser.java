package model;

import java.util.Map;
import java.util.ResourceBundle;

import model.commandBuilder.CommandDef;
import model.commands.NumberCommand;

public class Parser implements TokenDispenser{

	private int index = 0;
	private String[] tokens;
	private Map<String, CommandDef> availableCommands;
	
	private static ResourceBundle syntax = ResourceBundle.getBundle("resources.languages/Syntax");
	
	public Parser(String code, Map<String, CommandDef> availableCommands) {
		code = code.replaceAll(syntax.getString("Comment"), " ");
		tokens = code.split("\\s+");
		this.availableCommands = availableCommands;
	}
	
	public String peek() {
		return tokens[index];
	}
	
	public String getNextToken() {
		index++;
		return tokens[index-1];
	}
	
	public boolean hasNextCommand() {
		return index < tokens.length;
	}
	
	public Command getNextCommand() {
		String s = getNextToken();
		if(s.matches(syntax.getString("Constant")))
			return new NumberCommand(Double.parseDouble(s));
		return availableCommands.get(getNextToken()).build(this);
	}
}
