package api;

/**
 * Like Movie API.
 * @author Brian
 * @see LikeMovie
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.Movie;
import model.Rate;
import model.User;

public interface LikeMovieAPI {

	/**
	 * Add a new user.
	 * @param firstName (String) new users' first name.
	 * @param lastName (String) new users' last name.
	 * @param age (Integer) new users' age.
	 * @param gender (char) new users' gender.
	 * @param occupation (String) new users' occupation.
	 */
	public void addUser(String firstName, String lastName, Integer age, char gender, String occupation);

	/**
	 * Remove a user.
	 * @param userID (Integer) users' id.
	 */
	public void removeUser(Integer userID);

	/**
	 * Add a movie rating.
	 * @param userID (Integer) user id who added the rating.
	 * @param movieID (Integer) the movie id being rating.
	 * @param rating (Integer) the rating.
	 */
	public void addRate(Integer userID, Integer movieID, Integer rating);

	/**
	 * Add a Movie
	 * @param title (String) title of the movie.
	 * @param year (String) year of release.
	 * @param url (String) the url to the website.
	 */
	public void addMovie(String title, String year, String url);

	/**
	 * Get movie details (toString).
	 * @param movieID (Integer) movie id.
	 * @return (String) Movie objects' toString (in JSON format).
	 */
	public String getMovieDetails(Integer movieID);

	/**
	 * Get the movie of a given Integer
	 * @param movieID (Integer) id of movie being searched.
	 * @return (Movie) Movie object with matching id.
	 */
	public Movie getMovie(Integer movieID);

	/**
	 * Get a given id for a user, get their ratings for movies.
	 * @param userID (Integer) user id.
	 * @return (Map<Integer MovieID, Integer Rate>)
	 */
	public Map<Integer, Integer> getUserRatings(Integer userID);

	/**
	 * For a given user id, get their recommendations.
	 * @param userID (Integer) user id.
	 * @return (List<Movie>) movies the given user has not rated but have been rated by other users with similar interest.
	 */
	public List<Movie> getUserRecommendations(Integer userID);

	/**
	 * Returns the top ten rated movies. Highest to lowest.
	 * @return (List<Rate>) list of movie ids' and there total ratings in JSON formatting.
	 */
	public List<Rate> getTopTenMovies();

	/**
	 * Read in the xml file, and store the users, movies, ratings in memory.
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @see SerializerAPI
	 */
	public void load() throws ClassNotFoundException, FileNotFoundException, IOException;

	/**
	 * Write the users, movies, and ratings to xml file.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @see SerializerAPI
	 */
	public void write() throws ClassNotFoundException, IOException;

	/**
	 * Get a user object with a given index.
	 * @param userID (Integer) user index.
	 * @return (User)
	 */
	public User getUser(Integer userID);
	
	/**
	 * If the xml file dose not exist, read in the data from the dat files and parse it into memory.
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void prime() throws ClassNotFoundException, FileNotFoundException, IOException;
	
	/**
	 * Returns the next index of the movie count.
	 * @return (Integer)
	 */
	public Integer getMovieId();
	
	/**
	 * Returns the next index of the user count.
	 * @return (Integer)
	 */
	public Integer getUserId();
	
}
