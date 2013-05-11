package br.com.oncast;

import br.com.oncast.helper.Logger;
import br.com.oncast.model.Client;

/**
 * Class created in order to initialize the application.
 * 
 * @author thania
 *
 */
public class Initializer 
{

	/**
	 * Main method that initializes the application sending a request from 
	 * the client.
	 * 
	 * @param args Arguments aren't needed here.
	 */
	public static void main(String[] args) 
	{
		Logger.log("<< INITIALIZED >>");
		Client client = new Client();
		client.sendRequest();
		Logger.log("<< FINISHED >>");
	}

}