package br.com.oncast.controller;

import java.util.LinkedList;
import java.util.List;

import br.com.oncast.model.Book;

/**
 * This abstract class is the key to extend the functionality of a simple orderer 
 * of books. By aggregating an instance of the same class that it implements, it's
 * possible to do concrete multiple decorators stack on top of each other, each 
 * time adding a new way to order the book, according to the order rules. 
 * In this way, the ordering service will become scalable and won't be needed to 
 * predict what combinations of orderer extensions could be used at design time.    
 * 
 * @author thania
 *
 */
public abstract class BookOrdererDecorator implements BookOrderer 
{

	/**
	 * A list of books to be ordered.
	 */
	protected List<Book> books = new LinkedList<Book>();
	
    /**
     * A different book orderer that will extend the functionality of this orderer.
     */
    protected BookOrderer bookOrderer;
    
    /**
	 * Receives an orderer in an effort to order the books list in a different 
	 * way at first. 
     * 
     * @param bookOrderer
     */
    public BookOrdererDecorator(BookOrderer bookOrderer) 
    {
        this.bookOrderer = bookOrderer;
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
