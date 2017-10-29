package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import view.Animation.TurtleListener;

public class TurtleManager implements Iterable<SingularTurtle>, Turtle {

	private List<SingularTurtle> turtles;
	private Supplier<List<TurtleListener>> listenersProducer;

	public TurtleManager(Supplier<List<TurtleListener>> listenersProducer) {
		turtles = new ArrayList<>();
		this.listenersProducer = listenersProducer;
	}

	public void addTurtle() {
		SingularTurtle turtle = new SingularTurtle(0, 0, 0, turtles.size() + 1);
		for (TurtleListener tL : listenersProducer.get())
			turtle.addTurtleListener(tL);
		turtle.setNumTurtles(() -> getNumTurtles());
		turtles.add(turtle);
	}

	public int getNumTurtles() {
		return turtles.size();
	}

	@Override
	public Iterator<SingularTurtle> iterator() {
		return new Iterator<SingularTurtle>() {

			private List<SingularTurtle> activeTurtles = getActiveTurtles();
			private int nextIndex = 0;

			@Override
			public boolean hasNext() {
				if (nextIndex < activeTurtles.size())
					return true;
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
		for (SingularTurtle t : turtles) {
			if (t.isActive())
				activeTurtles.add(t);
		}
		return activeTurtles;
	}

	private SingularTurtle getLastActiveTurtle() {
		return getActiveTurtles().get(getActiveTurtles().size() - 1);
	}
 	
	@Override
	public double forward(Command command, CommandManager commands, VariableManager variables) {
		return doToEach(t -> t.forward(command, commands, variables));
	}
	
	@Override
	public double left(Command command, CommandManager commands, VariableManager variables) {
		return doToEach(t -> t.left(command, commands, variables));
	}
	
	@Override
	public double setXY(Command command1, Command command2, CommandManager commands, VariableManager variables) {
		return doToEach(t -> t.setXY(command1, command2, commands, variables));
	}
	
	@Override
	public double setHeading(Command command, CommandManager commands, VariableManager variables) {
		return doToEach(t -> t.setHeading(command, commands, variables));
	}
	
	@Override
	public double setTowards(Command command1, Command command2, CommandManager commands, VariableManager variables) {
		return doToEach(t -> t.setTowards(command1, command2, commands, variables));
	}

	@Override
	public double clearScreen() {
		return doToEach(t -> t.clearScreen());
	}

	@Override
	public void setPenDown(boolean b) {
		for (SingularTurtle t : this)
			t.setPenDown(b);
	}
	
	@Override
	public void setPenColor(int index) {
		for(SingularTurtle t : this) {
			t.setPenColor(index);
		}
	}
	
	@Override
	public void setPenSize(double size) {
		for(SingularTurtle t : this) {
			t.setPenSize(size);
		}
	}
	
	@Override
	public void setBackgroundColor(int index) {
		for(SingularTurtle t : turtles) {
			t.setBackgroundColor(index);
		}
	}

	@Override
	public void setVisible(boolean b) {
		for (SingularTurtle t : this)
			t.setVisible(b);
	}

	@Override
	public void setShapeIndex(int index) {
		for (SingularTurtle t : this)
			t.setShapeIndex(index);
	}

	private double doToEach(Function<SingularTurtle, Double> command) {
		double result = 0;
		for (SingularTurtle t : this) {
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

	@Override
	public double getPenSize() {
		return getLastActiveTurtle().getPenSize();
	}

	@Override
	public int getShapeIndex() {
		return getLastActiveTurtle().getShapeIndex();
	}

	public void ask(List<Integer> ids, Consumer<SingularTurtle> consumer) {
		for (Integer i : ids) {
			makeTurtlesTo(i);
			consumer.accept(turtles.get(i - 1));
		}
	}

	public void askAll(Consumer<SingularTurtle> consumer) {
		for (SingularTurtle t : turtles)
			consumer.accept(t);
	}

	public void setActiveTurtles(List<Integer> ids) {
		if (!ids.isEmpty())
			makeTurtlesTo(Collections.max(ids));
		for (SingularTurtle t : turtles) {
			t.setActive(ids.contains(t.getID()));
		}
	}

	private void makeTurtlesTo(int lastID) {
		while (turtles.size() < lastID)
			addTurtle();
	}

}
