package view.Windows;

import java.util.ArrayList;
import java.util.List;

import model.ImmutableTurtle;
import view.Animation.TurtleListener;
import view.Animation.TurtleView;
import view.Toolbar.TurtleImageOptionListener;

/**
 * Class allowing users to see attributes of current turtle.
 * 
 * @author DavidTran
 *
 */
public class TurtleStateView extends Window implements TurtleListener, TurtleImageOptionListener {
	private List<ImmutableTurtle> turtles = new ArrayList<>();;

	public TurtleStateView(double height) {
		super(height);
		ta.appendText(myResources.getString("TurtleStateView"));
	}

	// add to interface
	private void update() {
		ta.clear();
		ta.appendText(myResources.getString("TurtleStateView") + "\n\n");
		
		for (ImmutableTurtle turtle : turtles) {
			
			if (turtle.isActive()) {
				ta.appendText("ID: " + Integer.toString(turtle.getID()) + "\n");
				ta.appendText(String.format("X: %5.3f\n", turtle.getX()));
				ta.appendText(String.format("Y: %5.3f\n", turtle.getY()));
				ta.appendText(String.format("Heading: %5.1f\n", turtle.getHeading()));
				ta.appendText("Pen Down: " + Boolean.toString(turtle.getPenDown()) + "\n");
				ta.appendText("Pen Color: " + TurtleView.colorList.get(turtle.getPenColorIndex()) + "\n");
				ta.appendText("Pen Thickness: " + turtle.getPenSize() + "\n");
				ta.appendText("Visibility: " + Boolean.toString(turtle.isVisible()) + "\n\n");
			}
		}
	}

	@Override
	public void imageChange(int imageIndex) {
		update();
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		turtles.add(turtle);
		System.out.println("added turtle");
		update();
	}

	@Override
	public void locationChange(double newX, double newY) {
		update();
	}

	@Override
	public void headingChange(double newAngle) {
		update();
	}

	@Override
	public void penChange(boolean down) {
		update();
	}

	@Override
	public void visibilityChange(boolean isVisible) {
		update();
	}

	@Override
	public void penColorChange(int colorIndex) {
		update();
	}

	@Override
	public void clearScreen() {
		update();
	}

	@Override
	public void activeToggle(boolean active) {
		update();
	}

	@Override
	public void penSizeChange(double thickness) {
		update();
		
	}

	@Override
	public void shapeChange(int index) {
		update();asdfa
		
	}

}
