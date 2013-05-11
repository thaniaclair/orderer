/**
 * 
 */
package br.com.oncast.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.oncast.controller.OrderingService;
import br.com.oncast.exception.OrderingException;
import br.com.oncast.helper.Constants;
import br.com.oncast.helper.Logger;
import br.com.oncast.model.Book;
import br.com.oncast.model.Rule;

/**
 * A test case for the Ordering Service class. 
 * 
 * @author thania
 *
 */
public class OrderingServiceTC 
{	
	
	/**
	 * A list of book to be ordered in different ways.
	 */
	public static List<Book> books = new LinkedList<Book>();
	
	/**
	 * Log the test start.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpClass() throws Exception 
	{
		Logger.log("<< TEST CASE STARTED >>");
	}	
	
	/**
	 * Clear the books list for each test and add the test data. 
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{		
		books.clear();
		
		// Book 1
		Book book1 = new Book();
		book1.setId(1);
		book1.setTitle("Java How to Program");
		book1.setAuthorName("Deitel & Deitel");
		book1.setEditionYear(2007);
		books.add(book1);

		// Book 2
		Book book2 = new Book();
		book2.setId(2);
		book2.setTitle("Patterns of Enterprise Application Architecture");
		book2.setAuthorName("Martin Fowler");
		book2.setEditionYear(2002);
		books.add(book2);

		// Book 3
		Book book3 = new Book();
		book3.setId(3);
		book3.setTitle("Head First Design Patterns");
		book3.setAuthorName("Elisabeth Freeman");
		book3.setEditionYear(2004);
		books.add(book3);

		// Book 4
		Book book4 = new Book();
		book4.setId(4);
		book4.setTitle("Internet & World Wide Web: How to Program");
		book4.setAuthorName("Deitel & Deitel");
		book4.setEditionYear(2007);
		books.add(book4);	
	}

	/**
	 * Test method for {@link br.com.oncast.controller.OrderingService#order(
	 * java.util.List, java.util.List)}.
	 * 
	 * It checks if the list of books is ordered by title ascending correctly.
	 * 
	 * @throws OrderingException If problems occur to order.
	 */
	@Test
	public void testOrderTitleAsc() throws OrderingException 
	{
		List<Rule> rules = new LinkedList<Rule>();
		
		Rule rule = new Rule();
		rule.setAttribute(Constants.TITLE);
		rule.setDirection(Constants.ASC);
		rules.add(rule);
		
		int expectedResult[] = { 3, 4, 1, 2 };
		
		OrderingService.getInstance().order(books, rules);
		
		int result[] = this.getBooksId();
		
		assertArrayEquals(expectedResult, result);
	}

	/**
	 * Test method for {@link br.com.oncast.controller.OrderingService#order(
	 * java.util.List, java.util.List)}.
	 * 
	 * It checks if the list of books is ordered by author ascending and title 
	 * descending correctly.
	 * 
	 * @throws OrderingException If problems occur to order.
	 */
	@Test
	public void testOrderAuthorAscTitleDesc() throws OrderingException 
	{
		List<Rule> rules = new LinkedList<Rule>();
		
		Rule rule1 = new Rule();
		rule1.setAttribute(Constants.TITLE);
		rule1.setDirection(Constants.DESC);
		rules.add(rule1);		

		Rule rule2 = new Rule();
		rule2.setAttribute(Constants.AUTHOR);
		rule2.setDirection(Constants.ASC);
		rules.add(rule2);
		
		int expectedResult[] = { 1, 4, 3, 2 };
		
		OrderingService.getInstance().order(books, rules);
		
		int result[] = this.getBooksId();
		
		assertArrayEquals(expectedResult, result);
	}

	/**
	 * Test method for {@link br.com.oncast.controller.OrderingService#order(
	 * java.util.List, java.util.List)}.
	 * 
	 * It checks if the list of books is ordered by edition descending, author 
	 * descending and title ascending correctly.
	 * 
	 * @throws OrderingException If problems occur to order.
	 */
	@Test
	public void testOrderEditionDescAuthorDescTitleAsc() throws OrderingException 
	{
		List<Rule> rules = new LinkedList<Rule>();
		
		Rule rule1 = new Rule();
		rule1.setAttribute(Constants.TITLE);
		rule1.setDirection(Constants.ASC);
		rules.add(rule1);		

		Rule rule2 = new Rule();
		rule2.setAttribute(Constants.AUTHOR);
		rule2.setDirection(Constants.DESC);
		rules.add(rule2);

		Rule rule3 = new Rule();
		rule3.setAttribute(Constants.EDITION);
		rule3.setDirection(Constants.DESC);
		rules.add(rule3);
		
		int expectedResult[] = { 4, 1, 3, 2 };
		
		OrderingService.getInstance().order(books, rules);
		
		int result[] = this.getBooksId();
		
		assertArrayEquals(expectedResult, result);
	}

	/**
	 * Test method for {@link br.com.oncast.controller.OrderingService#order(
	 * java.util.List, java.util.List)}.
	 * 
	 * When there is no order rules (null), the application must throw a specific 
	 * exception. This test will check this. 
	 * 
	 * @throws OrderingException If problems occur to order. 
	 */
	@Test(expected=OrderingException.class)
	public void testOrderNullRules() throws OrderingException 
	{
		List<Rule> rules = null;
		OrderingService.getInstance().order(books, rules);
	}

	/**
	 * Test method for {@link br.com.oncast.controller.OrderingService#order(
	 * java.util.List, java.util.List)}.
	 * 
	 * When there is no books (null), the application must throw a specific 
	 * exception. This test will check this. 
	 * 
	 * @throws OrderingException If problems occur to order. 
	 */
	@Test(expected=OrderingException.class)
	public void testOrderNullBooks() throws OrderingException 
	{
		List<Rule> rules = new LinkedList<Rule>();
		
		Rule rule1 = new Rule();
		rule1.setAttribute(Constants.TITLE);
		rule1.setDirection(Constants.ASC);
		rules.add(rule1);	
		
		books = null;
		
		OrderingService.getInstance().order(books, rules);
	}
	
	/**
	 * Get a set of books identification.
	 * 
	 * @return A list of books id.
	 */
	private int[] getBooksId() 
	{
		int[] ids = new int[books.size()];
		int i = 0;
		for (Book book : books) 
		{
			ids[i] = book.getId();
			i++;
		}
		return ids;
	}

	/**
	 * Log the test end.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownClass() throws Exception 
	{
		Logger.log("<< TEST CASE FINISHED >>");
	}
}
