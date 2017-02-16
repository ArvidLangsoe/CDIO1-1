package boundary;

import java.util.Scanner;

import boundary.interfaces.IUserCreationBoundary;
import dto.InputException;
import dto.UserDTO;

public class UserCreationBoundary implements IUserCreationBoundary {

	private UserDTO newUser;
	private Scanner sc;

	@Override
	public UserDTO createNewUser() {
		sc = new Scanner(System.in);
		newUser = new UserDTO();
		try {
			newUser.setPassword(newUser.generatePassword());
		} catch (InputException e) {
			System.out.println("Something went horribly wrong when generating a password.");
		}
		getUserID();
		getUserName();
		getInitials();
		getCpr();
		getRoles();

		sc.close();
		return newUser;
	}


	public UserDTO editUserId() {
		System.out.println("The userId already exist in the database. Please chose another:");
		getUserID();
		return newUser;
	}

	private void getUserID() {
		String question="Please enter the ID of the new user. It has to be between 11 and 99";
		while (true) {
			System.out.println(question);
			String userInput = sc.nextLine();
			try {
				newUser.setUserId(Integer.parseInt(userInput));
				break;
			} catch (NumberFormatException e) {
				System.out.println("That is not a number.");
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void getUserName() {
		String question = "Please enter a username. The username has to have a length between 2 and 20.";
		while (true) {
			System.out.println(question);
			String userInput = sc.nextLine();
			try {
				newUser.setUserName(userInput);
				break;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private void getInitials() {
		String question = "Please enter initials. The intials have to contain 2-4 letters.";
		while (true) {
			System.out.println(question);
			String userInput = sc.nextLine();
			try {
				newUser.setIni(userInput);
				break;
			} catch (InputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void getCpr() {
		String question = "Please enter the users cpr number.";
		while (true) {
			System.out.println(question);
			String userInput = sc.nextLine();
			try {
				newUser.setCpr(userInput);
				break;
			} catch (InputException e) {
				System.out.println(e.getMessage());
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
				System.out.println(out);
				int userInput = Integer.parseInt(sc.nextLine());
				if (userInput == chosenRoles.length) {
					break;
				} else if (userInput > chosenRoles.length || userInput < 0) {
					System.out.println("That is not a valid choice.");
				} else {
					newUser.addRole(validRoles[userInput]);
					chosenRoles[userInput] = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("That is not a number.");
			} catch (InputException e) {
				System.out.println("An internal error occured. Role was not valid.");
			}
		}

	}

}
