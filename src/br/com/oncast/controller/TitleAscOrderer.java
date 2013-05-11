package br.com.oncast.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.oncast.model.Book;

/**
 * This class provides an implementation to order a list of books by the title 
 * ascending.
 * 
 * @author thania
 *
 */
public class TitleAscOrderer extends BookOrdererDecorator 
{

	/**
	 * Receives another decorator to order the books list in a different way
	 * at first. 
	 * 
	 * @param bookOrderer A book orderer that is a decorator too.
	 */	
	public TitleAscOrderer(BookOrderer bookOrderer) 
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
        // Order the list by title ascending.
        Collections.sort(orderedBooks, new TitleAscComparator());
        this.books = orderedBooks;
	}

	/**
	 * This comparator is used to order a list of books by the title ascending.
	 * 
	 * @author thania
	 *
	 */
	private class TitleAscComparator implements Comparator<Book>
	{
		/* 
		 * (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Book book1, Book book2)
		{
			String title1 = book1.getTitle();
		    String title2 = book2.getTitle();
			return title1.compareTo(title2);
		}
	}

}
