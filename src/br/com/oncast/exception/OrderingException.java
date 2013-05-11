package br.com.oncast.exception;

/**
 * This exception happens when the rules aren't supplied.
 * 
 * @author thania
 *
 */
public class OrderingException extends Exception 
{

	/**
	 * The serial version uid.
	 */
	private static final long serialVersionUID = -7489173998717239940L;
	
	/**
	 * Default constructor.
	 */
	public OrderingException() 
	{
		super();
	}
	
	/**
	 * Pass the error message.
	 * 
	 * @param errorMessage The error message.
	 */
	public OrderingException(String errorMessage) 
	{
		super(errorMessage);
	}
	
}
