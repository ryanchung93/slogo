package view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import view.API.SubcomponentViewAPI;

/**
 * Class to view toolbar.
 * 
 * @author DavidTran
 *
 */
public class ToolbarView implements SubcomponentViewAPI {

	private static final double NODE_SPACING = 30;
	private HBox myToolbar;
	private Hyperlink myHelpLink;
	private BackgroundOptionView myBackgroundOptionView;

	public ToolbarView() {

		myToolbar = new HBox();
		myToolbar.setAlignment(Pos.CENTER);
		myToolbar.setSpacing(NODE_SPACING);

		addBackgroundColorOption();
		addHelpLink();

	}

	@Override
	public Parent getParent() {
		return myToolbar;
	}

	// Must add to API
	public BackgroundOptionView getBackgroundOptionView() {
		return myBackgroundOptionView;
	}

	private void addHelpLink() {

		myHelpLink = new Hyperlink();
		myHelpLink.setText("Help");
		
		myHelpLink.setOnAction(e -> {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(
							new URI("http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php"));
				} catch (IOException e1) {
					e1.printStackTrace();
					showError(e1.getMessage());
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});

		myToolbar.getChildren().add(myHelpLink);

	}

	private void addBackgroundColorOption() {
		myBackgroundOptionView = new BackgroundOptionView();
		myToolbar.getChildren().add(myBackgroundOptionView.getParent());

	}

	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
