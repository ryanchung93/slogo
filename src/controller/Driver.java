package controller;

import controller.API.DriverAPI;
import javafx.stage.Stage;
import model.CommandManager;
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
		myView = new View(stage, s -> myModel.execute(s));
		CommandManager commandManager = new CommandManager("resources.builders.basicCommands");
		myModel = new Model(commandManager);
	}
	
	@Override
	public void run() {
		Turtle t = new Turtle(0, 0, 0);
		myModel.addTurtle(t, myView.getTurtleListener());
		myModel.addCommandListener(myView.getCommandListener());
		myModel.addVariableListener(myView.getVariableListener());
	}
	
}
