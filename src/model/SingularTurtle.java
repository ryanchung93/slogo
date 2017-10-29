package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import view.Animation.TurtleListener;

/**
 * A representation of the state of the turtle at a given time. Notifies all
 * TurtleListeners when changes are made to the state.
 *
 */
public class SingularTurtle implements ImmutableTurtle, Turtle {

	private List<TurtleListener> listeners;
	private int id;
	private double x;
	private double initX;
	private double y;
	private double initY;
	private double heading;
	private double initHeading;
	private boolean penDown;
	private boolean isVisible;
	private boolean isActive;
	private int penColorIndex;
	private int backgroundColorIndex;
	private double penSize;
	private int shapeIndex;
	private Supplier<Integer> numTurtles;

	public static final int DEFAULT_PEN_COLOR_INDEX = 0;
	public static final int DEFAULT_PEN_SIZE = 1;
	public static final int DEFAULT_BACKGROUND_COLOR_INDEX = 0;
	public static final int DEFAULT_SHAPE_INDEX = 0;
	
	public SingularTurtle(double x0, double y0, double heading0, int id) {
		this.id = id;
		x = initX = x0;
		y = initY = y0;
		heading = initHeading = heading0;
		penDown = true;
		isVisible = true;
		isActive = true;
		penColorIndex = DEFAULT_PEN_COLOR_INDEX;
		backgroundColorIndex = DEFAULT_BACKGROUND_COLOR_INDEX;
		penSize = DEFAULT_PEN_SIZE;
		shapeIndex = DEFAULT_SHAPE_INDEX;
		listeners = new ArrayList<>();
	}

	// need to add to interface
	public void addTurtleListener(TurtleListener tL) {
		listeners.add(tL);
		tL.setTurtle(this);
	}

	public void setNumTurtles(Supplier<Integer> numTurtles) {
		this.numTurtles = numTurtles;
	}

	public int getNumTurtles() {
		return numTurtles.get();
	}

	public int getID() {
		return id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeading() {
		return heading;
	}

	public boolean getPenDown() {
		return penDown;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public boolean isActive() {
		return isActive;
	}

	public int getPenColorIndex() {
		return penColorIndex;
	}

	public double getPenSize() {
		return penSize;
	}

	@Override
	public int getShapeIndex() {
		return shapeIndex;
	}

	public double setXY(double newX, double newY) {
		double dist = Math.sqrt(Math.pow(x - newX, 2) + Math.pow(y - newY, 2));
		x = newX;
		y = newY;
		for (TurtleListener tL : listeners)
			tL.locationChange(newX, newY);
		return dist;
	}

	public void setHeading(double newHeading) {
		heading = newHeading;
		for (TurtleListener tL : listeners)
			tL.headingChange(newHeading);
	}

	public void setPenDown(boolean down) {
		penDown = down;
		for (TurtleListener tL : listeners)
			tL.penChange(down);
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
		for (TurtleListener tL : listeners)
			tL.visibilityChange(visible);
	}

	public void setPenColor(int index) {
		penColorIndex = index;
		for (TurtleListener tL : listeners)
			tL.penColorChange(index);
	}

	@Override
	public void setPenSize(double pixels) {
		penSize = pixels;
		for (TurtleListener tL : listeners)
			tL.penSizeChange(penSize);
	}
	
	public void setBackgroundColor(int index) {
		backgroundColorIndex = index;
		for(TurtleListener tL : listeners) tL.backgroundColorChange(backgroundColorIndex);
	}

	@Override
	public void setShapeIndex(int index) {
		shapeIndex = index;
		for(TurtleListener tL : listeners) tL.shapeChange(index);
	}

	/**
	 * Returns to original position, heading, visibility, and pen position, then
	 * notifies the listeners to clear
	 */
	public double clearScreen() {
		double ret = setXY(initX, initY);
		setHeading(initHeading);
		setPenDown(true);
		setVisible(true);
		setPenColor(DEFAULT_PEN_COLOR_INDEX);
		for (TurtleListener tL : listeners)
			tL.clearScreen();
		return ret;
	}

	@Override
	public void setActive(boolean active) {
		isActive = active;
		for (TurtleListener listener : listeners)
			listener.activeToggle(active);
	}

	public double forward(Command par, CommandManager commands, VariableManager variables) {
		double result = par.execute(this, commands, variables);
		setXY(getX() - result * Math.sin(Math.toRadians(getHeading())),
				getY() + result * Math.cos(Math.toRadians(getHeading())));
		return result;
	}

	public double left(Command input, CommandManager commands, VariableManager variables) {
		double result = input.execute(this, commands, variables);
		setHeading(getHeading() + result);
		return result;
	}

	public double setHeading(Command input, CommandManager commands, VariableManager variables) {
		double result = input.execute(this, commands, variables);
		setHeading(result);
		return Math.abs(getHeading() - result);
	}

	public double setXY(Command xCor, Command yCor, CommandManager commands, VariableManager variables) {
		double xC = xCor.execute(this, commands, variables);
		double yC = yCor.execute(this, commands, variables);
		return setXY(xC, yC);
	}

	public double setTowards(Command xCor, Command yCor, CommandManager commands, VariableManager variables) {
		double newHeading = Math.toDegrees(Math.atan2(getX() - xCor.execute(this, commands, variables),
				yCor.execute(this, commands, variables) - getY()));
		if (newHeading < 0)
			newHeading += 360;
		double dtheta = newHeading - getHeading();
		setHeading(newHeading);
		return dtheta;
	}

}
