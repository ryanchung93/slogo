package view.Windows;

import java.util.Set;

/**
 * Class allowing users to see defined functions.
 * 
 * @author DavidTran
 *
 */
public class UserDefinedCommandView extends Window implements StringListener {

	public UserDefinedCommandView(double height) {
		super(height);
		ta.appendText(myResources.getString("UserDefinedCommandView"));
	}

	@Override
	public void changedMap(Set<String> refMap, Set<String> commandSet) {
		ta.clear();
		ta.appendText(myResources.getString("UserDefinedCommandView") + "\n\n");
		for (String key : commandSet) {
			ta.appendText(key + "\n");
		}
	}

}
