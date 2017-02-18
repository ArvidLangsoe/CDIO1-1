package boundary;



import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dto.UserDTO;
import dto.Validator;
import dto.Validator.InputException;

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
		String question="Please enter the ID of the new user. It has to be between 11 and 99";
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
		String question = "Please enter a username. The username has to have a length between 2 and 20.";
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
		String question = "Please enter the users cpr number.";
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
		String question = "What roles do you want to assign to the user? Enter a number corresponding to a role:";
		String[] validRoles = Validator.validRoles;
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
				show(out);
				int userInput = getInt();
				if (userInput == chosenRoles.length) {
					break;
				}
				else if (userInput > chosenRoles.length || userInput < 0) {
					show("That is not a valid choice.");
				} else {
					newUser.addRole(validRoles[userInput]);
					chosenRoles[userInput] = true;
				}
			} catch (NumberFormatException e) {
				show("That is not a number.");
			}
		}

	}

}
