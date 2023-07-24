package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;
	
	public ReadConfig() {
		File src = new File("./config/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		}
		catch(Exception e) {
		System.out.println(e.getMessage());
	}
	
	}

	public String getBaseURI() {
		String baseURI = prop.getProperty("baseURI");
		return baseURI;
		
	}
	
	public String getParamURIWLG() {
		String paramURI_WLG = prop.getProperty("paramURI_WLG");
		return paramURI_WLG;
		
	}
	
	public String getParamURIAKL() {
		String paramURI_AKL = prop.getProperty("paramURI_AKL");
		return paramURI_AKL;
		
	}
	
}
