package view.Animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ImmutableTurtle;
import view.Windows.TurtleStateView;

/**
 * Class to make the turtle viewable.
 *
 * @author DavidTran
 */
public class TurtleView implements TurtleListener, TurtleImageAPI {

	private static final double WIDTH = 35;
	private static final double HEIGHT = 35;
	private static final ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private static final ArrayList<String> imageNameList = new ArrayList<String>(new ArrayList<String>(
			Arrays.asList(myResources.getString("TurtleImages").replaceAll("\\s+", "").split(","))));
	public static final List<String> colorList = new ArrayList<String>(
			Arrays.asList(myResources.getString("PenColors").replaceAll("\\s+", "").split(",")));

	private ImageView myView;
	private int myPenColorIndex;
	private boolean myPenIsDown;
	private Pane myParent;
	private double myHeading;
	private boolean myIsActive;
	private int myID;

	private TurtleStateView stateListener;
	private boolean myIsToggled;
	private List<Image> imageList = new ArrayList<Image>();

	private double myOffsetX;
	private double myOffsetY;
	private double myPrevNewX;
	private double myPrevNewY;

	public TurtleView(Pane parent, Image image, int id) {
		myView = new ImageView(image);
		myView.setFitWidth(WIDTH);
		myView.setFitHeight(HEIGHT);
		myView.setLayoutX(-WIDTH / 2);
		myView.setLayoutY(-HEIGHT / 2);
		myView.setRotate(180);
		myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0)");
		myView.setOnMouseClicked(e -> clicked());
		myView.setOnMouseEntered(e -> entered());
		myView.setOnMouseEntered(e -> exited());

		myID = id;
		myPenColorIndex = 0;
		myPenIsDown = true;
		myIsActive = true;
		myIsToggled = true;
		myParent = parent;

		for (String s : imageNameList) {
			Image fileImage = new Image(getClass().getClassLoader().getResourceAsStream("resources/images/" + s));
			imageList.add(fileImage);
		}
	}

	/*************************** PUBLIC METHODS ********************************/

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		myOffsetX = myParent.getLayoutX();
		myOffsetY = myParent.getLayoutY();

		myView.setX(turtle.getX() + myOffsetX);
		myView.setY(turtle.getY() + myOffsetY);

		myID = turtle.getID();
		myHeading = turtle.getHeading();
		myPenColorIndex = turtle.getPenColorIndex();
		myPenIsDown = turtle.getPenDown();
		myView.setVisible(turtle.isVisible());
		//
	}

	@Override
	public void locationChange(double newX, double newY) {

		// compensate for center offset since center if not (0,0), returns value
		// referenced from center.
		double offsetNewX = newX + myOffsetX;
		double offsetNewY = newY + myOffsetY;

		double coordInsideX = offsetNewX % (myOffsetX * 2);
		double coordInsideY = offsetNewY % (myOffsetY * 2);

		boolean rightBound = (offsetNewX) / (myOffsetX * 2) >= 1.0;
		boolean upperBound = (offsetNewY) / (myOffsetY * 2) >= 1.0;
		boolean leftBound = (offsetNewX - myPrevNewX) / (myOffsetX * 2) <= -1.0;
		boolean lowerBound = (offsetNewY - myPrevNewY) / (myOffsetY * 2) <= -1.0;

		Line line;

		// if (rightBound && upperBound) {
		// line = new Line(myView.getX(), myView.getY(), myOffsetX * 2 - myPrevNewX,
		// myOffsetY * 2 - myPrevNewY);
		// line.setStroke(myPenColor);
		// myParent.getChildren().add(line);
		// myView.setX(0);
		// myView.setY(0);
		// System.out.println("OOB");
		// }
		// else if (rightBound) {
		// line = new Line(myView.getX(), myView.getY(), myOffsetX * 2 - myPrevNewX,
		// coordInsideY);
		// line.setStroke(myPenColor);
		// myParent.getChildren().add(line);
		// myView.setX(0);
		// System.out.println("OOB");
		// }
		// else if (upperBound) {
		// line = new Line(myView.getX(), myView.getY(), coordInsideX, myOffsetY * 2);
		// line.setStroke(myPenColor);
		// myParent.getChildren().add(line);
		// myView.setY(0);
		// System.out.println("OOB");
		// }

		// line = new Line(myView.getX(), myView.getY(), coordInsideX, coordInsideY);
		// line.setStroke(myPenColor);
		// myParent.getChildren().add(line);
		// myView.setX(coordInsideX);
		// myView.setY(coordInsideY);

		if (myPenIsDown) {
			line = new Line(myView.getX(), myView.getY(), offsetNewX, offsetNewY);
			line.setStroke(Color.valueOf(colorList.get(myPenColorIndex)));
			myParent.getChildren().add(line);
		}

		myView.setX(offsetNewX);
		myView.setY(offsetNewY);

		myPrevNewX = newX;
		myPrevNewY = newY;

		System.out.println("LayoutX: " + myOffsetX + " LayoutY: " + myOffsetY);
		System.out.println("myX: " + myView.getX() + " | myY: " + myView.getY());
		System.out.println("newX: " + newX + " | newY: " + newY);
		System.out.println("offsetNewX: " + offsetNewX + " | offsetNewY: " + offsetNewY);
		//
	}

	@Override
	public void headingChange(double newHeading) {
		// create an animation that rotates the shape
		if (myIsActive) {
			myHeading = -newHeading;
			myView.setRotate(180 - myHeading);
			//
		}
	}

	@Override
	public void penChange(boolean newState) {
		if (myIsActive) {
			myPenIsDown = newState;
			//
		}
	}

	@Override
	public void penColorChange(int index) {
		try {
			if (myIsActive) {
				myPenColorIndex = index;
				//
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	@Override
	public void visibilityChange(boolean visibility) {
		if (myIsActive) {
			myView.setVisible(visibility);
			//
		}
	}
	
	@Override
	public void activeToggle(boolean active) {
		myIsActive = active;
		//
	}

	@Override
	public void clearScreen() {
		// remove image from pane
		if (myIsActive)
			myParent.getChildren().remove(myView);
		myParent.getChildren().remove(myView);
	}

	@Override
	public void imageChange(int imageIndex) {
		if (myIsActive)
			myView.setImage(imageList.get(imageIndex));
	}

	@Override
	public ImageView getImageView() {
		return myView;
	}
	
	public void addTurtleStateListener(TurtleStateView l) {
		stateListener = l;
	}

	/*************************** PRIVATE METHODS ********************************/

	/**
	 * Make toggling viewable.
	 */
	private void clicked() {
		System.out.println("Clicked turtle");
		if (myIsActive)
			myView.setStyle("-fx-background-color:transparent");
		else
			myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0)");
		myIsActive = !myIsActive;
		//

		// MUST NOTIFY MODEL (that turtle is toggled)
	}

	/**
	 * Make mouse hovering noticeable.
	 */
	private void entered() {
		if (!myIsActive)
			myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
	}

	private void exited() {
		if (!myIsActive)
			myView.setStyle("-fx-background-color:transparent;");
	}
	
	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
