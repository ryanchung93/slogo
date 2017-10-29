package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class SetBackground implements Command {

	private Command index;
	
	public SetBackground(Command index) {
		this.index = index;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double indexNum = index.execute(t, commands, variables);
		t.setBackgroundColor((int)indexNum);
		return indexNum;
	}

}
