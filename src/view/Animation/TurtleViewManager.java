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

	private List<TurtleView> turtleList;
	private final CanvasView myParent;
	private Image myImage;

	public TurtleViewManager(CanvasView parent, Image image) {
		turtleList = new ArrayList<TurtleView>();
		myParent = parent;
		myImage = image;
	}

	public void addTurtle() {
		TurtleView turtleView = new TurtleView(myParent, myImage);		
		turtleList.add(turtleView);
		myParent.getChildren().add(turtleView.getImageView());
//		tsv.addTurtleList(this.getImmutableTurtleList());
		
	}

	public TurtleListener getNewListener() {
		addTurtle();
		return turtleList.get(turtleList.size()-1);
	}

//	@Override
//	public void imageChange(int imageIndex) {
//		for(TurtleView turtle : turtleList) {
//			turtle.imageChange(imageIndex);//TODO
//		}
//	}
}
