package view.API.ToolbarAPI;

import view.API.SubcomponentViewAPI;
import view.API.CommandIOAPI.TurtleImageOptionListener;

public interface TurtleImageOptionDisplay extends SubcomponentViewAPI{

	/**
	 * Give a listener to the color option box to detect selections.
	 * 
	 * @param l
	 *            Listener that detects when user selects a color option.
	 */
	public void addTurtleImageListener(TurtleImageOptionListener l);
	
}
