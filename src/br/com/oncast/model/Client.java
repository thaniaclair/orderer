package br.com.oncast.model;

import java.util.LinkedList;
import java.util.List;

import br.com.oncast.controller.OrderingService;
import br.com.oncast.exception.OrderingException;
import br.com.oncast.helper.BookParser;
import br.com.oncast.helper.Logger;
import br.com.oncast.helper.RuleParser;

/**
 * This class represents a client that needs to order a list of books by some
 * rules.
 * 
 * @author thania
 *
 */
public class Client 
{
	
	/**
	 * A list of books.
	 */
	private List<Book> books = new LinkedList<Book>();
	
	/**
	 * A list of order rules.
	 */
	private List<Rule> rules = new LinkedList<Rule>();
	
	/**
	 * Send the request to the Ordering Service. 
	 */
	public void sendRequest() 
	{		
		// Get the rules list from the configuration file.
		this.rules = RuleParser.getRulesFromFile();
		// If the client doesn't have any order rules, it's not needed to talk to
		// the Ordering Service.
		if (this.rules.size() == 0) 
		{
			return;
		}
		// Get the books list from the client files.
		this.books = BookParser.getBooksFromFile();
		try 
		{
			// Ask for the ordering service to order a list of books, according
			// to the list of rules.
			OrderingService.getInstance().order(this.books, this.rules);
		} 
		catch (OrderingException e) 
		{
			Logger.logError(e);
		}
		// Set the ordered list of books (that the client received from the 
		// Ordering Service) in the client output file. 
		BookParser.setBooksOutputFile(this.books);
	}

}