package controller;

import java.util.ArrayList;
import java.util.List;

import controller.API.DriverAPI;
import javafx.stage.Stage;
import model.CommandManager;
import model.Model;
import view.View;
import view.Animation.TurtleListener;

public class Driver implements DriverAPI {

	private View myView;
	private Model myModel;

	/**
	 * Constructor
	 */
	public Driver(Stage stage) {
		CommandManager commandManager = new CommandManager("resources.builders.completeCommands");
		myModel = new Model(commandManager, this::getListeners);
		myView = new View(stage, s -> languageChange(s), s -> myModel.execute(s), ()->run());
	}

	private List<TurtleListener> getListeners() {
		List<TurtleListener> list = new ArrayList<>();
		list.add(myView.getNewTurtleListener());
		list.add(myView.getStateViewListener());
		return list;
	}

	@Override
	public void run() {
		CommandManager commandManager = new CommandManager("resources.builders.completeCommands");
		myModel = new Model(commandManager, this::getListeners);
		myModel.addTurtle();
		myModel.addCommandListener(myView.getCommandListener());
		myModel.addCommandListener(myView.getUserDefinedCommandListener());
		myModel.addVariableListener(myView.getVariableListener());
	}

	private void languageChange(String language) {
		myModel.setLanguage(language);
	}
}
