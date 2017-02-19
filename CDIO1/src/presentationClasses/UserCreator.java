package presentationClasses;



import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dataTransferObjects.UserDTO;
import dataTransferObjects.Validator;
import dataTransferObjects.Validator.InputException;

public class UserCreator extends  TUI {

	private UserDTO newUser;
	private IUserDAO data;

	public UserCreator(IUserDAO data2){
		this.data=data2;
	}
		

	public void createNewUser() {
		newUser = new UserDTO();
		
		
		
		getUserID();
		getUserName();
		getCpr();
		getRoles();
		
		newUser.setPassword(newUser.generatePassword());
		newUser.setIni(newUser.generateInitials(newUser.getUserName()));

		try {
			data.createUser(newUser);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show("User: " + newUser.getUserName()+ " has been added.");
		
	}


	public UserDTO editUserId() {
		show("The userId already exist in the database. Please chose another:");
		getUserID();
		return newUser;
	}

	private void getUserID() {
		String question="\n"+"Please enter the ID of the new user. It has to be between 11 and 99";
		while (true) {
			show(question);
			String userInput = getString();
			try {
				int userID=Integer.parseInt(userInput);
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
