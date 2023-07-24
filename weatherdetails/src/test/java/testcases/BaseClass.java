package testcases;

import utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	public String baseURI = readConfig.getBaseURI();
	public String paramURI_WLG = readConfig.getParamURIWLG();
	public String paramURI_AKL = readConfig.getParamURIAKL();
	
	public void setUp() {
		//Initial Setup
		//File Clearance
		//Memory Clearance
	}
	
	public void tearDown() {
		//Initial Setup
		//File Clearance
		//Memory Clearance
		
	}
}
