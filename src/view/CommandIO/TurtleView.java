package view.CommandIO;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ImmutableTurtle;
//import view.API.PenOptionListener;
import model.Turtle;
import model.UserTurtle;
import view.API.TurtleImageViewAPI;
import view.API.TurtleListener;

/**
 * Class to make the turtle viewable.
 *
 * @author DavidTran
 */
public class TurtleView implements TurtleListener, TurtleImageViewAPI {

    private static final double WIDTH = 35;
    private static final double HEIGHT = 35;

    private ImageView myView;

    private Color myPenColor;
    private boolean myPenIsDown;
    private Pane myParent;
    private double myHeading;
    private boolean myIsToggled;
    private UserTurtle myBackEndTurtle;

    private double myOffsetX;
    private double myOffsetY;
    private double myPrevNewX;
    private double myPrevNewY;

    public TurtleView(Pane parent, Image image) {
        // for testing
        myView = new ImageView(image);
        myView.setFitWidth(WIDTH);
        myView.setFitHeight(HEIGHT);
        myView.setLayoutX(-WIDTH / 2);
        myView.setLayoutY(-HEIGHT / 2);
        myView.setX(0);
        myView.setY(0);
        headingChange(0);

        myView.setRotate(180);

        setMouseEvents();

        myPenColor = Color.WHITE;
        myPenIsDown = true;
        myIsToggled = false;
        myParent = parent;
    }

    @Override
    public void setTurtle(ImmutableTurtle immutableTurtle, UserTurtle userTurtle) {
        myOffsetX = myParent.getLayoutX();
        myOffsetY = myParent.getLayoutY();

        myView.setX(immutableTurtle.getX() + myOffsetX);
        myView.setY(immutableTurtle.getY() + myOffsetY);

        myBackEndTurtle = userTurtle;
        myHeading = immutableTurtle.getHeading() + 180;
        myPenColor = immutableTurtle.getPenColor();
        myPenIsDown = immutableTurtle.getPenDown();
        myView.setVisible(immutableTurtle.isVisible());

    }


    /**
     * Allow turtle imageviews to change with mouse interactions
     */
    private void setMouseEvents() {

        myView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                // do stuff (toggle turtle)
                System.out.println("Clicked turtle");
                if (myIsToggled)
                    myView.setStyle("-fx-background-color:transparent");
                else
                    myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0)");
                myIsToggled = !myIsToggled;

                //MUST NOTIFY MODEL

            }
        });

        myView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (!myIsToggled)
                    myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
            }
        });

        myView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (!myIsToggled)
                    myView.setStyle("-fx-background-color:transparent;");
            }
        });

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
            line.setStroke(myPenColor);
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
    }

    @Override
    public void headingChange(double newHeading) {
        // create an animation that rotates the shape
        if (myIsToggled) {
            double newAngle = -newHeading;
            myView.setRotate(180 - newAngle);
            System.out.println("NewHeading: " + (-newHeading));
        }
    }

    @Override
    public void penChange(boolean newState) {
        if (myIsToggled)
            myPenIsDown = newState;
    }

    @Override
    public void penColorChange(Color color) {
        if (myIsToggled) {
            myPenColor = color;
//            if ( color != myBackEndTurtle.getPenColor())
//                myBackEndTurtle.setPenColor(color);
        }

    }

    @Override
    public void visibilityChange(boolean visibility) {
        if (myIsToggled)
            myView.setVisible(visibility);

    }

    @Override
    public void clearScreen() {
        // remove image from pane
        if (myIsToggled)
            myParent.getChildren().remove(myView);

    }

    @Override
    public void handleInput(KeyCode code) {
        System.out.println(code);

        // must change the back-end turtle!!!

        if (myIsToggled) {
            switch (code) {
                case W:
                    locationChange(myView.getX() - myOffsetX, myView.getY() - myOffsetY + 1);
                    myBackEndTurtle.translate(0, 1);
                    break;
                case S:
                    locationChange(myView.getX() - myOffsetX, myView.getY() - myOffsetY - 1);
                    myBackEndTurtle.translate(0, -1);
                    break;
                case A:
                    locationChange(myView.getX() - myOffsetX - 1, myView.getY() - myOffsetY);
                    myBackEndTurtle.translate(-1, 0);
                    break;
                case D:
                    locationChange(myView.getX() - myOffsetX + 1, myView.getY() - myOffsetY);
                    myBackEndTurtle.translate(1, 0);
                    break;
                case R:
                    headingChange(180 + myView.getRotate() + 2);
                    myBackEndTurtle.rotate(2);
                    break;
                case T:
                    headingChange(180 + myView.getRotate() - 2);
                    myBackEndTurtle.rotate(-2);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void imageChange(Image image) {
        if (myIsToggled)
            myView.setImage(image);
    }

    public boolean isToggled() {
        return myIsToggled;
    }

    @Override
    public ImageView getImageView() {
        return myView;
    }

    public UserTurtle getMyTurtle() {
        return myBackEndTurtle;
    }

}
