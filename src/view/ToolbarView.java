package view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import view.API.SubcomponentViewAPI;

/**
 * Class to view toolbar.
 * 
 * @author DavidTran
 *
 */
public class ToolbarView implements SubcomponentViewAPI {
	
	private HBox myToolbar;
	
	public ToolbarView() {
		
		myToolbar = new HBox();
		
		
	}
	@Override
	public Parent getParent() {
		return myToolbar;
	}
	
	// Must add to API
	public void addNode(Node e) {
		myToolbar.getChildren().add(e);
	}

}
