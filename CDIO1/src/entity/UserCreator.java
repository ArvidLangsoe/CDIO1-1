package entity;

import java.util.ArrayList;
import java.util.List;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dto.UserDTO;
import entity.IFun.InputException;

public class UserCreator {

	private IUserDAO data;
	UserDTO newUser;

	public UserCreator(IUserDAO data) {
		this.data = data;
	}

	public void startUserCreation() {
		newUser = new UserDTO();
	}

	public void setUserID(int userID) throws InputException {

		try {
			UserDTO user = data.getUser(userID);
			if (user != null) {
				throw new InputException("This user id is already taken.");
			}

		} catch (DALException e) {

		}

		newUser.setUserId(userID);
	}

	public void setUserName(String userName) throws InputException {
		newUser.setUserName(userName);
	}

	public void setIni(String ini) throws InputException {
		newUser.setIni(ini);
	}

	public void setCpr(String cpr) throws InputException {
		newUser.setCpr(cpr);
	}

	public void addRole(String role) throws InputException {
		newUser.addRole(role);
	}

	public String addUserToData() throws InputException {

		newUser.setPassword(generatePassword());

		try {
			data.createUser(newUser);
		} catch (DALException e) {
			return "Failure. Something went wrong when creating the user.";
		}

		return "Succes.";
	}

	private String generatePassword() {
		String password = "";

		int passLength = 8;
		boolean passwordValid = false;
		while (!passwordValid) {
			password = "";
			for (int i = 0; i < passLength; i++) {
				char newCharacther;
				int randGroup = (int) (Math.random() * 100);

				// Add a special characther
				if (randGroup < 5) {
					String specialCharacthers = ".-_+!?=";
					int rand = (int) (Math.random() * specialCharacthers.length());
					newCharacther = specialCharacthers.charAt(rand);
				}
				// Add a small letter.
				else if (randGroup < 30) {
					int rand = (int) (Math.random() * (122 - 97 + 1) + 97);
					newCharacther = (char) rand;

				}
				// Add a large letter.
				else if (randGroup < 55) {
					int rand = (int) (Math.random() * (90 - 65 + 1) + 65);
					newCharacther = (char) rand;
				}
				// Add a number.
				else {
					int rand = (int) (Math.random() * (57 - 48 + 1) + 48);
					newCharacther = (char) rand;
				}

				password += newCharacther + "";
			}
			try {
				newUser.isPasswordValid(password);
				passwordValid = true;
			} catch (InputException e) {

			}
		}

		return password;
	}

}
