package controller;

import boundary.ITUI;
import dal.IUserDAO;
import dal.IUserDAO.DALException;
import entity.IFun.InputException;
import entity.userCreation.IUserCreation;
import entity.userCreation.UserCreator;

public class UserCreationController {

	private ITUI tui;
	private IUserCreation userCreator;

	public UserCreationController( IUserCreation userCreator, ITUI tui) {
		this.tui = tui;
		this.userCreator = userCreator;
	}

	public void startUserCreation() {
		userCreator.startUserCreation();
		tui.showResponse("User creation has begun.");
		
		getUserID();
		getUserName();
		getInitials();
		getCpr();
		getRoles();
		
		try {
			tui.showResponse(userCreator.endUserCreation());
		} catch (InputException e) {
			System.out.println("Something went wrong with the password generation.");
		} catch (DALException e) {
			System.out.println("Something went wrong when trying to add a user.");
		}

	}

	private void getUserID() {
		String question = "Please enter the ID of the new user. It has to be between 11 and 99";
		while (true) {
			String userInput = askAndHold(question);
			try {
				userCreator.setUserID(Integer.parseInt(userInput));
				break;
			} catch (NumberFormatException e) {
				tui.showResponse("That is not a number.");
			} catch (InputException e) {
				tui.showResponse(e.getMessage());
			}
		}
	}

	private void getUserName() {
		String question = "Please enter a username. The username has to have a length between 2 and 20.";
		while (true) {
			String userInput = askAndHold(question);
			try {
				userCreator.setUserName(userInput);
				break;
			} catch (InputException e) {
				tui.showResponse(e.getMessage());
			}
		}

	}

	private void getInitials() {
		String question = "Please enter initials. The intials have to contain 2-4 letters.";
		while (true) {
			String userInput = askAndHold(question);
			try {
				userCreator.setIni(userInput);
				break;
			} catch (InputException e) {
				tui.showResponse(e.getMessage());
			}
		}
	}

	private void getCpr() {
		String question = "Please enter the users cpr number.";
		while (true) {
			String userInput = askAndHold(question);
			try {
				userCreator.setCpr(userInput);
				break;
			} catch (InputException e) {
				tui.showResponse(e.getMessage());
			}
		}

	}

	private void getRoles() {
		String question = "What roles do you want to assign to the user? Enter a number corresponding to a role:";
		String[] validRoles = userCreator.getValidroles();
		boolean[] chosenRoles = new boolean[validRoles.length];
		for (int i = 0; i < chosenRoles.length; i++) {
			chosenRoles[i] = false;
		}

		while (true) {
			String out = question;
			for (int i = 0; i < chosenRoles.length; i++) {
				if (chosenRoles[i] == false) {
					out += ("\n" + i + ": " + validRoles[i]+".");
				}
			}
			out+="\n"+chosenRoles.length+": Stop selecting roles.";
			try {
				int userInput = Integer.parseInt(askAndHold(out));
				if(userInput==chosenRoles.length){
					break;
				}
				else if(userInput>chosenRoles.length||userInput<0){
					tui.showResponse("That is not a valid choice.");
				}
				else{
					userCreator.addRole(validRoles[userInput]);
					chosenRoles[userInput]=true;
				}
			} catch (NumberFormatException e) {
				tui.showResponse("That is not a number.");
			} catch (InputException e) {
				tui.showResponse("An internal error occured. Role was not valid.");
			}
		}
	}

	private String askAndHold(String question) {
		tui.showResponse(question);
		return tui.getUserInput();
	}

}
