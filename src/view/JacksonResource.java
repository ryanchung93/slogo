package view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonResource implements java.io.Serializable {
	/**
	 * 
	 */
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	WorkSpace ws;
	private List<String> myImageNameList;
	private List<String> myColorList;
	
	private static final long serialVersionUID = 1L;

	public JacksonResource() {

		
		
//		writeToFile();
//		readFromFile();

	}
	
	private void makeNewDefaultFile() {
		ws = new WorkSpace();
		ws.setLanguage(myResources.getString("DefaultLanguage"));
		ws.setBackgroundIndex(Integer.parseInt(myResources.getString("DefaultBackgroundIndex")));
		ws.setColorPalette(makeMap(myColorList));
		ws.setImagePalette(makeMap(myImageNameList));
		ws.setViews(new ArrayList<String>(Arrays.asList(myResources.getString("DefaultViews").split(","))));
		ws.setTurtles(new ArrayList<String>(Arrays.asList(myResources.getString("DefaultTurtles").split(","))));
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			File file = new File("data/default.json");
			mapper.writeValue(new FileOutputStream("data/output.json"), ws);
			
//			getFilePaths();
//			updateWorkspace();
		} catch (Exception e) {
			new ErrorWindow(e.getMessage());
		}
		
	}

	private Map<Integer, String> makeMap(List<String> list) {
		Map<Integer, String> map = new HashMap<>();
		for (String s : list) {
			map.put(list.indexOf(s), s);
		}
		return map;
	}
	private void writeToFile() {
		final ObjectMapper mapper = new ObjectMapper();
		Map<Integer, String> colorPallete = new HashMap<Integer, String>();
		colorPallete.put(1, "RED");
		colorPallete.put(2, "WHITE");

		// Object to JSON in file
		try {
			WorkSpace ws = new WorkSpace();
			ws.setBackgroundIndex(0);
			ws.setColorPalette(colorPallete);

			mapper.writeValue(new FileOutputStream("data/output.json"), ws);
			
//			String jsonInString = mapper.writeValueAsString(ws);
//			JSONObject obj = new JSONObject(jsonInString); // Convert text to object
//			System.out.println("Write to file: " + obj.toString(4));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readFromFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		File file = new File("data/output.json");

		try {
			// Convert JSON to Java object
			WorkSpace newWS = objectMapper.readValue(file, WorkSpace.class);
			String json = objectMapper.writeValueAsString(newWS);
			JSONObject obj = new JSONObject(json); // Convert text to object
			System.out.println("Read from file: \n" + obj.toString(4));

			// Extract a map
			JSONObject colorPal = obj.getJSONObject("penColorPalette"); // Get the element object
			Iterator<String> nameItr = colorPal.keys();
			Map<String, String> outMap = new HashMap<String, String>();
			while (nameItr.hasNext()) {
				String name = nameItr.next();
				outMap.put(name, colorPal.getString(name));
			}
			// Print out map
			for (String key : outMap.keySet()) {
				System.out.println(key + " " + outMap.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new JacksonResource();

	}

}