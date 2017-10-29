package view.Toolbar;

/**
 * Class for detecting changes to background choice box.
 * 
 * @author DavidTran
 *
 */
public interface BackgroundColorListener extends ViewListener {

	/**
	 * Changes the background color.
	 */
	public void backgroundColorChange(int colorIndex);
	
}
