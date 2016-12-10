package fileLogger;

/**
 * @author Brian
 * Test the serializer class
 */

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import api.SerializerAPI;

public class SerializerTest {

	/**
	 * Check that different types of Objects can be pushed on the stack.<br>
	 * Then check if they are poped off in the correct order they were pushed on.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testStack() {

		SerializerAPI serializer = new Serializer();

		ArrayList<Double> temp = new ArrayList<Double>();
		temp.add(2.2);
		temp.add(2.1);
		temp.add(22.2);
		temp.add(1.5);

		serializer.push(new Integer(1));
		serializer.push("Hello World");
		serializer.push(temp);

		ArrayList<Double> temp2 = (ArrayList<Double>) serializer.pop();

		assertTrue(temp2.size() == temp.size());

		for (int x = 0; x < temp2.size(); x++)
			assertTrue(temp.get(x).equals(temp2.get(x)));

		assertTrue(serializer.pop().equals("Hello World"));
		assertTrue(serializer.pop().equals(1));

	}

	/**
	 * Test that if data is pushed onto the stack,<br>
	 * Then written to a file,<b>
	 * It's succesfully read back.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testIO() {

		Serializer serializer = new Serializer();

		ArrayList<Double> temp = new ArrayList<Double>();
		temp.add(2.2);
		temp.add(2.1);
		temp.add(22.2);
		temp.add(1.5);
		serializer.push(new Integer(1));
		serializer.push("Hello World");
		serializer.push(temp);

		serializer.setFile("./Data/test.xml");

		try {
			serializer.write();
		} catch (IOException e) {
			fail();
		}

		assertTrue(new File("./Data/test.xml").exists() && new File("./Data/test.xml").isFile());

		try {
			serializer.read();
		} catch (ClassNotFoundException | IOException e) {
			fail();
		}

		ArrayList<Double> temp2 = (ArrayList<Double>) serializer.pop();

		assertTrue(temp2.size() == temp.size());

		for (int x = 0; x < temp2.size(); x++)
			assertTrue(temp.get(x).equals(temp2.get(x)));

		assertTrue(serializer.pop().equals("Hello World"));
		assertTrue(serializer.pop().equals(1));
	}
	
	/**
	 * Tests the path is a file
	 */
	@Test
	public void testIsFile() {
		Serializer serializer = new Serializer();
		// test that ./Data/data.xml is a file
		assertTrue(serializer.isFile());
		
		// set the path to an empty String
		serializer.setFile("");
		
		// check that is file method can't find the file at the path
		assertTrue(!serializer.isFile());
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
