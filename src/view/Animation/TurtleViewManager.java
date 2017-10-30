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

	private List<TurtleView> mTurtleList;
	private List<String> myColorList;
	private List<String> myImageNameList;
	private final CanvasView myParent;
	private Image myImage;

	public TurtleViewManager(CanvasView parent, Image image, List<String> imgList, List<String> colorList) {
		mTurtleList = new ArrayList<TurtleView>();
		myParent = parent;
		myImage = image;
		myImageNameList = imgList;
		myColorList = colorList;
	}

	public void addTurtle() {
		TurtleView turtleView = new TurtleView(myParent, myImage, myImageNameList, myColorList);		
		mTurtleList.add(turtleView);
		myParent.getChildren().add(turtleView.getImageView());
	}

	public TurtleListener getNewListener() {
		addTurtle();
		return mTurtleList.get(mTurtleList.size()-1);
	}
}
