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
}
