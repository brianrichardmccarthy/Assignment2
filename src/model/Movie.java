package model;

import com.google.common.base.Objects;

import fileLogger.ToJsonString;

/**
 * Movie Class for LikeMovie.
 * @author Brian
 *
 */
public class Movie {

	private final Integer id;
	private String title;
	private String url;
	private String year;

	/**
	 * Constructor for Movie class
	 * @param id (Integer)
	 * @param title (String)
	 * @param url (String)
	 * @param year (String)
	 */
	public Movie(Integer id, String title, String url, String year) {
		setUrl(url);
		setTitle(title);
		setYear(year);
		this.id = id;
	}

	/**
	 * Accessor for url
	 * @return (String)
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Mutator for url
	 * @param url (String)
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Accessor for title of movie
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Mutator for title of movie
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Accessor for year of release.
	 * @return (String)
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Mutator for year of release.
	 * @param year (String)
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Accessor for movie id.
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Movie) {
			final Movie that = (Movie) obj;
			
			return Objects.equal(this.id, that.id) 
					&& Objects.equal(this.title, that.title)
					&& Objects.equal(this.url, that.url)
					&& Objects.equal(this.year, that.year);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return new ToJsonString(getClass(), this).toString();
	}

	
}
