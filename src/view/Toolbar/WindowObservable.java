package view.Toolbar;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Class that stores active windows (windows that users can view) in ArrayList
 * as string.
 * 
 * Extends Observable, so any class that implements Observer can
 * detect change in this class.
 * 
 * @author taekwhunchung
 *
 */

public class WindowObservable<T> extends Observable {
	
	private ArrayList<String> windows;

	/**
	 * 
	 * Constructor for Observable ArrayList of active windows as Strings
	 * 
	 * @param activeWindows
	 */
	
	public WindowObservable(ArrayList<String> activeWindows) {
		windows = activeWindows;
		for (String window : windows) {
			System.out.println("Y" + window);
		}
	}

	/**
	 * 
	 * method to 
	 * @param t
	 */
	public void add(T t) {
		String str = (String) t;
		windows.add(str);
		this.setChanged();
		notifyObservers(t);
	}

	public void remove(T t) {
		String str = (String) t;
		windows.remove(str);
		this.setChanged();
		notifyObservers(t);
	}

	public boolean contains(T t) {
		return windows.contains(t);
	}

	public ArrayList<String> getList() {
		return windows;
	}

}
