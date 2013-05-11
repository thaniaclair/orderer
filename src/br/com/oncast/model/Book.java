package br.com.oncast.model;

/**
 * This class represents a book. An instance of this class will be used to order
 * some book records passed by the client's input file.
 * 
 * @author thania
 * 
 */
public class Book 
{

	/**
	 * The book's identification.
	 */
	private int id;
	
	/**
	 * The author's name that wrote the book.
	 */
	private String authorName;
	
	/**
	 * The book's title.
	 */
	private String title;
	
	/**
	 * The year which the book was published.
	 */
	private int editionYear;

	/**
	 * Return the book's identification.
	 * 
	 * @return The id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the book's identification.
	 * 
	 * @param id The id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the book's title.
	 * 
	 * @return The title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the book's title.
	 * 
	 * @param title The title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Return the author name.
	 * 
	 * @return The authorName.
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Set the author name.
	 * 
	 * @param authorName The author name.
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Return the book's edition year.
	 * 
	 * @return The edition year.
	 */
	public int getEditionYear() {
		return editionYear;
	}

	/**
	 * Set the book's edition year.
	 * 
	 * @param editionYear The edition year.
	 */
	public void setEditionYear(int editionYear) {
		this.editionYear = editionYear;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + editionYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (editionYear != other.editionYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
