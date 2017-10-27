package view.API.CommandIOAPI;

/**
 * Allows detection of image changes for turtles.
 * 
 * @author DavidTran
 *
 */
public interface TurtleImageOptionListener {

	/**
	 * Called when image changes
	 * 
	 * @param imageIndex
	 *            index of image list
	 */
	public void imageChange(int imageIndex);

}
