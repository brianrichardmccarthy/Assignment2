package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest {

	/**
	 * Test the movie Constructor.
	 */
	@Test
	public void testConstructor() {
		Movie movie = null;
		movie = new Movie(1, "The Room", "1994", "www.theroom.com");
		assertTrue(movie != null);
	}
	
	/**
	 * Test The Accessors and mutators for the Movie class.
	 */
	@Test
	public void testGettersAndSetters() {
		Movie movie = new Movie(1, "The Room", "1994", "www.theroom.com");

		movie.setTitle("Lord of the Rings");
		movie.setYear("2002");
		movie.setUrl("www.lotr.com");
		
		assertTrue(movie.getId() == 1);
		assertTrue(movie.getTitle().equals("Lord of the Rings"));
		assertTrue(movie.getYear().equals("2002"));
		assertTrue(movie.getUrl().equals("www.lotr.com"));
	}
	
	/**
	 * Test the equals method in the Movie Class
	 */
	@Test
	public void testEquals() {
		Movie movie1 = new Movie(1, "The Room", "1994", "www.theroom.com");
		Movie movie2 = new Movie(1, "Lord of the Rings", "2002", "www.lotr.com");
		assertTrue(movie1.equals(movie1) && !movie1.equals(movie2));
	}

}
