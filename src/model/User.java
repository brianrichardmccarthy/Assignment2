package model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import com.google.common.base.Objects;

import fileLogger.ToJsonString;

/**
 * User class for like movie
 * 
 * @author Brian
 *
 */

public class User {

	private final Integer id;
	private String firstName, lastName, occupation;
	private Integer age;
	private char gender;
	private Map<Integer, Integer> moviesRated;
	private TreeSet<Integer> keys;
	private TreeSet<Integer> recommendations;

	/**
	 * Constructor for User
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param occupation
	 * @param age
	 * @param gender
	 */
	public User(Integer id, String firstName, String lastName, String occupation, Integer age, char gender) {
		this.id = id;
		setFirstName(firstName);
		setLastName(lastName);
		setOccupation(occupation);
		setAge(age);
		setGender(gender);
		moviesRated = new HashMap<Integer, Integer>();
		keys = new TreeSet<Integer>();
		recommendations = new TreeSet<Integer>();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof User) {
			final User that = (User) obj;

			return Objects.equal(this.id, that.id) && Objects.equal(this.firstName, that.firstName)
					&& Objects.equal(this.lastName, that.lastName) && Objects.equal(this.occupation, that.occupation)
					&& Objects.equal(this.age, that.age) && this.gender == that.gender
					&& Objects.equal(this.moviesRated, that.moviesRated) && Objects.equal(this.keys, that.keys)
					&& Objects.equal(this.recommendations, that.recommendations);
		}

		return false;
	}

	/**
	 * Calculates the similarity between two users by multiplying this users'
	 * rating for a movie with the second users' rating for the same movie
	 * 
	 * @param user (User)
	 * @return (int) how similar the two users are.
	 */
	private int calSimilar(User user) {
		int similar = 0;

		for (Integer id : this.keys)
			if (user.keys.contains(id))
				similar += this.moviesRated.get(id) * user.moviesRated.get(id);

		return similar;
	}

	/**
	 * Set the recommendations for this user.
	 * @param user (User) other use to be compared to with this user.
	 * @return (int) how similar the two users are.
	 */
	public int setRecommendations(final User user) {
		int similar = calSimilar(user);

		// if similar id greater than zero
		if (similar > 0) {
			
			// assign the other user keys to a temporary treeset
			TreeSet<Integer> tempOtherKeys = user.keys;
			
			// remove any key that is in both this keys and temp other keys
			tempOtherKeys.removeAll(this.keys);
			
			// add all remainings keys to recommendations.
			recommendations.addAll(tempOtherKeys);
		}

		return similar;
	}

	/**
	 * Accessor for recommended movie keys.
	 * @return (TreeSet<Integer>) movie ID,
	 */
	public TreeSet<Integer> getRecommendations() {
		return recommendations;
	}

	/**
	 * Method to add movie id and rating.
	 * @param movieID (Integer) the movie id the user rated.
	 * @param rate (Integer) the rating the user gave the movie.
	 */
	public void addMovie(Integer movieID, Integer rate) {
		keys.add(movieID);
		moviesRated.put(movieID, rate);
	}

	/**
	 * Accessor for movies the user has rated
	 * @return (Map<MovieID as Integer, Rate as Integer>)
	 */
	public Map<Integer, Integer> getMovies() {
		return moviesRated;
	}

	/**
	 * Accessor for the user id
	 * @return (Integer) user id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Accessor for the user first name
	 * @return (String) firstname.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Mutator for the user firstname
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Accessor for the last name
	 * @return (String) users' lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Mutator for the users' lastname
	 * @param lastName (String) users' lastname
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Accessor for occupation
	 * @return (String)
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * Mutator for occupation
	 * @param occupation (String)
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * Accessor for age
	 * @return (Integer)
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Mutator for age
	 * @param age (Integer)
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Accessor for Gender
	 * @return (char)
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Mutator for gender
	 * @param gender (char)
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return new ToJsonString(getClass(), this).toString();
	}

}
