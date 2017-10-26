package model;

import java.util.ArrayList;
import java.util.List;

import view.API.CommandIOAPI.TurtleListener;

public class TurtleManager {

	private List<TurtleListener> turtleViewManagers;
	private List<Turtle> turtles;
	
	public TurtleManager() {
		turtleViewManagers = new ArrayList<>();
		turtles = new ArrayList<>();
	}
	
	public List<Turtle> getTurtles() {
		return turtles;
	}
	
	public void addTurtle(Turtle t) {
		boolean match = false;
		for(Turtle e : turtles) {
			if(e.getID() == t.getID()) match = true;
		}
		if(!match) {
			turtles.add(t);
		}
		//Throw Turtle already exists exception
	}
	
	public void addTurtleViewManager(TurtleListener tL) {
		turtleViewManagers.add(tL);
	}
 	
}
