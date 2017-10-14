package view;

import model.ImmutableTurtle;

/**
 * Listens for modifications to a turtle's state -- its methods must be called
 * when these changes occur
 *
 */
public interface TurtleListener {
	/**
	 * Gives the listener a Turtle to keep track of the state
	 * @param turtle A turtle with an observable state
	 */
	public void setTurtle(ImmutableTurtle turtle);

	/**
	 * Called when the turtle moves coordinates
	 * @param dx change in x
	 * @param dy change in y
	 */
	public void locationChange(double dx, double dy);

	/**
	 * Called when the turtle turns
	 * @param dtheta change in heading
	 */
	public void headingChange(double dtheta);

	/**
	 * Called when the pen's state (up/down) is toggled
	 */
	public void penChange();

	/**
	 * Called when the visibility is toggled
	 */
	public void visibilityChange();

	/**
	 * Called when the screen should be cleared
	 */
	public void clearScreen();
}