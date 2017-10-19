package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import model.commandBuilder.CommandBuilder;
import model.commands.NumberCommand;

public class Parser implements TokenDispenser{

	private int index;
	private String[] tokens;
	private Map<String, CommandDef> availableCommands;
	
	private static ResourceBundle syntax = ResourceBundle.getBundle("resources.languages/Syntax");
	
	public Parser(String code, Map<String, CommandDef> availableCommands) {
		index = 0;
		code = code.replaceAll(syntax.getString("Comment"), " ").toLowerCase();
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
		return availableCommands.get(s).build(this);
	}

	@Override
	public List<Command> getNextCommandList() {
		return getNextList(()->getNextCommand());
	}

	@Override
	public List<String> getNextTokenList() {
		return getNextList(()->getNextToken());
	}
	
	public <T> List<T> getNextList(Supplier<T> supplier){
		if(!getNextToken().equals("["))
			throw new RuntimeException("TO DO"); //TODO
		
		List<T> result = new ArrayList<T>();
		while(!peek().equals("]")) {
			result.add(supplier.get());
		}
		getNextToken();
		return result;
	}
}
