package view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.API.CanvasDisplay;

/**
 * Class for setting up the canvas where turtles are placed.
 * 
 * @author DavidTran
 *
 */
public class CanvasView extends Pane implements CanvasDisplay {

	/**
	 * Constructor
	 */
	public CanvasView(double width, double height) {
		this.setBackgroundColor(Color.BLACK);
		this.setScaleY(-1);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
	}
	
	@Override
	public void setBackgroundColor(Color c) {
		this.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
		
	}

}
