package model;

import com.google.common.base.Objects;

import fileLogger.ToJsonString;

/**
 * Rate class for Like movie
 * @author Brian
 *
 */

public class Rate implements Comparable<Rate> {

	private Integer movieID, rate;

	/**
	 * Constructor for rate.
	 * @param movieID (Integer)
	 * @param rate (Integer)
	 */
	public Rate(Integer movieID, Integer rate) {
		this.movieID = movieID;
		setRate(rate);
	}

	/**
	 * Accessor for movie id.
	 * @return (Integer)
	 */
	public Integer getMovieID() {
		return movieID;
	}

	/**
	 * Accessor for the rating
	 * @return
	 */
	public Integer getRate() {
		return rate;
	}

	/**
	 * Mutator for the rating
	 * @param rate (Integer)
	 */
	public void setRate(Integer rate) {
		if (this.rate == null)
			this.rate = rate;
		else
			this.rate += rate;
	}

	/**
	 * Compare the rate of this class to another classs' rate (Natural Order).
	 * @return (int)
	 */
	@Override
	public int compareTo(Rate arg0) {
		return this.rate.compareTo(arg0.rate);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Rate) {
			final Rate that = (Rate) obj;
			return Objects.equal(this.movieID, that.movieID) && Objects.equal(this.rate, that.rate);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return new ToJsonString(getClass(), this).toString();
	}

}
