package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import view.TurtleListener;

/**
 * A representation of the state of the turtle at a given time. Notifies all
 * TurtleListeners when changes are made to the state.
 *
 */
public class Turtle implements ImmutableTurtle {
	
	private List<TurtleListener> listeners;
	private double x;
	private double initX;
	private double y;
	private double initY;
	private double heading;
	private double initHeading;
	private boolean penDown;
	private boolean isVisible;
	private Color penColor;
	
	public static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	
	public Turtle(double x0, double y0, double heading0) {
		x = initX = x0;
		y = initY = y0;
		heading = initHeading = heading0;
		penDown = true;
		isVisible = true;
		penColor = DEFAULT_PEN_COLOR;
	}
	
	public void addTurtleListener(TurtleListener tL) {
		listeners.add(tL);
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

	public Color getPenColor() {
		return penColor;
	}

	public void setXY(double newX, double newY) {
		x = newX;
		y = newY;
		for(TurtleListener tL : listeners) {
			tL.locationChange(newX, newY);
		}
	}

	public void setHeading(double newHeading) {
		heading = newHeading;
		for(TurtleListener tL : listeners) {
			tL.headingChange(newHeading);
		}
	}

	public void setPenDown(boolean down) {
		penDown = down;
		for(TurtleListener tL : listeners) {
			tL.penChange(down);
		}
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
		for(TurtleListener tL : listeners) {
			tL.visibilityChange(visible);
		}
	}

	public void setPenColor(Color color) {
		penColor = color;
		for(TurtleListener tL : listeners) {
			tL.penColorChange(color);
		}
	}

	/**
	 * Returns to original position, heading, visibility, and pen position, then notifies the listeners to clear
	 */
	public void clearScreen() {
		x = initX;
		y = initY;
		heading = initHeading;
		penDown = true;
		isVisible = true;
		penColor = DEFAULT_PEN_COLOR;
		for(TurtleListener tL : listeners) {
			tL.clearScreen();
		}
	}
}
