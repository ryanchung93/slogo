package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.API.VariableListener;

public class VariableManager {
	private Map<String, Double> globals = new HashMap<>();
	private List<Map<String, Double>> localScopes = new ArrayList<>();
	private List<VariableListener> listeners = new ArrayList<>();
	private boolean updated;
	
	public VariableManager() {
	}
	
	public Double getValue(String s) {
		if(getLocalScope().containsKey(s))
			return getLocalScope().get(s);
		return globals.get(s);
	}
	
	public void setValue(String s, double val) {
		if(getLocalScope().containsKey(s))
			setLocalValue(s, val);
		else
			setGlobalValue(s, val);
	}
	
	public void setLocalValue(String s, double val) {
		localScopes.get(localScopes.size()-1);
	}
	
	public void setGlobalValue(String s, double val) {
		updated = true;
		globals.put(s, val);
	}
	
	public void enterLocalScope() {
		localScopes.add(new HashMap<>());
	}
	
	public void leaveLocalScope() {
		localScopes.remove(localScopes.size()-1);
	}
	
	public void addListener(VariableListener listener) {
		listeners.add(listener);
	}
	
	public void notifyListeners() {
		if(updated) {
			updated = false;
			for(VariableListener listener : listeners) {
				listener.changedMap(Collections.unmodifiableMap(globals));
			}
		}
	}
	
	private Map<String, Double> getLocalScope(){
		return localScopes.get(localScopes.size()-1);
	}
}
