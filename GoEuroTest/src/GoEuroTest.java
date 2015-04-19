import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GoEuroTest {
	
	public static void main(String[] input) {
		try {
			//Read jsonArray from URL
			JSONArray jsonArray = readJsonFromUrl("http://api.goeuro.com/api/v2/position/suggest/en/"+input[0]);
			//Get jsonObject from Json array
			ArrayList<JSONObject> jsonObjects = getJsonObjects(jsonArray);
			//Get CSVBeanData
			ArrayList<CSVBeanData> CSVBeansData =  getCSVBeansData(jsonObjects);
			//Create CSV File
			String fileNameNative = System.getProperty("user.home")+"/"+input[0]+"Native.csv";
			String fileNameSuperCSV = System.getProperty("user.home")+"/"+input[0]+"SuperCSV.csv";
			CSVFileWriter.writeCsvFile(fileNameNative, CSVBeansData);
			SuperCSVFileWriter.writeCsvFile(fileNameSuperCSV, CSVBeansData);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}

	public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONArray json = new JSONArray(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	}
	
	private static ArrayList<JSONObject> getJsonObjects(JSONArray jsonArray){
		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		for (int i=0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if(jsonObject != null){
				jsonObjects.add(jsonObject);
			}
		}
		return jsonObjects;
	}
	
	private static CSVBeanData fillCSVBeanDataFromJsonObject(JSONObject jsonObject){
		CSVBeanData bean = new CSVBeanData();
		BigDecimal _id = BigDecimal.valueOf(jsonObject.getDouble("_id"));
		String name = (jsonObject.getString("name")!=null) ? jsonObject.getString("name") : "";
		String type = (jsonObject.getString("type")!=null) ? jsonObject.getString("type") : "";
		JSONObject geoPosition = jsonObject.getJSONObject("geo_position");
		BigDecimal latitude = new BigDecimal(0);
		BigDecimal longitude = new BigDecimal(0);
		if(geoPosition != null){
			latitude = BigDecimal.valueOf(geoPosition.getDouble("latitude"));
			longitude = BigDecimal.valueOf(geoPosition.getDouble("longitude"));
		}
		
		bean.set_id(_id);
		bean.setName(name);
		bean.setType(type);
		bean.setLatitude(latitude);
		bean.setLongitude(longitude);
		
		return bean;
	}
	
	private static ArrayList<CSVBeanData> getCSVBeansData(ArrayList<JSONObject> jsonObjects){
		ArrayList<CSVBeanData> CSVBeansData = new ArrayList<CSVBeanData>();
		
		for(JSONObject jo : jsonObjects){
			CSVBeanData temp = fillCSVBeanDataFromJsonObject(jo);
			CSVBeansData.add(temp);
		}
		
		return CSVBeansData;
	}

}
