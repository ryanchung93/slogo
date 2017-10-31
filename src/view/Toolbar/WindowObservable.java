package view.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class WindowObservable<T> extends Observable {
	private ArrayList<String> windows;
	
	public WindowObservable(ArrayList<String> activeWindows) {
		windows = activeWindows;
		/*for (String window : windows) {
			System.out.println("Y" + window);
		}*/
	}
	
	public void add(T t) {
		String str = (String) t;
		windows.add(str);
		
		//System.out.println("ACTIVEVIEWS: ");
		/*for (String s : windows) {
			System.out.println(s);
		}
		System.out.println("============");*/
		this.setChanged();
		notifyObservers(t);
	}
	
	public void remove(T t) {
		String str = (String) t;
		windows.remove(str);
		
		//System.out.println("ACTIVEVIEWS: ");
		for (String s : windows) {
			//System.out.println(s);
		}
		
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
