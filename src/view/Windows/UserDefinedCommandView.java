package view.Windows;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import view.ErrorWindow;

/**
 * Class allowing users to see defined functions.
 * 
 * @author DavidTran
 *
 */
public class UserDefinedCommandView implements StringListener {

	private Map<String, List<String>> userCommands;
	private ScrollPane sp;
	private VBox vb;
	protected ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	public UserDefinedCommandView(double height) {
		sp = new ScrollPane();
		vb = new VBox(new Label(myResources.getString("UserDefinedCommandView") + "\n\n"));
		sp.setContent(vb);
	}

	@Override
	public void changedMap(Set<String> refMap, Map<String, List<String>> userCs) {
		userCommands = userCs;
		vb.getChildren().removeAll(vb.getChildren());

		vb.getChildren().add(new Label(myResources.getString("UserDefinedCommandView") + "\n\n"));

		for (String key : userCommands.keySet()) {
			Hyperlink hp = new Hyperlink(key + "\n");
			hp.setOnAction(e -> prompt(key));
			vb.getChildren().add(hp);
		}
	}

	private void prompt(String key) {
		if (!userCommands.get(key).isEmpty()) {

//			TextInputDialog dialog = new TextInputDialog();
//			dialog.setTitle("Update Variable Dialog");
//			dialog.setHeaderText("Update Variable");
//			dialog.setContentText("Please enter new variable value for \'" + key + "\':");
//
//			Optional<String> result = dialog.showAndWait();
//			try {
//				result.ifPresent(value -> variableMap.put(key, Double.parseDouble(value)));
//			} catch (NumberFormatException e) {
//				new ErrorWindow(e.getMessage());
//			}
		}
	}

	@Override
	public Parent getParent() {
		return sp;
	}

}
