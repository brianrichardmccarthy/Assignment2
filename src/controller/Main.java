package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import api.LikeMovieAPI;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

/**
 * Command Line Interface for Like Movie.<br>
 * Uses asg.cliche-110413.jar<br>
 * instead of switch inside of do while loop.
 * 
 * @author Brian
 *
 */

public class Main {

	private LikeMovieAPI likeMovieAPI;

	/**
	 * Constructor for Main.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Main() throws ClassNotFoundException, FileNotFoundException, IOException {
		likeMovieAPI = new LikeMovie();
		likeMovieAPI.prime();
	}

	/**
	 * CLI Method to add user.
	 * 
	 * @param firstName
	 *            (String)
	 * @param secondName
	 *            (String)
	 * @param age
	 *            (Integer)
	 * @param gender
	 *            (char)
	 * @param occupation
	 *            (String)
	 */
	@Command(description = "Add a User")
	public void addUser(@Param(name = "First name") String firstName, @Param(name = "Second name") String secondName,
			@Param(name = "Age") Integer age, @Param(name = "Gender") String gender,
			@Param(name = "occupation") String occupation) {
		likeMovieAPI.addUser(firstName, secondName, age, gender.charAt(0), occupation);
	}

	/**
	 * CLI Method to remove user.
	 * 
	 * @param userID
	 *            (Integer)
	 */
	@Command(description = "Remove a User")
	public void removeUser(@Param(name = "User ID") Integer userID) {
		likeMovieAPI.removeUser(userID);
	}

	/**
	 * CLI Method to add a movie rating.
	 * 
	 * @param userID
	 * @param movieID
	 * @param rate
	 */
	@Command(description = "Add a Rate")
	public void addRate(@Param(name = "User ID") Integer userID, @Param(name = "Movie ID") Integer movieID,
			@Param(name = "User rating") Integer rate) {
		likeMovieAPI.addRate(userID, movieID, rate);
	}

	/**
	 * CLI Method to add movie.
	 * 
	 * @param title
	 *            (String)
	 * @param year
	 *            (String)
	 * @param url
	 *            (String)
	 */
	@Command(description = "Add a Movie")
	public void addMovie(@Param(name = "Movie Name") String title, @Param(name = "Release Year") String year,
			@Param(name = "Movie Website") String url) {
		likeMovieAPI.addMovie(title, year, url);
	}

	/**
	 * CLI method to get movie information.
	 * 
	 * @param movieID
	 *            (Integer)
	 */
	@Command(description = "Get Movie info")
	public void getMovie(@Param(name = "Movie ID") Integer movieID) {
		System.out.println(likeMovieAPI.getMovieDetails(movieID));
	}

	/**
	 * CLI Method to get a user ratings.
	 * 
	 * @param userID
	 *            (Integer)
	 */
	@Command(description = "Get User Ratings")
	public void getUserRatings(@Param(name = "User ID") Integer userID) {
		System.out.println(likeMovieAPI.getUserRatings(userID));
	}

	/**
	 * CLI Method to get user recommended movie.
	 * 
	 * @param userID
	 *            (Integer).
	 */
	@Command(description = "Get User Recommendations")
	public void getUserRecommendations(@Param(name = "User ID") Integer userID) {
		System.out.println(likeMovieAPI.getUserRecommendations(userID));
	}

	/**
	 * CLI Method to get user information.
	 * 
	 * @param userID
	 *            (Integer)
	 */
	@Command(description = "Get User information")
	public void getUser(@Param(name = "User ID") Integer userID) {
		System.out.println(likeMovieAPI.getUser(userID));
	}

	/**
	 * CLI method to get the top ten movies.
	 */
	@Command(description = "Get Top Ten Movies")
	public void getTopTenMovies() {
		System.out.println(likeMovieAPI.getTopTenMovies());
	}

	/**
	 * CLI method to load the xml life.
	 * 
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Command(description = "Load file")
	public void loadFile() throws ClassNotFoundException, FileNotFoundException, IOException {
		likeMovieAPI.load();
	}

	/**
	 * CLI method write the xml file.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Command(description = "Write file")
	public void writeFile() throws ClassNotFoundException, IOException {
		likeMovieAPI.write();
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			// new main object.
			Main main = new Main();

			// create a new shell object.
			Shell shell = ShellFactory.createConsoleShell(">", "Welcome to Like Movie-console - ?help for instructions",
					main);

			// loop the shell menu.
			shell.commandLoop();

			// write the file to xml.
			main.likeMovieAPI.write();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Sorry there was an error writing the xml file.");
		}

	}
}
