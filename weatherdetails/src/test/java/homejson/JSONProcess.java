package homejson;

import java.io.FileInputStream;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JSONProcess {

	public Response resObj;
	public String responseBody;
	public RequestSpecification reqObj;
	public static FileInputStream propFile = null;
	public static Properties prop = null;
	public static int jj=0;
	public String baseURI;
	
	public String[] getMethod(String URI, String paramURI){
		RestAssured.baseURI = URI;
		reqObj = RestAssured.given();
		resObj = reqObj.request(Method.GET,paramURI);
		responseBody = resObj.getBody().asString();
		//int statusCode = resObj.getStatusCode();
		String statusMsg = resObj.getStatusLine();
		String[] responseParam = {statusMsg, responseBody};
		
		//------------------------------------------------------
		return responseParam;
		
	}
	
	
	public String responseHandler(String responseBody, String fieldName) {
		
		 JSONParser parser = new JSONParser();
		 String actualValue="";
		 
	        try {
	 
	            Object obj = parser.parse(responseBody);
	            JSONObject jo = (org.json.simple.JSONObject)obj;
	            actualValue = nodeTraverser(jo, fieldName);
	            System.out.println(fieldName);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return actualValue;
	    }
	
	public String nodeTraverser(JSONObject jsonObj, String fieldName)
	{
		String actualValue = "";
	    for (Object keyObj : jsonObj.keySet()) {
	        String key = (String)keyObj;
	        Object valObj = jsonObj.get(key);
	        if (valObj instanceof JSONObject) {
	        	nodeTraverser((JSONObject) valObj, fieldName);
	        } else {
	            System.out.println("key : "+key);
	            System.out.println("value : "+valObj.toString());
	            if (key.toString().equalsIgnoreCase(fieldName)) {
	            	actualValue = valObj.toString();
	            	break;
	            }
	        }
	    }
	    return actualValue;
	}
	

}
