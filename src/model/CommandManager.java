package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import view.API.StringListener;

public class CommandManager {
	public static final String PATH_START = "resources.languages.";
	public static final String DEFAULT_LANGUAGE = "English";
	
	private String builderPropertiesPath;
	
	private Map<String, CommandDef> commands = new HashMap<>();
	private List<StringListener> listeners = new ArrayList<>();
	
	public CommandManager(String builderPropertiesPath){
		this.builderPropertiesPath = builderPropertiesPath;
		setLanguage(DEFAULT_LANGUAGE);
	}

	private void loadCommands(String propertyFile, String language) {
		ResourceBundle classFile = ResourceBundle.getBundle(propertyFile);
		ResourceBundle acceptedCommands = ResourceBundle.getBundle(PATH_START+language);
		Enumeration<String> keys = classFile.getKeys();
		while(keys.hasMoreElements()) {
			String command = keys.nextElement();
			
			CommandDef definition;
			try {
				definition = (CommandDef) Class.forName(classFile.getString(command)).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Check basicCommands.properties -- key: " + command);
			}
			
			commands.put(acceptedCommands.getString(command), definition);
		}
	}
	
	public void setLanguage(String language) {
		commands.clear();
		loadCommands(builderPropertiesPath, language);
		updateListeners();
	}
	
	public void clear() {
		commands.clear();
		updateListeners();
	}

	public CommandDef get(String s) {
		for(String regex : commands.keySet()) {
			if(s.matches(regex))
				return commands.get(regex);
		}
		throw new RuntimeException("Command '" + s + "' not found!");
	}
	
	public void addListener(StringListener commandListener) {
		listeners.add(commandListener);
	}
	
	public void put(String regex, CommandDef definition) {
		commands.put(regex, definition);
		updateListeners();
	}

	private void updateListeners() {
		for(StringListener listener : listeners)
			listener.changedMap(commands);
	}
}
