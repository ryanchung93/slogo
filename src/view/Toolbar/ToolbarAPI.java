package view.Toolbar;

/**
 * Methods for view components in Toolbar.
 * 
 * @author taekwhunchung
 *
 */

public interface ToolbarAPI {

	/**
	 * method used to get BackgroundOptionView to then call .makeChoiceBox() method
	 * when starting or loading application.
	 * 
	 * @return BackgroundOptionView in ToolbarView
	 */
	public BackgroundOptionView getBackgroundOptionView();

	/**
	 * method used to get PenOptionView to then call .makeChoiceBox() method when
	 * starting or loading application.
	 * 
	 * @return PenOptionView in ToolbarView
	 */
	public PenOptionView getPenOptionView();

	/**
	 * 
	 * @return
	 */
	public PenSlider getPenSlider();

	/**
	 * 
	 * @return
	 */
	public PenButtons getPenButtons();

	/**
	 * method used to get LanguageOptionView to then call
	 * .addLanguageOptionListener.
	 * 
	 * @return PenButtons in ToolbarView
	 */
	public LanguageOptionView getLanguageOptionView();

	/**
	 * 
	 * @return
	 */
	public TurtleImageOptionView getImageOptionView();

}
