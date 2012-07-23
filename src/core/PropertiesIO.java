package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesIO {
	
	
	/**
	 * Available key to use:
	 * user - gives the guthub username
	 * access_token - gives the token that is used to get private gists
	 */
	
	private final String FILENAME = "config.properties";
	private Properties prop;
	
	public PropertiesIO(){
		prop = new Properties();
	}
	
	public String getProperty(String key){
		try {
			prop.load(new FileInputStream(FILENAME));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop.getProperty(key);
	}
	
	public void setProperty(String key, String value){
		prop.setProperty(key,value);
		try {
			prop.store(new FileOutputStream(FILENAME), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
