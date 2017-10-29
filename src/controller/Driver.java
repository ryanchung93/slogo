package controller;

import java.util.ArrayList;
import java.util.List;

import controller.API.DriverAPI;
import javafx.stage.Stage;
import model.CommandManager;
import model.Model;
import model.SLogoException;
import view.View;
import view.Animation.TurtleListener;

public class Driver implements DriverAPI {

	private View myView;
	private Model myModel;

	/**
	 * Constructor
	 */
	public Driver(Stage stage) {
		myView = new View(stage, s -> languageChange(s), s -> execute(s));
		CommandManager commandManager = new CommandManager("resources.builders.completeCommands");
		myModel = new Model(commandManager, this::getListeners);
	}

	private List<TurtleListener> getListeners() {
		List<TurtleListener> list = new ArrayList<>();
		list.add(myView.getNewTurtleListener());
		list.add(myView.getStateViewListener());
//		list.add(myView.getCanvasListener());
		return list;
	}
	
	private void execute(String s) {
		try {
			myModel.execute(s);
		} catch (SLogoException e) {
			myView.display(e);
		}
	}

	@Override
	public void run() {
		myModel.addTurtle();
		
		myModel.addCommandListener(myView.getCommandListener());
		myModel.addCommandListener(myView.getUserDefinedCommandListener());
		myModel.addVariableListener(myView.getVariableListener());
	}

	private void languageChange(String language) {
		myModel.setLanguage(language);
	}
}
