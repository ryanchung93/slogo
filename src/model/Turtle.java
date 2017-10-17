package model;

import javafx.scene.paint.Color;
import view.API.TurtleListenerAPI;

/**
 * A representation of the state of the turtle at a given time. Notifies all
 * TurtleListeners when changes are made to the state.
 *
 */
public interface Turtle extends ImmutableTurtle {
	public void addTurtleListener(TurtleListenerAPI tL);

	public double getX();

	public double getY();

	public double getHeading();

	public boolean getPenDown();

	public boolean isVisible();

	public Color getPenColor();

	public void setXY(double x, double y);

	public void setHeading(double heading);

	public boolean setPenDown(boolean down);

	public void setVisible(boolean visible);

	public Color setPenColor(Color color);

	/**
	 * Returns to original position, heading, visibility, and pen position, then notifies the listeners to clear
	 */
	public void clearScreen();
}
