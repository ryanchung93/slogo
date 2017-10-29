package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorWindow {

	public ErrorWindow(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
