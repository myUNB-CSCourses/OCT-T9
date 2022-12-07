package project.team9;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFileReader {
	
	public String configRead(String key) {
		Properties prop=new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("src/config.properties");
			prop.load(ip);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
}
