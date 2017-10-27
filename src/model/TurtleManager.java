package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import view.Animation.TurtleListener;

public class TurtleManager {

	private List<TurtleListener> turtleViewManagers;
	private List<Turtle> turtles;
	
	public TurtleManager() {
		turtleViewManagers = new ArrayList<>();
		turtles = new ArrayList<>();
	}
	
	public List<Turtle> getTurtles() {
		return turtles;
	}
	
	public void addTurtle(Turtle t) {
		boolean match = false;
		for(Turtle e : turtles) {
			if(e.getID() == t.getID()) match = true;
		}
		if(!match) {
			turtles.add(t);
		}
		//Throw Turtle already exists exception
	}
	
	public void addTurtleViewManager(TurtleListener tL) {
		turtleViewManagers.add(tL);
	}
 	
	public double forward(Command command, CommandManager commands, VariableManager variables) {
		return doToEach(t->t.forward(command, commands, variables));
	}
	
	public double left(Command command, CommandManager commands, VariableManager variables) {
		return doToEach(t->t.left(command, commands, variables));
	}
	
	public double setXY(Command command1, Command command2, CommandManager commands, VariableManager variables) {
		return doToEach(t->t.setXY(command1, command2, commands, variables));
	}
	
	public double setHeading(Command command, CommandManager commands, VariableManager variables) {
		return doToEach(t->t.setHeading(command, commands, variables));
	}
	
	public double towards(Command command1, Command command2, CommandManager commands, VariableManager variables) {
		return doToEach(t->t.setTowards(command1, command2, commands, variables));
	}
	
	public double clearScreen(CommandManager commands, VariableManager variables) {
		for(Turtle t : this)
			t.clearScreen();
	}
	
	public void setPenDown(boolean b) {
		for(Turtle t : this)
			t.setPenDown(b);
	}

	public void setVisible(boolean b) {
		for(Turtle t : this)
			t.setVisible(b);
	}

	private double doToEach(Function<Turtle, Double> command) {
		double result = 0;
		for(Turtle t : this) {
			result = command.apply(t);
		}
		return result;
	}
}
