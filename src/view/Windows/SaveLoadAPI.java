package view.Windows;

/**
 * methods for saving/loading view components.
 * 
 * @author taekwhunchung
 *
 */

public interface SaveLoadAPI {
	
	/**
	 * save view components
	 * 
	 * @param filePath
	 */

	public void save(String filePath);

	/**
	 * load view components
	 * 
	 * @param filePath
	 */
	
	public void load(String filePath);
	

}
