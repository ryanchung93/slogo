package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Towards implements Command {

	private NumberCommand x;
	private NumberCommand y;
	
	public Towards(NumberCommand xCor, NumberCommand yCor) {
		x = xCor;
		y = yCor;
	}
	
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		
		
		return 5;
	}

	
	
}
