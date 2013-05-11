package br.com.oncast.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import br.com.oncast.model.Book;

/**
 * This class is a client's helper. We need to convert some text file lines to
 * books but this responsibility is too much for the client. So, we get a help
 * from here. This class finds where is the client's input file with unsorted
 * records and convert the data in Book objects.
 * 
 * @author thania
 *
 */
public class BookParser 
{

	/**
	 * The file where is our application.
	 */
	private static File rootFile = new File(".");
	
	/**
	 * This method converts a text file with this syntax: 
	 * [id];[authorName];[title];[editionYear]
	 * to a list of books.
	 * 
	 * @return A list of Book objects.
	 */
	public static List<Book> getBooksFromFile() 
	{
		List<Book> books = new LinkedList<Book>();
	    try 
	    {
	    	String rootFilePath = rootFile.getCanonicalPath();
	    	File bookInFile = new File(rootFilePath + File.separator + 
	    			"client_files" + File.separator + "book_in.txt");
	        BufferedReader in = new BufferedReader(new FileReader(bookInFile));
            
	        String str = null;
            // Read each line from the file.
            while (in.ready()) 
            {
            	// Get the text from the line.
                str = in.readLine();
                // Break the string in parts that are the book's attributes 
                // separated in a specific syntax.
                String[] tokens = str.split(";");
                // Create a book and set the values.
                Book book = new Book();
                if (tokens.length < 4) 
                {
                	Logger.logError("Please, check the syntax to add a book: " +
 	    							"[id];[authorName];[title];[editionYear]");
                }
                book.setId(Integer.parseInt(tokens[0]));
                book.setAuthorName(tokens[1]);
                book.setTitle(tokens[2]);
                book.setEditionYear(Integer.parseInt(tokens[3]));
                // Add in the books list.
                books.add(book);
            }
            in.close();
			Logger.log("Input file was read successfully. For further information, " +
					   "see the file: [book_in.txt]");
	    } 
	    catch (IOException e) 
	    {
	    	Logger.logError("Error reading from file: [book_in.txt]. ", e);
	    }
	    return books;	
	}

	/**
	 * This method creates a output file with a ordered list of books, 
	 * requested by the client. The output file format is the same than the
	 * input file format, see below:
	 * [id];[authorName];[title];[editionYear]
	 * 
	 * @param books The ordered books that will be converted to text records in 
	 * the output file.
	 */
	public static void setBooksOutputFile(List<Book> books) 
	{
		try 
		{
			String rootFilePath = rootFile.getCanonicalPath();
			File bookOutFile = new File(rootFilePath + File.separator
					+ "client_files" + File.separator + "book_out.txt");
			// If the output file exists, then delete it, because is trash.
			if (bookOutFile.exists()) 
			{
				bookOutFile.delete();
			}
			bookOutFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(bookOutFile));
			// For each book, write a line.
			for (Book book : books) 
			{
				// Write the book's details in a specific syntax.
				out.write(book.getId() + ";" + book.getAuthorName()
						 + ";" + book.getTitle()
						 + ";" + book.getEditionYear() + "\n");
			}
			out.close();
			Logger.log("Output file was written successfully. For further " +
					   "information, see the file: [book_out.txt]");
		} 
		catch (IOException e) 
		{
	    	Logger.logError("Error writing file: [book_out.txt].", e);
		}

	}
	
}
