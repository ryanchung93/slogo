package view.Animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.ImmutableTurtle;
import view.Toolbar.BackgroundColorListener;

/**
 * Class for setting up the canvas where turtles are placed.
 * 
 * @author DavidTran
 *
 */
public class CanvasView extends Pane implements TurtleListener {

	private Color DEFAULT_COLOR = Color.WHITE;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private final List<String> colorList = new ArrayList<String>(
			Arrays.asList(myResources.getString("BackgroundColors").replaceAll("\\s+", "").split(",")));

	/**
	 * Constructor
	 */
	public CanvasView(double width, double height) {
		this.setBackgroundColor(DEFAULT_COLOR);
		this.setScaleY(-1);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		this.setLayoutX(this.getMaxWidth() / 2);
		this.setLayoutY(this.getMaxHeight() / 2);
	}

	private void setBackgroundColor(Color c) {
		this.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	@Override
	public void backgroundColorChange(int colorIndex) {
		this.setBackgroundColor(Color.valueOf(colorList.get(colorIndex)));
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void locationChange(double newX, double newY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void headingChange(double newAngle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void penChange(boolean down) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visibilityChange(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void penColorChange(int colorIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activeToggle(boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void penThicknessChange(double thickness) {
		// TODO Auto-generated method stub
		
	}

}
