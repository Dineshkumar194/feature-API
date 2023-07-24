package testcases;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import homejson.JSONProcess;

public class TC02VerifyGetReqAKL extends BaseClass {

	@DataProvider(name = "TestData")
	public Object[][] getUserData(){
		
		Object[][] data = {
				{"-36.8485","174.7635"}
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
		String actualValue = jp.responseHandler(responseJson[1], "timezone");
		Assert.assertTrue("Validate field timezone as GMT", actualValue.contains("GMT"));
		
	}
	
	@Test
	public void verifyGetDetailsWLG2(String latitude, String longitude) throws JsonMappingException, JsonProcessingException {
		
		String paramURI = "?latitude=-36.8485&longitude=174.7635&forecast_days=1&&daily=sunrise&daily=weathercode";
		JSONProcess jp = new JSONProcess();
		String[] responseJson = jp.getMethod(baseURI, paramURI);
		Assert.assertTrue("Validate Response Message containing status message 'OK'", responseJson[0].contains("Bad Request"));
		Assert.assertTrue("Validate Response Message containing status code '200'", responseJson[0].contains("400"));
		String actualValue = jp.responseHandler(responseJson[1], "reason");
		Assert.assertTrue("Negative TC validation", actualValue.contains("Timezone is required"));
		
	}
	
}
