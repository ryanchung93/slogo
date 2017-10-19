package controller;

import controller.API.DriverAPI;
import javafx.stage.Stage;
import model.Model;
import model.Turtle;
import view.View;

public class Driver implements DriverAPI {

	private View myView;
	private Model myModel;
	
	/**
	 * Constructor
	 */
	public Driver(Stage stage) {
		myView = new View(stage);
		myModel = new Model();
	}
	
	@Override
	public void run() {
		myModel.addTurtle(new Turtle(0, 0, 0), myView.getTurtleListener());
		myModel.addCommandListener(myView.getCommandListener());
		myModel.addVariableListener(myView.getVariableListener());
		//call myView.start();
	}
	
}
