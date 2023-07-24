package testcases;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import homejson.JSONProcess;


public class TC01VerifyGetReqWLG extends BaseClass {
	
	@DataProvider(name = "TestData")
	public Object[][] getUserData(){
		
		Object[][] data = {
				{"-41.2866","174.7756"}
		};
		return data;
	}
	
	@Test(dataProvider="TestData")
	public void verifyGetDetailsWLG(String latitude, String longitude) throws JsonMappingException, JsonProcessingException {
		String paramURI = "?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m";
		JSONProcess jp = new JSONProcess();
		String[] responseJson = jp.getMethod(baseURI, paramURI);
		Assert.assertTrue("Validate Response Message containing status message 'OK'", responseJson[0].contains("OK"));
		Assert.assertTrue("Validate Response Message containing status code '200'", responseJson[0].contains("200"));
		//jp.getResponseValue(responseJson[1]);
		String actualValue = jp.responseHandler(responseJson[1], "timezone");
		Assert.assertTrue("Validate field timezone as GMT", actualValue.contains("GMT"));
		
	}
	

}
