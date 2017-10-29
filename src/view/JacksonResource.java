package view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonResource implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JacksonResource() {

		final ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in file
		try {
			WorkSpace ws = new WorkSpace();
			ws.setBackgroundIndex(0);
			ws.setPenColorPalette(new HashMap<Double, String>());

			mapper.writeValue(new FileOutputStream("data/output.json"), ws);
			String jsonInString = mapper.writeValueAsString(ws);
			JSONObject obj = new JSONObject(jsonInString); // Convert text to object
			System.out.println(obj.toString(4));
		} catch (JsonGenerationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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

			JSONObject turtles = obj.getJSONObject("turtles"); // Get the element object
			JSONObject userMethods = obj.getJSONObject("userMethods"); // Get duration sub object
			JSONObject variables = obj.getJSONObject("variables"); // Get distance sub object

			System.out.println("turtles: " + turtles); // Print int value
			System.out.println("userMethods: " + userMethods.toString()); // Print int value
			System.out.println("variables: " + variables.toString()); // Print int value

			// WorkSpace parsed = objectMapper.parse(json);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new JacksonResource();

	}

}