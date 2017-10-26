package view.SidePane;

import java.util.Set;
import view.API.SidePane.StringListener;

/**
 * A Pane representing the basic instructions for using SLogo.
 * 
 * @author DavidTran
 *
 */
public class ReferenceView extends Window implements StringListener {

	public ReferenceView(double height) {
		super(height);
		ta.appendText(myResources.getString("ReferenceView") + "\n");
	}

	@Override
	public void changedMap(Set<String> refMap, Set<String> commandSet) {
		ta.clear();
		ta.appendText(myResources.getString("ReferenceView") + "\n\n");
		for (String key : refMap) {
			ta.appendText(key + "\n");
		}
		ta.setScrollTop(0);

	}

}
