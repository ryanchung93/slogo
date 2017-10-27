package model;

import javafx.scene.paint.Color;

/**
 * A view-only version of 
 * @see Turtle
 */
public interface ImmutableTurtle {
	public int getID();
	
	public double getX();

	public double getY();

	public double getHeading();

	public boolean getPenDown();

	public boolean isVisible();

	public int getPenColorIndex();
	
	public void setActive(boolean active);

	public boolean isActive();
}
