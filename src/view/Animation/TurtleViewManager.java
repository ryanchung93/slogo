package view.Animation;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * Class that manages the viewing of turtles.
 * 
 * @author DavidTran
 */

public class TurtleViewManager {

	private List<TurtleView> myTurtleList;
	private List<String> myColorList;
	private List<String> myImageNameList;
	private final CanvasView myParent;
	private Image myImage;

	public TurtleViewManager(CanvasView parent, Image image, List<String> imgList, List<String> colorList) {
		myTurtleList = new ArrayList<TurtleView>();
		myParent = parent;
		myImage = image;
		myImageNameList = imgList;
		myColorList = colorList;
	}

	public void addTurtle() {
		TurtleView turtleView = new TurtleView(myParent, myImage, myImageNameList, myColorList);		
		myTurtleList.add(turtleView);
		myParent.getChildren().add(turtleView.getImageView());
	}

	public TurtleListener getNewListener() {
		addTurtle();
		return myTurtleList.get(myTurtleList.size()-1);
	}
	
	public void clear() {
		myTurtleList.clear();
		myParent.getChildren().clear();
	}
}
