package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class ID implements Command {

	private static final long serialVersionUID = 272185873730515699L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.getID();
	}
}
