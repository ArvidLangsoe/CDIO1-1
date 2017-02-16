package boundary;


import boundary.interfaces.IUserCreationBoundary;
import boundary.interfaces.UI;
import dto.InputException;
import dto.UserDTO;

public class UserCreationBoundary implements IUserCreationBoundary {

	private UserDTO newUser;
	private UI tui;

	public UserCreationBoundary(UI tui){
		this.tui=tui;
	}
		
	@Override
	public UserDTO createNewUser() {
		newUser = new UserDTO();
		try {
			newUser.setPassword(newUser.generatePassword());
		} catch (InputException e) {
			tui.show("Something went horribly wrong when generating a password.");
		}
		
		getUserID();
		getUserName();
		newUser.setIni(newUser.generateInitials(newUser.getUserName()));
		getCpr();
		getRoles();


		return newUser;
	}


	public UserDTO editUserId() {
		tui.show("The userId already exist in the database. Please chose another:");
		getUserID();
		return newUser;
	}

	private void getUserID() {
		String question="Please enter the ID of the new user. It has to be between 11 and 99";
		while (true) {
			tui.show(question);
			String userInput = tui.getString();
			try {
				newUser.setUserId(Integer.parseInt(userInput));
				break;
			} catch (NumberFormatException e) {
				tui.show("That is not a number.");
			} catch (InputException e) {
				tui.show(e.getMessage());
			}
		}
	}

	private void getUserName() {
		String question = "Please enter a username. The username has to have a length between 2 and 20.";
		while (true) {
			tui.show(question);
			String userInput = tui.getString();
			try {
				newUser.setUserName(userInput);
				break;
			} catch (InputException e) {
				tui.show(e.getMessage());
			}
		}

	}

	private void getInitials() {
		String question = "Please enter initials. The intials have to contain 2-4 letters.";
		while (true) {
			tui.show(question);
			String userInput =tui.getString();
			try {
				newUser.setIni(userInput);
				break;
			} catch (InputException e) {
				tui.show(e.getMessage());
			}
		}
	}

	private void getCpr() {
		String question = "Please enter the users cpr number.";
		while (true) {
			tui.show(question);
			String userInput = tui.getString();
			try {
				newUser.setCpr(userInput);
				break;
			} catch (InputException e) {
				tui.show(e.getMessage());
			}
		}

	}

	private void getRoles() {
		String question = "What roles do you want to assign to the user? Enter a number corresponding to a role:";
		String[] validRoles = newUser.validRoles;
		boolean[] chosenRoles = new boolean[validRoles.length];
		for (int i = 0; i < chosenRoles.length; i++) {
			chosenRoles[i] = false;
		}

		while (true) {
			String out = question;
			for (int i = 0; i < chosenRoles.length; i++) {
				if (chosenRoles[i] == false) {
					out += ("\n" + i + ": " + validRoles[i] + ".");
				}
			}
			out += "\n" + chosenRoles.length + ": Stop selecting roles.";
			try {
				tui.show(out);
				int userInput = tui.getInt();
				if (userInput == chosenRoles.length) {
					break;
				} else if (userInput > chosenRoles.length || userInput < 0) {
					tui.show("That is not a valid choice.");
				} else {
					newUser.addRole(validRoles[userInput]);
					chosenRoles[userInput] = true;
				}
			} catch (NumberFormatException e) {
				tui.show("That is not a number.");
			} catch (InputException e) {
				tui.show("An internal error occured. Role was not valid.");
			}
		}

	}

}
