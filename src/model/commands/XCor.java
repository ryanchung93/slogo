package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;
import model.VariableManager;

public class XCor implements Command {

	public XCor() {}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, VariableManager variables) {
		return t.getX();
	}

}
