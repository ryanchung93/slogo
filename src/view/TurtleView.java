package view;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.ImmutableTurtle;
import view.API.TurtleListener;

/**
 * Class to make the turtle viewable.
 * 
 * @author DavidTran
 *
 */
public class TurtleView implements TurtleListener {

	private static final double WIDTH = 25;
	private static final double HEIGHT = 25;

	private ImageView myView;
	private Color myPenColor;
	
	private boolean myPenIsDown;
	private Pane myParent;
	private double myHeading;
	private double offsetX;
	private double offsetY;


	public TurtleView(Pane parent, Image image) {
		//for testing
		myView = new ImageView(image);
		myView.setFitWidth(WIDTH);
		myView.setFitHeight(HEIGHT);
		myView.setLayoutX(-WIDTH/2);
		myView.setLayoutY(-HEIGHT/2);
		myView.setX(0);
		myView.setY(0);
		headingChange(180);

		myPenColor = Color.WHITE;
		myPenIsDown = true;
		myParent = parent;
//		parent.getChildren().addAll(myView);
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		offsetX = myView.getParent().getLayoutX();
		offsetY = myView.getParent().getLayoutY();
		
		myView.setX(turtle.getX() + offsetX);
		myView.setY(turtle.getY() + offsetY);
		
		myHeading = turtle.getHeading();
		myPenColor = turtle.getPenColor();
		myPenIsDown = turtle.getPenDown();
		myView.setVisible(turtle.isVisible());

	}

	@Override
	public void locationChange(double newX, double newY) {
//		System.out.println("x :" + myView.getX() + " " + myView.getY());
		
		// compensate for center offset since center if not (0,0)
		double offsetNewX = newX + offsetX;
		double offsetNewY = newY + offsetY;
		
		Line line = new Line(myView.getX(), myView.getY(), offsetNewX, offsetNewY);
		line.setStroke(myPenColor);
		
		if (myPenIsDown) 
			myParent.getChildren().add(line);
		
		myView.setX(offsetNewX);
		myView.setY(offsetNewY);
		
//		System.out.println("LayoutX: " +  offsetX + " LayoutY: " + offsetY);
//		System.out.println("myX: " + myView.getX() + " | myY: " + myView.getY());
//		System.out.println("newX: " + newX + " | newY: " + newY);
//		System.out.println("offsetNewX: " + offsetNewX + " | offsetNewY: " + offsetNewY);
		

	}

	@Override
	public void headingChange(double dtheta) {
		// TODO Auto-generated method stub
		// create an animation that rotates the shape
		myView.setRotate(dtheta);

	}

	@Override
	public void penChange(boolean newState) {
		myPenIsDown = newState;
	}

	@Override
	public void penColorChange(Color color) {
		myPenColor = color;

	}

	@Override
	public void visibilityChange(boolean visibility) {
		myView.setVisible(visibility);

	}

	@Override
	public void clearScreen() {
		// remove image from pane
		((Pane) myView.getParent()).getChildren().remove(myView);

	}
	
	public ImageView getImage() {
		return myView;
	}

}
