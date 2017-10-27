package view.Animation;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import model.ImmutableTurtle;
import view.Windows.TurtleStateView;

/**
 * Class that manages the viewing of turtles.
 * 
 * @author DavidTran
 */
public class TurtleViewManager implements TurtleImageOptionListener {

	private List<TurtleView> turtleList;
	private final Pane myParent;
	private Image myImage;
	private TurtleStateView stateView;

	public TurtleViewManager(Pane parent, Image image) {
		turtleList = new ArrayList<TurtleView>();
		myParent = parent;
		myImage = image;
	}

	public void addTurtle() {
		TurtleView turtleView = new TurtleView(myParent, myImage, turtleList.size());		
		turtleList.add(turtleView);
		myParent.getChildren().add(turtleView.getImageView());
	}

	public TurtleListener getListener(int id) {
		if(id >= turtleList.size())
			addTurtle();
		return turtleList.get(id);
	}

	@Override
	public void imageChange(int imageIndex) {
		for(TurtleView turtle : turtleList) {
			turtle.imageChange(imageIndex);//TODO
		}
	}
}
