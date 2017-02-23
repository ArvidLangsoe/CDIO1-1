package presentationLayer;

import dataAccessObjects.IUserDAO;
import dataAccessObjects.IUserDAO.DALException;
import dataTransferObjects.UserDTO;
import functionLayer.IDataVerifier;
import functionLayer.IDataVerifier.WrongDataException;
import staticClasses.Validator;
import staticClasses.Validator.InputException;

/**
 * The class UserCreator extends the class TUI. 
 * The responsibility of this class is to carry out the tasks of creating a new user and add it to the data.
 * @author Group 22
 *
 */
public class UserCreator extends TUI {

	// Instance variables.
	private UserDTO newUser; 
	
	private IDataVerifier data;

	/**
	 * Constructor
	 * 
	 * @param data
	 *            The data access object to use.
	 */
	public UserCreator(IDataVerifier data) {
		this.data = data;
	}

	/**
	 * Creates a new user (UserDTO) by asking the user administrator for the information required and generates the rest.
	 */
	public void createNewUser() {
		newUser = new UserDTO();
		
		getUserID();
		getUserName();
		getCpr();
		getRoles();


		try {
			data.createUser(newUser);
			show("User: " + newUser.getUserName()+ " has been added.");
		} catch (WrongDataException e) {
			show(e.getMessage());
		}
		
		
	}

	/**
	 * Asks the user administrator which userID should be assigned to the new user (UserDTO).
	 */
	private void getUserID() {
		String question="\n"+"Please enter the ID of the new user. It has to be between 11 and 99";
		while (true) {
			show(question);
			try {
				int userID=getInt();
				Validator.validateUserID(userID);
				newUser.setUserId(userID);
				break;
			} catch (NumberFormatException e) {
				show("That is not a number.");
			} catch (InputException e) {
				show(e.getMessage());
			}
		}
	}

	/**
	 * Asks the user administrator which username should be assigned to the new user (UserDTO)
	 */
	private void getUserName() {
		String question = "\n"+"Please enter a username. The username has to have a length between 2 and 20.";
		while (true) {
			show(question);
			String userInput = getString();
			try {
				Validator.validateUsername(userInput);
				newUser.setUserName(userInput);
				break;
			} catch (InputException e) {
				show(e.getMessage());
			}
		}

	}

	/**
	 * Asks the user administrator which CPR should be assigned to the new user (UserDTO).
	 */
	private void getCpr() {
		String question = "\n"+"Please enter the users cpr number.";
		while (true) {
			show(question);
			String userInput = getString();
			try {
				Validator.validateCPR(userInput);
				newUser.setCpr(userInput);
				break;
			} catch (InputException e) {
				show(e.getMessage());
			}
		}

	}

	/**
	 * Asks the user administrator which roles should be assigned to the new user (UserDTO).
	 */
	private void getRoles() {
		String question = "\n"+"What roles do you want to assign to the user? Enter a number corresponding to a role:";
		String[] validRoles = Validator.validRoles;
		boolean[] chosenRoles = new boolean[validRoles.length];
		for (int i = 0; i < chosenRoles.length; i++) {
			chosenRoles[i] = false;
		}

		while (true) {
			String out = question;
			for (int i = 1; i <= chosenRoles.length; i++) {
				if (chosenRoles[i-1] == false) {
					out += ("\n" + i + ": " + validRoles[i-1] + ".");
				}
			}
			out += "\n" + (chosenRoles.length+1) + ": Stop selecting roles.";
			try {
				show(out);
				int userInput = getInt();
				if (userInput == chosenRoles.length+1) {
					break;
				}
				else if (userInput > chosenRoles.length+1 || userInput < 0) {
					show("That is not a valid choice.");
				} else {
					newUser.addRole(validRoles[userInput-1]);
					chosenRoles[userInput-1] = true;
				}
			} catch (NumberFormatException e) {
				show("That is not a number.");
			}
		}

	}
}
