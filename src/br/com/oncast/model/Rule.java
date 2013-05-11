package br.com.oncast.model;

/**
 * This class represents the way to order a list of books. A order rule consists 
 * on a book's attribute (it could be: title, author or edition year) and a book's 
 * direction (it could be: ascending or descending). 
 * 
 * @author thania
 *
 */
public class Rule 
{
	
	/**
	 * The book's attribute to be ordered.
	 */
	private String attribute;
	
	/**
	 * The direction which the attribute will be ordered.
	 */
	private String direction;

	/**
	 * Return the book's attribute.
	 * 
	 * @return The attribute.
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * Set the book's attribute.
	 * 
	 * @param attribute The attribute.
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * Return the direction. 
	 * 
	 * @return The direction.
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Set the direction.
	 * 
	 * @param direction The direction.
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}