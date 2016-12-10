package controller;

/**
 * @author Brian
 * Program to run all test cases.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import model.MovieTest;
import model.UserTest;
import model.RateTest;
import fileLogger.SerializerTest;

@RunWith(Suite.class)
@SuiteClasses({ LikeMovieTest.class, RateTest.class, UserTest.class, MovieTest.class, SerializerTest.class})
public class AllTests {
	
}
