package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import view.Animation.TurtleListener;

public class TurtleManager implements Iterable<Turtle>{

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

	@Override
	public Iterator<Turtle> iterator() {
		return new Iterator<Turtle>() {

			private List<Turtle> activeTurtles = getActiveTurtles();
			private int nextIndex = 0;
			
			@Override
			public boolean hasNext() {
				if(nextIndex < activeTurtles.size()) return true;
				return false;
			}

			@Override
			public Turtle next() {
				Turtle ret = activeTurtles.get(nextIndex);
				nextIndex++;
				return ret;
			}
			
		};
	}
	
	private List<Turtle> getActiveTurtles() {
		List<Turtle> activeTurtles = new ArrayList<>();
		for(Turtle t : turtles) {
			if(t.isActive()) activeTurtles.add(t);
		}
		return activeTurtles;
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
		return 1; //TODO return distance moved?
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
