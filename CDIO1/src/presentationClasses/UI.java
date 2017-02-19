package presentationClasses;

/**
 * The user interface (UI) makes sure the boundaries either TUI or GUI have the 
 * right methods to communicate with the rest of the program.
 * @author Group 22
 *
 */
public interface UI {
	/**
	 * returns a string.
	 * @return The string to be returned.
	 */
	public String getString();
	
	/**
	 * Returns an integer.
	 * @return The integer to be returned.
	 */
	public int getInt();
	
	/**
	 * Shows a messages.
	 * @param msg The message to be showed.
	 */
	public void show(String msg);

}
