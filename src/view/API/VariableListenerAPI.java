package view.API;

import java.util.Map;

/**
 * Listens for changes to the Map representing the variables -- gets a new map after changes are made
 */
public interface VariableListenerAPI {
	/**
	 * @param vars An **immutable** map representing the variables available
	 */
	public void changedMap(Map<String, Double> vars);
}
