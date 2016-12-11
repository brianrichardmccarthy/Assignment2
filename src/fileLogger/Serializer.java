package fileLogger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import api.SerializerAPI;

/**
 * Serializer class implements the serializer interface.<br>
 * Also uses Xstream for IO.
 * @author Brian
 * @see SerializerAPI
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Serializer implements SerializerAPI {
	
	private String file;
	private Stack stack;

	public Serializer(String file) {
		stack = new Stack();
		setFile(file);
	}

	/**
	 * Change the path to the file this class reads and writes to.
	 * @param file (String)
	 */
	public void setFile(String file) {
		this.file = file;
	}
	
	/**
	 * Get the path to the file this class reads and writes to.
	 * @return (String)
	 */
	public String getFile() {
		return file;
	}
	
	@Override
	public void push(Object o) {
		stack.push(o);
	}

	@Override
	public Object pop() {
		return stack.pop();
	}

	@Override
	public void read() throws IOException, ClassNotFoundException {

		// declare a new object input stream
		ObjectInputStream is = null;

		try {
			
			// create a new XStream object
			XStream xstream = new XStream(new DomDriver());
			
			// use the xstream object to inital the object input stream.
			is = xstream.createObjectInputStream(new FileReader(file));
			
			// read the objects from xml
			stack = (Stack) is.readObject();
		} finally {
			// close open resources
			if (is != null) {
				is.close();
			}
		}
	}

	@Override
	public void write() throws IOException {

		// declare output stream
		ObjectOutputStream os = null;

		try {
			
			// create a new XStream object
			XStream xstream = new XStream(new DomDriver());
			
			// use the xstream object to inital the object output stream.
			os = xstream.createObjectOutputStream(new FileWriter(file));
			
			// write the stack.
			os.writeObject(stack);
			
		} finally {
			// close open resources
			if (os != null) {
				os.close();
			}
		}
	}

	@Override
	public boolean isFile() {
		return new File(file).exists() && new File(file).isFile();
	}
	
}