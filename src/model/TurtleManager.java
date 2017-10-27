package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import view.API.CommandIOAPI.TurtleListener;

public class TurtleManager implements Iterable<Turtle>{

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

	@Override
	public Iterator<Turtle> iterator() {
		return new Iterator<Turtle>() {

			//private List<Turtle> activeTurtles = 
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Turtle next() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
	
//	private List<Turtle> getActiveTurtles() {
//		List<Turtle> activeTurtles = new ArrayList<>();
//		for(Turtle t : turtles) {
//			
//		}
//	}
 	
}
