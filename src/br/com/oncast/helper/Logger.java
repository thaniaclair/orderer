package br.com.oncast.helper;

/**
 * This class helps to log the events happening in this program. Another library
 * wasn't used (such as log4j) here because is too much for this use case.
 * 
 * @author thania
 *
 */
public class Logger 
{

	/**
	 * Prints the error message.
	 * 
	 * @param message The message that helps the user to figure out what's
	 * going on.
	 */
	public static void logError(String message) 
	{		
		System.out.println("[ERROR] " + message);
	}
	
	/**
	 * Prints the error message.
	 * If it's development mode, then prints the stack trace too.
	 * 
	 * @param message The message.
	 * @param e The exception.
	 */
	public static void logError(String message, Exception e) 
	{		
		logError(message);
		if (("true").equals(ConfigProperties.getString("devMode"))) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints the error message.
	 * If it's development mode, then prints the stack trace too.
	 * 
	 * @param message The message.
	 * @param e The exception.
	 */
	public static void logError(Exception e) 
	{		
		logError(e.getMessage());
		if (("true").equals(ConfigProperties.getString("devMode"))) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * A simple log. For this case, it just prints in the console, but it could
	 * be in another interface.
	 * 
	 * @param message The message.
	 */
	public static void log(String message) 
	{		
		System.out.println(message);
	}
	
}
