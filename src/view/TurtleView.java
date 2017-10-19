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

public class TurtleView implements TurtleListener {

	private static final double WIDTH = 100;
	private static final double HEIGHT = 100;

	private ImageView myView;
	private Color myPenColor;
	private double myPenX;
	private double myPenY;
	
	private boolean myPenIsDown;
	private Pane myParent;
	private double myHeading;


	public TurtleView(Pane parent, Image image) {
		//for testing
		myView = new ImageView(image);
		myView.setFitWidth(WIDTH);
		myView.setFitHeight(HEIGHT);
		myView.setLayoutX(-WIDTH/2);
		myView.setLayoutY(-HEIGHT/2);
		myView.setX(0);
		myView.setY(0);
		myPenX = 0;
		myPenY = 0;

		myPenColor = Color.WHITE;
		myPenIsDown = true;
		myParent = parent;
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		myView.setX(turtle.getX() + myView.getParent().getLayoutX());
		myView.setY(turtle.getY() + myView.getParent().getLayoutY());
		myHeading = turtle.getHeading();
		myPenColor = turtle.getPenColor();
		myPenIsDown = turtle.getPenDown();
		myView.setVisible(turtle.isVisible());

	}

	@Override
	public void locationChange(double newX, double newY) {
		
		double myX = myPenX + myView.getParent().getLayoutX();
		double myY = myPenY + myView.getParent().getLayoutY();
		// compensate for center offset since center if not (0,0)
		double offsetNewX = newX + myView.getParent().getLayoutX();
		double offsetNewY = newY + myView.getParent().getLayoutY();
		
		Line line = new Line(myX, myY, offsetNewX, offsetNewY);
		line.setStroke(myPenColor);
//		lines.add(line);
		myParent.getChildren().add(line);
//		PathTransition pt = new PathTransition(Duration.millis(1000), line, myView);
//		pt.play();
		
		myView.setX(offsetNewX);
		myView.setY(offsetNewY);
		myPenX = newX;
		myPenY = newY;

	}

	@Override
	public void headingChange(double dtheta) {
		// TODO Auto-generated method stub
		// create an animation that rotates the shape
		RotateTransition rt = new RotateTransition(Duration.seconds(3));
		rt.setByAngle(90);

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
