package view;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.ImmutableTurtle;
import view.API.TurtleListener;

public class TurtleView implements TurtleListener {

	private static final double WIDTH = 25;
	private static final double HEIGHT = 25;

	private ImageView myView;
	private Color myPenColor;
	private boolean myPenIsDown;
	private Pane myParent;
	private double myHeading;

	public TurtleView(Pane parent, Image image) {
		//for testing
		myView = new ImageView(image);
		myView.setFitWidth(WIDTH);
		myView.setFitHeight(HEIGHT);
		myView.setX(0);
		myView.setY(0);
		myPenColor = Color.WHITE;
		myPenIsDown = true;
		myParent = parent;
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		myView.setX(turtle.getX());
		myView.setY(turtle.getY());
		myHeading = turtle.getHeading();
		myPenColor = turtle.getPenColor();
		myPenIsDown = turtle.getPenDown();
		myView.setVisible(turtle.isVisible());

	}

	@Override
	public void locationChange(double newX, double newY) {
		
		double myX = myView.getX() + myView.getParent().getLayoutX();
		double myY = myView.getY() + myView.getParent().getLayoutY();
		// compensate for center offset since center if not (0,0)
		double realNewX = newX + myView.getParent().getLayoutX();
		double realNewY = newY + myView.getParent().getLayoutY();
		
		Line line = new Line(myX, myY, realNewX, realNewY);
		line.setStroke(myPenColor);
//		lines.add(line);
		myParent.getChildren().add(line);
		PathTransition pt = new PathTransition(Duration.millis(1000), line, myView);
		pt.play();

		myView.setX(realNewX - myView.getFitWidth()/2);
		myView.setY(realNewY - myView.getFitHeight()/2);

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
