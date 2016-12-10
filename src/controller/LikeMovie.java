package controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import api.LikeMovieAPI;
import api.SerializerAPI;
import edu.princeton.cs.introcs.In;
import fileLogger.Serializer;
import model.Movie;
import model.Rate;
import model.User;

/**
 * Like movie class implements the Like movie interface in the api package.
 * 
 * @author Brian
 * @see LikeMovieAPI
 */
public class LikeMovie implements LikeMovieAPI {

	private Map<Integer, User> users;
	private Map<Integer, Movie> movies;
	private Integer movieId, userId;
	private SerializerAPI serializer;
	private List<Rate> ratings;

	/**
	 * Constructor.
	 */
	public LikeMovie() {
		this.movieId = 1;
		this.userId = 1;
		users = new HashMap<Integer, User>();
		movies = new HashMap<Integer, Movie>();
		serializer = new Serializer();
		ratings = new ArrayList<Rate>();
	}

	/**
	 * Loads users, movies, and ratings from either one xml file or three dat
	 * files.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void prime() throws ClassNotFoundException, FileNotFoundException, IOException {
		if (serializer.isFile())
			serializer.read();
		else {
			init("./data/users.dat");
			init("./data/items.dat");
			init("./data/ratings.dat");
		}
	}

	@Override
	public void addUser(String firstName, String lastName, Integer age, char gender, String occupation) {
		users.put(userId, new User(userId++, firstName, lastName, occupation, age, gender));
	}

	@Override
	public void addMovie(String title, String year, String url) {
		movies.put(movieId, new Movie(movieId++, title, url, year));
	}

	@Override
	public void addRate(Integer userID, Integer movieID, Integer rate) throws IllegalArgumentException {

		// rate can only be between -5 and 5 inclusive.
		if (rate < -5 || rate > 5)
			throw new IllegalArgumentException("Not a valid range! Rate can only be between -5 and 5 inclusive.");

		// add the movie id and rating to the user
		users.get(userID).addMovie(movieID, rate);

		// Sort the ratings by movie id (Natural order).
		Comparator<Rate> comparator = new Comparator<Rate>() {

			@Override
			public int compare(Rate arg0, Rate arg1) {
				return arg0.getMovieID().compareTo(arg1.getMovieID());
			}
		};

		// sort the ratings by comparator.
		Collections.sort(ratings, comparator);

		// check if the movie has already been rated by one or more users.
		int position = Collections.binarySearch(ratings, new Rate(movieID, rate), comparator);

		// if the movie has been rated by others user, then add the score to
		// its' existing rating.
		if (position >= 0)
			ratings.get(position).setRate(rate);
		else
			// else create a new rate class for the movie.
			ratings.add(new Rate(movieID, rate));

	}

	@Override
	public void removeUser(Integer userID) {
		users.remove(userID);
	}

	@Override
	public String getMovieDetails(Integer movieID) {
		return movies.get(movieID).toString();
	}

	@Override
	public Map<Integer, Integer> getUserRatings(Integer userID) {
		return getUser(userID).getMovies();

	}

	@Override
	public User getUser(Integer userID) {
		return users.get(userID);
	}

	@Override
	public List<Rate> getTopTenMovies() {

		// sort the ratings by it comparable interface.
		Collections.sort(ratings);

		// reverse the ratings list
		Collections.reverse(ratings);

		// if the ratings list is greater than 10
		// return the first 10 ratings.
		// else
		// return all the ratings.
		return (ratings.size() > 10) ? ratings.subList(0, 10) : ratings;

	}

	@Override
	public List<Movie> getUserRecommendations(Integer userID) {

		// recommended movies the user has not seen
		List<Movie> recommendations = new ArrayList<Movie>();

		// movie ids for the recommended movies.
		TreeSet<Integer> recommendedMovieIDs = new TreeSet<Integer>();

		// user to get the recommended films for.
		User tempUser = getUser(userID);

		// loop throught all other users to get films that have not been rated
		// by the tempUser
		for (int x = 1; x < userId; x++)
			if (x == userID)
				continue;
			else
				tempUser.setRecommendations(getUser(x));

		recommendedMovieIDs = tempUser.getRecommendations();

		// get the movie objects from the recommended movie ids.
		for (Integer movieId : recommendedMovieIDs)
			recommendations.add(getMovie(movieId));

		// return recommended films.
		return recommendations;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load() throws ClassNotFoundException, FileNotFoundException, IOException {

		// read the file in
		serializer.read();

		// pop the objects off the stack
		userId = (Integer) serializer.pop();
		movieId = (Integer) serializer.pop();
		ratings = (List<Rate>) serializer.pop();
		users = (Map<Integer, User>) serializer.pop();
		movies = (Map<Integer, Movie>) serializer.pop();

	}

	@Override
	public void write() throws ClassNotFoundException, IOException {

		// push the objects onto the stack.
		serializer.push(movies);
		serializer.push(users);
		serializer.push(ratings);
		serializer.push(movieId);
		serializer.push(userId);

		// write the stack to xml.
		serializer.write();
	}

	public void init(String path) {
		File usersFile = new File(path);

		In inUsers = new In(usersFile);

		// each field is separated(delimited) by a '|'
		String delims = "[|]";

		while (!inUsers.isEmpty()) {

			// get user and rating from data source
			String userDetails = inUsers.readLine();

			// parse user details string
			String[] userTokens = userDetails.split(delims);

			// userTokens length is 4, then it's a rating class, 
			// else if its length is 7 it's a user class, 
			// else its a movie class if its length is 23
			if (userTokens.length == 4)
				addRate(Integer.parseInt(userTokens[0]), Integer.parseInt(userTokens[1]),
						Integer.parseInt(userTokens[2]));
			else if (userTokens.length == 7)
				addUser(userTokens[1], userTokens[2], Integer.parseInt(userTokens[3]), userTokens[4].charAt(0),
						userTokens[5]);
			else if (userTokens.length == 23)
				addMovie(userTokens[1], userTokens[2], userTokens[3]);
		}

	}

	@Override
	public Movie getMovie(Integer movieID) {
		return movies.get(movieID);
	}

	@Override
	public Integer getMovieId() {
		return movieId;
	}

	@Override
	public Integer getUserId() {
		return userId;
	}

}
