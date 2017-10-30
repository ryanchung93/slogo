package view;

import java.util.List;
import java.util.Map;

import model.CommandBuilder;
import model.ImmutableTurtle;

public class WorkSpace implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> views;
	private List<String> turtles;

	private Map<Integer, String> imagePalette;
	private Map<Integer, String> colorPalette;

	private String language;
	private int backgroundIndex;

	private Map<String, CommandBuilder> userCommands;
	private Map<String, Double> globalVariables;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String lang) {
		this.language = lang;
	}

	public int getBackgroundIndex() {
		return backgroundIndex;
	}

	public void setBackgroundIndex(int index) {
		this.backgroundIndex = index;
	}

	public List<String> getViews() {
		return views;
	}

	public void setViews(List<String> views) {
		this.views = views;
	}

	public Map<Integer, String> getColorPalette() {
		return colorPalette;
	}

	public void setColorPalette(Map<Integer, String> colorPalette) {
		this.colorPalette = colorPalette;
	}

	public Map<Integer, String> getImagePalette() {
		return imagePalette;
	}

	public void setImagePalette(Map<Integer, String> imagePalette) {
		this.imagePalette = imagePalette;
	}

	public List<String> getTurtles() {
		return turtles;
	}

	public void setTurtles(List<String> turtles) {
		this.turtles = turtles;
	}

	public Map<String, Double> getVariables() {
		return globalVariables;
	}

	public void setVariables(Map<String, Double> variables) {
		this.globalVariables = variables;
	}

	public Map<String, CommandBuilder> getUserMethods() {
		return userCommands;
	}

	public void setUserMethods(Map<String, CommandBuilder> userMethods) {
		this.userCommands = userMethods;
	}
}