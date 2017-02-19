package presentationClasses;

/**
 * The user interface (UI) makes sure the boundaries either TUI or GUI have the 
 * right methods to communicate with the rest of the program and the user.
 * @author Group 22
 *
 */
public interface UI {
	/**
	 * returns a string from the user.
	 * @return The string to be returned.
	 */
	public String getString();
	
	/**
	 * Returns an integer from the user.
	 * @return The integer to be returned.
	 */
	public int getInt();
	
	/**
	 * Shows a messages to a user.
	 * @param msg The message to be showed.
	 */
	public void show(String msg);

}
