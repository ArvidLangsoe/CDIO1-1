package presentationClasses;

import java.util.Scanner;

/**
 * The TUI boundary which implements the user interface (UI). The TUI
 * communicates with the user.
 * @author Group 22
 *
 */
public class TUI implements UI {
	// Instance variables.
	private Scanner scan;

	/**
	 * Constructor. Initializes a scanner object to read from the console.
	 */
	public TUI() {
		scan = new Scanner(System.in);
	}

	/**
	 * Asks the user for a String.
	 * 
	 * @return The String which the user entered.
	 */
	@Override
	public String getString() {
		String input = scan.nextLine();
		return input;
	}

	/**
	 * Asks the user for a integer.
	 * 
	 * @return The integer which the user entered.
	 */
	@Override
	public int getInt() {
		try {
			String inputInt = getString();
			return Integer.parseInt(inputInt);
		} catch (NumberFormatException e) {
			show("You must input an integer. Try again");
			return getInt();
		}
	}

	/**
	 * Shows a message from the system to the user.
	 * 
	 * @param msg
	 *            The message to show the user.
	 */
	@Override
	public void show(String msg) {
		System.out.println(msg);
	}

}
