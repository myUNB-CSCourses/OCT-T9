package project.team9;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFileReader {
	
	public String configRead(String key) {
		Properties prop=new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("/Users/dineth/repos/OCT-T9/On-Call-Tracker/src/config.properties");
			prop.load(ip);
			System.out.println(prop.getProperty(key));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "Sdfs";
	}
}
