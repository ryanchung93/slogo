package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class SetPenSize implements Command {

	private Command size;
	
	public SetPenSize(Command size) {
		this.size = size;
	} 
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double sizeNum = size.execute(t, commands, variables);
		t.setPenSize(sizeNum);
		return sizeNum;
	}

}
