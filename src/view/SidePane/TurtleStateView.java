package view.SidePane;

import model.ImmutableTurtle;
import view.CommandIO.TurtleView;

/**
 * Class allowing users to see attributes of current turtle.
 * 
 * @author DavidTran
 *
 */
public class TurtleStateView extends Window {

	public TurtleStateView(double height) {
		super(height);
		ta.appendText(myResources.getString("TurtleStateView"));
	}

	// add to interface
	public void update(ImmutableTurtle turtle) {
		ta.clear();

		ta.appendText(myResources.getString("TurtleStateView") + "\n\n");
		ta.appendText("ID: " + Integer.toString(turtle.getID()) + "\n");
		ta.appendText("X: " + Double.toString(turtle.getX()) + "\n");
		ta.appendText("Y: " + Double.toString(turtle.getY()) + "\n");
		ta.appendText("Heading: " + Double.toString(turtle.getHeading()) + "\n");
		ta.appendText("Pen Down: " + Boolean.toString(turtle.getPenDown()) + "\n");
		ta.appendText("Pen Color: " + TurtleView.colorList.get(turtle.getPenColorIndex()) + "\n");
		ta.appendText("Visibility: " + Boolean.toString(turtle.isVisible()) + "\n");

	}

}
