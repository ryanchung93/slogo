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
import model.SaverLoader;

/**
 * Class for setting up the canvas where turtles are placed.
 * 
 * @author DavidTran
 *
 */
public class CanvasView extends Pane {

	private Color DEFAULT_COLOR = Color.WHITE;
	private List<String> colorList;
	private int myColorIndex;

	/**
	 * Constructor
	 */
	public CanvasView(double width, double height, List<String> list) {
		this.setBackground(new Background(new BackgroundFill(DEFAULT_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setScaleY(-1);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		this.setLayoutX(this.getMaxWidth() / 2);
		this.setLayoutY(this.getMaxHeight() / 2);
		colorList = list;
	}

	public void backgroundColorChange(int colorIndex) {
		this.setBackground(new Background(new BackgroundFill(Color.valueOf(colorList.get(colorIndex)), CornerRadii.EMPTY, Insets.EMPTY)));
		myColorIndex = colorIndex;
	}
	
	public void save(String filePath) {
		SaverLoader.save(myColorIndex, filePath);
	}
	
	public void load(String filePath) {
		backgroundColorChange((int) SaverLoader.load(filePath));
	}
	
	public void update(List<String> list) {
		colorList = list;
	}
}