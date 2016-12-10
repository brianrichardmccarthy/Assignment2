package model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class RateTest {

	/**
	 * Test the Rate constructor.
	 */
	@Test
	public void testConstructor() {
		Rate rate = null;
		rate = new Rate(1, 5);
		assertTrue(rate != null);
	}
	
	/**
	 * Test the rate accessor and mutator
	 */
	@Test
	public void testGettersAndSetters() {
		Rate rate = new Rate(1, 5);
		rate.setRate(4);
		assertTrue(rate.getRate() == 9);
		assertTrue(rate.getMovieID() == 1);
	}
	
	/**
	 * Test the comparable method that it ranks the movies lowest to highest (Natural Ordering).
	 */
	@Test
	public void testComparable() {
		List<Rate> movies = new ArrayList<Rate>();
		
		movies.add(new Rate(1, 5));
		movies.add(new Rate(2, 4));
		movies.add(new Rate(4, 2));
		movies.add(new Rate(5, 1));
		movies.add(new Rate(3, 3));
		
		Collections.sort(movies);
		
		for (int x = 0; x < movies.size(); x++)
			assertTrue(movies.get(x).getRate() == (x+1));
	}
	
	/**
	 * Test the rate Equals method.
	 */
	@Test
	public void testEquals() {
		Rate rate1 = new Rate(1, 5), rate2 = new Rate(2, 5);
		assertTrue(!rate1.equals(rate2) && rate1.equals(rate1));
	}

}
