package br.com.oncast.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.oncast.model.Book;

/**
 * This class provides an implementation to order a list of books by the book's 
 * edition year descending.
 * 
 * @author thania
 *
 */
public class EditionDescOrderer extends BookOrdererDecorator 
{

	/**
	 * Receives another decorator to order the books list in a different way
	 * at first. 
	 * 
	 * @param bookOrderer A book orderer that is a decorator too.
	 */
	public EditionDescOrderer(BookOrderer bookOrderer) 
	{
		super(bookOrderer);
	}

	/* 
	 * (non-Javadoc)
	 * @see br.com.oncast.control.BookOrderer#order(java.util.List)
	 */
	@Override
	public void order(List<Book> books) 
	{
		// Order the list in the way of the decorator stacked on top.
		this.bookOrderer.order(books);
		List<Book> orderedBooks = this.bookOrderer.getBooks();
        // Order the list by edition year descending.
        Collections.sort(orderedBooks, new EditionDescComparator());
        this.books = orderedBooks;
	}

	/**
	 * This comparator is used to order a list of books by the book's edition 
	 * year descending.
	 * 
	 * @author thania
	 *
	 */
	private class EditionDescComparator implements Comparator<Book>
	{
		/* 
		 * (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Book book1, Book book2)
		{
			int edition1 = book1.getEditionYear();
		    int edition2 = book2.getEditionYear();
			return edition2 - edition1;
		}
	}
	
}
