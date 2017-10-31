package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Turtles implements Command {

	private static final long serialVersionUID = -3760037466743708880L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.getNumTurtles(); 
	}

}
