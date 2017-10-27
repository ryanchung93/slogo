package model;

import java.util.ArrayList;
import java.util.List;

import view.Animation.TurtleListener;

/**
 * A representation of the state of the turtle at a given time. Notifies all
 * TurtleListeners when changes are made to the state.
 *
 */
public class Turtle implements ImmutableTurtle {
	
	private List<TurtleListener> listeners;
	private Parser myParser;
	private int id;
	private double x;
	private double initX;
	private double y;
	private double initY;
	private double heading;
	private double initHeading;
	private boolean penDown;
	private boolean isVisible;
	private int penColorIndex;
	
	public static final int DEFAULT_PEN_COLOR_INDEX = 0;
	
	public Turtle(double x0, double y0, double heading0, int id) {
		this.id = id;
		x = initX = x0;
		y = initY = y0;
		heading = initHeading = heading0;
		penDown = true;
		isVisible = true;
		penColorIndex = DEFAULT_PEN_COLOR_INDEX;
		listeners = new ArrayList<>();
	}
	
	// need to add to interface
	public void addTurtleListener(TurtleListener tL) {
		listeners.add(tL);
		tL.setTurtle(this);
	}
	
	public void setParser(Parser p) {
		myParser = p;
	}
	
	public Parser getParser() {
		return myParser;
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

	public int getPenColorIndex() {
		return penColorIndex;
	}

	public void setXY(double newX, double newY) {
		x = newX;
		y = newY;
		for(TurtleListener tL : listeners) tL.locationChange(newX, newY);
	}

	public void setHeading(double newHeading) {
		heading = newHeading;
		for(TurtleListener tL : listeners) tL.headingChange(newHeading);
	}

	public void setPenDown(boolean down) {
		penDown = down;
		for(TurtleListener tL : listeners) tL.penChange(down);
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
		for(TurtleListener tL : listeners) tL.visibilityChange(visible);
	}
	
	public void setPenColor(int index) {
		penColorIndex = index;
		for(TurtleListener tL : listeners) tL.penColorChange(index);
	}

    /**
	 * Returns to original position, heading, visibility, and pen position, then notifies the listeners to clear
	 */
	public void clearScreen() {
		setXY(initX, initY);
		setHeading(initHeading);
		setPenDown(true);
		setVisible(true);
		setPenColor(DEFAULT_PEN_COLOR_INDEX);
		for(TurtleListener tL : listeners) tL.clearScreen();
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
		double dx = xC - getX();
		double dy = yC - getY();
		setXY(xC,yC);
		return Math.sqrt(dx*dx+dy*dy);
	}

	public double setTowards(Command xCor, Command yCor, CommandManager commands, VariableManager variables) {
		double newHeading = Math.toDegrees(
				Math.atan2(getX() - xCor.execute(this, commands, variables), 
						yCor.execute(this, commands, variables) - getY()));
		if(newHeading < 0)
			newHeading += 360;
		double dtheta = newHeading - getHeading();
		setHeading(newHeading);
		return dtheta;
	}	
}
