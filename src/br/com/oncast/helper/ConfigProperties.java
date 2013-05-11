package br.com.oncast.helper;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class was created in order to get strings of the properties file.
 * 
 * @author thania
 */
public class ConfigProperties {

	/**
	 * A ResourceBundle instance to refer the properties file.
	 */
	private static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("config");

	/**
	 * Gets the string of the ResourceBundle instance.
	 * 
	 * @param key The key for searching the string.
	 * @return The value that was searched in the properties file.
	 * 
	 */
	public static String getString(String key) 
	{
		String value = null;
		try 
		{
			value = resourceBundle.getString(key);
		} 
		catch (MissingResourceException e) 
		{
			Logger.logError("Check if your properties file exists in the classpath.", 
					e);
		}
		return value;
	}
	
}