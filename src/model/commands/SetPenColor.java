package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class SetPenColor implements Command {

	private Command index;
	
	public SetPenColor(Command index) {
		this.index = index;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double indexNum = index.execute(t, commands, variables);
		t.setPenColor((int)indexNum);
		return indexNum;
	}
	
	
	
}
