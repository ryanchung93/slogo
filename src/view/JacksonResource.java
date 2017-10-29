package view;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonResource implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JacksonResource() {

		final ObjectMapper mapper = new ObjectMapper();
		Map<Integer,String> colorPallete = new HashMap<Integer, String>();
		colorPallete.put(1, "RED");
		colorPallete.put(2, "WHITE");
		
		// Object to JSON in file
		try {
			WorkSpace ws = new WorkSpace();
			ws.setBackgroundIndex(0);
			
			ws.setPenColorPalette(colorPallete);

			mapper.writeValue(new FileOutputStream("data/output.json"), ws);
			String jsonInString = mapper.writeValueAsString(ws);
			JSONObject obj = new JSONObject(jsonInString); // Convert text to object
			System.out.println(obj.toString(4));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		File file = new File("data/output.json");

		try {
			WorkSpace newWS = objectMapper.readValue(file, WorkSpace.class);
			String json = objectMapper.writeValueAsString(newWS);
			JSONObject obj = new JSONObject(json); // Convert text to object
			System.out.println(obj.toString(4));

			JSONObject colorPal = obj.getJSONObject("penColorPalette"); // Get the element object
			Iterator<String> nameItr = colorPal.keys();
			Map<String, String> outMap = new HashMap<String, String>();
			while(nameItr.hasNext()) {
			    String name = nameItr.next();
			    outMap.put(name, colorPal.getString(name));
			}
			for (String key : outMap.keySet()) {
				System.out.println(key + " " + outMap.get(key));
			}
			
//			JSONObject userMethods = obj.getJSONObject("userMethods"); // Get duration sub object
//			JSONObject variables = obj.getJSONObject("variables"); // Get distance sub object
//
//			System.out.println("turtles: " + turtles); // Print int value
//			System.out.println("userMethods: " + userMethods.toString()); // Print int value
//			System.out.println("variables: " + variables.toString()); // Print int value
//
//			// WorkSpace parsed = objectMapper.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
//
	}

	public static void main(String[] args) {
		new JacksonResource();

	}

}