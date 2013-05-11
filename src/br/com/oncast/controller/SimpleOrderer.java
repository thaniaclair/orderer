package br.com.oncast.controller;

import java.util.LinkedList;
import java.util.List;

import br.com.oncast.model.Book;

/**
 * This class provides a default implementation to the BookOrderer interface.
 * 
 * @author thania
 *
 */
public class SimpleOrderer implements BookOrderer 
{

	/**
	 * A list of books.
	 */
	protected List<Book> books = new LinkedList<Book>();

	/* 
	 * (non-Javadoc)
	 * @see br.com.oncast.control.BookOrderer#order(java.util.List)
	 */
	@Override
	public void order(List<Book> books) 
	{
		// It's not needed to order here.
		this.books = books;
	}

	/* 
	 * (non-Javadoc)
	 * @see br.com.oncast.control.BookOrderer#getBooks()
	 */
	@Override
	public List<Book> getBooks() 
	{
		return this.books;
	}

}
