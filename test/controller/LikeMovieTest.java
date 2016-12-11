package controller;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.LikeMovieAPI;
import model.Movie;
import model.Rate;
import model.User;

public class LikeMovieTest {

	private LikeMovieAPI likeMovieAPI;

	@Before
	public void setup() {
		likeMovieAPI = new LikeMovie("./Data/test.xml");
	}

	/**
	 * Test that a new movie was created
	 */
	@Test
	public void testAddAndGetMovie() {
		likeMovieAPI.addMovie("The Room", "2000", "www.theroom.com");
		assertTrue(likeMovieAPI.getMovie(1).equals(new Movie(1, "The Room", "www.theroom.com", "2000")));
	}

	/**
	 * Test that a new user was added then removed.
	 */
	@Test
	public void testAddAndRemoveUser() {
		likeMovieAPI.addUser("Joe", "Bloggs", 18, 'M', "Student");
		assertTrue(likeMovieAPI.getUser(1).equals(new User(1, "Joe", "Bloggs", "Student", 18, 'M')));
		likeMovieAPI.removeUser(1);
		assertTrue(likeMovieAPI.getUser(1) == null);
	}

	/**
	 * Test that a rate cannot be out of bounds
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddRateException() {
		likeMovieAPI.addRate(1, 1, -6);
	}

	/**
	 * Test that a valid rate can be added to both a new movie and an existing
	 * movie. Test that the top ten method returns the highest to lowest films
	 */
	@Test
	public void testAddRate() {

		// add dummy data
		for (int x = 1; x <= 10; x++) {
			likeMovieAPI.addUser("Joe", "Bloggs", 22, 'M', "Student");
			likeMovieAPI.addMovie("film " + x, "2002", "www.website.com");
		}

		// rating
		int r = -5;

		// for x = 1 < - > 10 inclusive and a rating of r = -5 < - > 5
		for (int x = 1; x <= 10; x++, r++)
			likeMovieAPI.addRate(x, x, r);

		// add a new rating to an existing movie rating
		likeMovieAPI.addRate(10, 10, 4);

		// get the top ten movies
		List<Rate> topTen = likeMovieAPI.getTopTenMovies();

		// set the rating to -5
		r = -5;

		// for x = 9 < - > 0
		// if x == 0 then the rating should be equal to 8
		// else the rating should be equal to r
		for (int x = 9; x >= 0; x--, r++)
			if (x == 0)
				assertTrue(topTen.get(x).getRate().equals(8));
			else
				assertTrue(topTen.get(x).getRate().equals(r));

	}

	/**
	 * Test the get movie details : String
	 */
	@Test
	public void testGetMovieDetails() {
		likeMovieAPI.addMovie("The Room", "2000", "www.theroom.com");
		String notPrimeMovie = likeMovieAPI.getMovieDetails(1);
		String newMovie = new Movie(1, "The Room", "www.theroom.com", "2000").toString();
		assertTrue(notPrimeMovie.equals(newMovie));
	}

	/**
	 * Test the get user ratings.
	 */
	@Test
	public void testGetUserRatings() {

		likeMovieAPI.addUser("Joe", "Bloggs", 18, 'M', "Student");
		likeMovieAPI.addMovie("The Room", "2000", "www.theroom.com");
		likeMovieAPI.addRate(1, 1, 5);

		assertTrue(likeMovieAPI.getUserRatings(1).size() == 1 && likeMovieAPI.getUserRatings(1).get(1).equals(5));
	}

	/**
	 * Test the get user recommendations
	 */
	@Test
	public void testGetUserRecommendations() {
		addValues();

		List<Movie> movieRecommendations = likeMovieAPI.getUserRecommendations(1);

		assertTrue(movieRecommendations.size() == 2);
		assertTrue(movieRecommendations.get(0).equals(likeMovieAPI.getMovie(4)));
		assertTrue(movieRecommendations.get(1).equals(likeMovieAPI.getMovie(6)));
	}

	/**
	 * Test the input and output by creating a second like movie object,<br>
	 * Then writing the objects from the first like movie object to an xml file.<br>
	 * Then in the second like movie object read in the file previously written.<br>
	 * Then compare the user, ratings, movies, userIDs, and movieIDs match.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testIO() throws ClassNotFoundException, IOException {
		addValues();
		LikeMovieAPI one = new LikeMovie("./Data/test.xml");
		likeMovieAPI.write();
		one.load();

		assertTrue(one.getMovieId().equals(likeMovieAPI.getMovieId()));
		assertTrue(one.getUserId().equals(likeMovieAPI.getUserId()));

		for (Integer i = 1; i < likeMovieAPI.getUserId(); i++)
			assertTrue(likeMovieAPI.getUser(i).equals(one.getUser(i)));

		for (Integer i = 1; i < likeMovieAPI.getMovieId(); i++)
			assertTrue(likeMovieAPI.getMovie(i).equals(one.getMovie(i)));

	}

	/**
	 * Assign values to the main like movie objects' users, movies, and ratings
	 */
	private void addValues() {
		likeMovieAPI.addUser("Paddy", "Paddy", 19, 'M', "Student");
		likeMovieAPI.addUser("Clodagh", "Clodagh", 20, 'F', "Student");
		likeMovieAPI.addUser("Paddy", "Paddy", 21, 'M', "Student");
		likeMovieAPI.addUser("Ronan", "Ronan", 22, 'M', "Student");

		for (int x = 0; x < 6; x++)
			likeMovieAPI.addMovie("Film " + x, "200" + x, "www.website.com");

		likeMovieAPI.addRate(1, 1, 5);
		likeMovieAPI.addRate(1, 2, 5);
		likeMovieAPI.addRate(1, 3, -5);

		likeMovieAPI.addRate(2, 1, 1);
		likeMovieAPI.addRate(2, 2, 5);
		likeMovieAPI.addRate(2, 3, -3);
		likeMovieAPI.addRate(2, 4, 5);

		likeMovieAPI.addRate(3, 1, 5);
		likeMovieAPI.addRate(3, 2, -3);
		likeMovieAPI.addRate(3, 3, 5);
		likeMovieAPI.addRate(3, 5, 5);

		likeMovieAPI.addRate(4, 1, 1);
		likeMovieAPI.addRate(4, 2, 3);
		likeMovieAPI.addRate(4, 3, 0);
		likeMovieAPI.addRate(4, 6, 5);
	}
	
	/**
	 * Remove the test file.
	 */
	@After
	public void tearDown() {
		File f= new File("./Data/test.xml");
		if (f.exists())
			f.delete();
			
	}
}
