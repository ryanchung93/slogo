package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class SetShape implements Command {

	private Command index;
	
	public SetShape(Command index) {
		this.index = index;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double shapeIndex = index.execute(t, commands, variables);
		t.setShapeIndex((int)shapeIndex);
		return shapeIndex;
	}
	
}
