package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class XCor implements Command {

	public XCor() {}
	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		return t.getX();
	}

}
