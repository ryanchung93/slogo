package view;

/**
 * Listens for the addition of new strings -- specifically used to get
 * representations of new commands
 */
public interface StringListener {
	/**
	 * Called when a new string becomes relevant to the particular context
	 * 
	 * @param s
	 *            The new String
	 */
	public void newString(String s);
}
