package api;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for Serializer class.
 * @author Brian
 * @see Serializer
 */

public interface SerializerAPI {
	
	/**
	 * Push a Object to the top of the stack.
	 * @param o (Object) - Can be any java class including list, and Maps.
	 */
	void push(Object o);

	/**
	 * Pop the Object at the top of the stack.
	 * @return (Object) - Can be any java class including list, and Maps.
	 */
	Object pop();

	/**
	 * Write the stack to an xml file in data folder.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void write() throws IOException, ClassNotFoundException;

	/**
	 * Read the contents of an xml file and push onto the stack.
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void read() throws ClassNotFoundException, FileNotFoundException, IOException;

	/**
	 * Checks if the xml it reads and writes to exists.
	 * @return (Boolean) checks if the file exists.
	 */
	boolean isFile();
}