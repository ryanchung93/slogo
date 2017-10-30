package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Heading implements Command {

	private static final long serialVersionUID = 6274339430529670915L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.getHeading();
	}

}
