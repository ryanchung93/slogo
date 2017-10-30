package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class PenColor implements Command {

	private static final long serialVersionUID = -2717324367969166866L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.getPenColorIndex();
	}

}
