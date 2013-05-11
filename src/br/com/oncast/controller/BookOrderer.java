package br.com.oncast.controller;

import java.util.List;

import br.com.oncast.model.Book;

/**
 * This interface provides a contract for the orderer concrete classes that
 * must have a method to order and another to acquire the ordered books list.  
 * 
 * @author thania
 *
 */
public interface BookOrderer 
{

	/**
	 * Order the list of books.
	 * 
	 * @param books The list of books to be ordered.
	 */
	public void order(List<Book> books);

	/**
	 * Get a list of books.
	 * 
	 * @return A list of books.
	 */
	public List<Book> getBooks();
}
