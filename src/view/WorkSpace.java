package view;

import java.util.List;
import java.util.Map;

import model.CommandBuilder;
import model.ImmutableTurtle;

public class WorkSpace implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> views;
	private List<ImmutableTurtle> turtles;

	private Map<Integer, String> penColorPalette;
	private Map<Integer, String> imagePalette;
	private Map<Integer, String> backgroundColorPalette;

	private String languageIndex;
	private double backgroundIndex;

	private Map<String, CommandBuilder> userCommands;
	private Map<String, Double> globalVariables;

	public String getLanguage() {
		return languageIndex;
	}

	public void setLanguageIndex(String index) {
		this.languageIndex = index;
	}

	public double getBackgroundIndex() {
		return backgroundIndex;
	}

	public void setBackgroundIndex(double index) {
		this.backgroundIndex = index;
	}

	public List<String> getViews() {
		return views;
	}

	public void setViews(List<String> views) {
		this.views = views;
	}

	public Map<Integer, String> getPenColorPalette() {
		return penColorPalette;
	}

	public void setPenColorPalette(Map<Integer, String> colorPalette) {
		this.penColorPalette = colorPalette;
	}

	public Map<Integer, String> getBackgroundColorPalette() {
		return backgroundColorPalette;
	}

	public void setBackgroundColorPalette(Map<Integer, String> colorPalette) {
		this.backgroundColorPalette = colorPalette;
	}

	public Map<Integer, String> getImagePalette() {
		return imagePalette;
	}

	public void setImagePalette(Map<Integer, String> imagePalette) {
		this.imagePalette = imagePalette;
	}

	public List<ImmutableTurtle> getTurtles() {
		return turtles;
	}

	public void setTurtles(List<ImmutableTurtle> turtles) {
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