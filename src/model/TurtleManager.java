package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import view.Animation.TurtleListener;

public class TurtleManager implements Iterable<SingularTurtle>, Turtle {

	private List<TurtleListener> turtleViewManagers;
	private List<SingularTurtle> turtles;
	
	public TurtleManager() {
		turtleViewManagers = new ArrayList<>();
		turtles = new ArrayList<>();
	}
	
	public List<SingularTurtle> getTurtles() {
		return turtles;
	}
	
	public void addTurtle(SingularTurtle t) {
		boolean match = false;
		for(SingularTurtle e : turtles) {
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
	public Iterator<SingularTurtle> iterator() {
		return new Iterator<SingularTurtle>() {

			private List<SingularTurtle> activeTurtles = getActiveTurtles();
			private int nextIndex = 0;
			
			@Override
			public boolean hasNext() {
				if(nextIndex < activeTurtles.size()) return true;
				return false;
			}

			@Override
			public SingularTurtle next() {
				SingularTurtle ret = activeTurtles.get(nextIndex);
				nextIndex++;
				return ret;
			}
			
		};
	}
	
	private List<SingularTurtle> getActiveTurtles() {
		List<SingularTurtle> activeTurtles = new ArrayList<>();
		for(SingularTurtle t : turtles) {
			if(t.isActive()) activeTurtles.add(t);
		}
		return activeTurtles;
	}
	
	private SingularTurtle getLastActiveTurtle() {
		return getActiveTurtles().get(getActiveTurtles().size() - 1);
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
	
	public double setTowards(Command command1, Command command2, CommandManager commands, VariableManager variables) {
		return doToEach(t->t.setTowards(command1, command2, commands, variables));
	}
	
	public double clearScreen() {
		return doToEach(t->t.clearScreen());
	}
	
	public void setPenDown(boolean b) {
		for(SingularTurtle t : this)
			t.setPenDown(b);
	}

	public void setVisible(boolean b) {
		for(SingularTurtle t : this)
			t.setVisible(b);
	}

	private double doToEach(Function<SingularTurtle, Double> command) {
		double result = 0;
		for(SingularTurtle t : this) {
			result = command.apply(t);
		}
		return result;
	}

	@Override
	public int getID() {
		return getLastActiveTurtle().getID();
	}

	@Override
	public double getX() {
		return getLastActiveTurtle().getX();
	}

	@Override
	public double getY() {
		return getLastActiveTurtle().getY();
	}

	@Override
	public double getHeading() {
		return getLastActiveTurtle().getHeading();
	}

	@Override
	public boolean getPenDown() {
		return getLastActiveTurtle().getPenDown();
	}

	@Override
	public boolean isVisible() {
		return getLastActiveTurtle().isVisible();
	}

	@Override
	public int getPenColorIndex() {
		return getLastActiveTurtle().getPenColorIndex();
	}

}
