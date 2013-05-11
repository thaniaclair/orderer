package br.com.oncast.controller;

import java.util.List;

import br.com.oncast.exception.OrderingException;
import br.com.oncast.helper.Constants;
import br.com.oncast.helper.Logger;
import br.com.oncast.model.Book;
import br.com.oncast.model.Rule;

/**
 * The client deals with this class to get a ordered books list. The client just
 * passes a list of book and a list of rules and this class is in charge of 
 * ordering the books list according to the rules.  
 * 
 * @author thania
 *
 */
public class OrderingService 
{

	/**
	 * The only instance of this class to be handled.
	 */
	private static final OrderingService service = new OrderingService();

	/** 
	 * Private constructor to prevent instantiation from other classes.
	 */
	private OrderingService() 
	{
		// Nothing to do.
	}

	/**
	 * Get the instance of this class, that is singleton.
	 * 
	 * @return
	 */
	public static OrderingService getInstance() 
	{
		return service;
	}
	
	/**
	 * This method is the key to order a list of books. The client just needs to
	 * pass as arguments: a list of books and a list of rules (to order that books). 
	 * It will modify the list of books in the order required.
	 *
	 * @param books The list of books that will be ordered.
	 * @param rules The list of rules that the client wants to order the books. 
	 * @throws OrderingException If no rules are supplied.
	 */
	public void order(List<Book> books, List<Rule> rules) throws OrderingException 
	{
		// If the user forgot to set the rule, throw a specific exception to warn.
		if (rules == null) 
		{
			throw new OrderingException("No order rules were supplied. Please, " +
					"check the configuration file: [config.properties] in the " +
					"classpath.");
		}
		// If the user forgot to set the books, throw a specific exception to warn.
		if ((books == null) || (books.size() == 0)) 
		{
			throw new OrderingException("No books were supplied. Please, " +
					"check the client input file: [book_in.txt], in the " +
					"directory: [client_files], inside the project.");
		}
		// Order the books.
		BookOrderer bookOrderer = this.getBookOrderer(rules);
		bookOrderer.order(books);	
		Logger.log("Books were ordered successfully!");
	}
	
	/**
	 * This method works like factory method pattern because provides an abstraction 
	 * to decide which orderer class should be instantiated based on the rules 
	 * given as parameter.
	 * Besides that, this method allows additional behaviors to be added because
	 * many times there are more than one order rule. In order to do that,
	 * multiple orderer decorators are stacked on top of each other, each time 
	 * adding a different way to order the list of books. This is how the
	 * decorator pattern works.
	 * 
	 * @param rules The list of rules that the client wants to order the books. 
	 * @return The right orderer according to the rules passed.
	 */
	private BookOrderer getBookOrderer(List<Rule> rules) 
	{
		BookOrderer bookOrderer = new SimpleOrderer();
		for (Rule rule : rules) 
		{
			// Rule = order by TITLE ASCENDING.
			if (Constants.ASC.equals(rule.getDirection()) && 
				Constants.TITLE.equals(rule.getAttribute())) 
			{
				bookOrderer = new TitleAscOrderer(bookOrderer);
			} 
			// Rule = order by TITLE DESCENDING.
			else if (Constants.DESC.equals(rule.getDirection()) && 
					Constants.TITLE.equals(rule.getAttribute())) 
			{
				bookOrderer = new TitleDescOrderer(bookOrderer);
			}

			// Rule = order by AUTHOR ASCENDING.
			if (Constants.ASC.equals(rule.getDirection()) && 
				Constants.AUTHOR.equals(rule.getAttribute())) 
			{
				bookOrderer = new AuthorAscOrderer(bookOrderer);
			} 
			// Rule = order by AUTHOR DESCENDING.
			else if (Constants.DESC.equals(rule.getDirection()) && 
					Constants.AUTHOR.equals(rule.getAttribute())) 
			{
				bookOrderer = new AuthorDescOrderer(bookOrderer);
			}

			// Rule = order by EDITION ASCENDING.
			if (Constants.ASC.equals(rule.getDirection()) && 
				Constants.EDITION.equals(rule.getAttribute())) 
			{
				bookOrderer = new EditionAscOrderer(bookOrderer);
			} 
			// Rule = order by EDITION DESCENDING.
			else if (Constants.DESC.equals(rule.getDirection()) && 
					Constants.EDITION.equals(rule.getAttribute())) 
			{
				bookOrderer = new EditionDescOrderer(bookOrderer);
			}
		}
		// Returns a stack of orderer decorators.
		return bookOrderer;
	}

}
