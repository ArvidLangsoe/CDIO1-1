package entity;

import java.util.List;

import dal.IUserDAO;
import dto.UserDTO;
import entity.IFun.InputException;

public class UserCreator {

	private IUserDAO data;

	public UserCreator(IUserDAO data) {
		this.data = data;
	}

	public String createUser(String userName, String ini, String cpr, String[] roles) throws InputException {
		
		UserDTO newUser= new UserDTO();
		newUser.setUserName(userName);
		newUser.setIni(ini);
		isCprValid(cpr);
		newUser.setCpr(cpr);
		newUser.setPassword(generatePassword());
		for(int i=0;i<roles.length;i++){
			if(roles[i]=="Admin"||roles[i]=="Pharmacis"||roles[i]=="Foreman"||roles[i]=="Operator"){
				newUser.addRole(roles[i]);
			}
			
		}
		
		
		
		return null;
	}

	public String generatePassword() {
		String password = "";

		int passLength = 8;

		while (!isPasswordValid(password)) {
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
		}

		return password;
	}

	public boolean isPasswordValid(String password) {
		boolean smallLetterFlag = false;
		boolean bigLetterFlag = false;
		boolean numberFlag = false;
		boolean specialCharFlag = false;
		int groupCount = 0;
		char currentChar;
		int charValue;

		if (password.length() < 6) {
			return false;
		}
		for (int i = 0; i < password.length(); i++) {
			currentChar = password.charAt(i);
			charValue = Character.getNumericValue(currentChar);

			// Is the char a small letter?
			if (charValue >= 97 && charValue <= 122) {
				if (smallLetterFlag == false) {
					groupCount++;
					smallLetterFlag = true;
				}
			}
			// Is the char a big letter?
			else if (charValue >= 65 && charValue <= 90) {
				if (bigLetterFlag == false) {
					groupCount++;
					bigLetterFlag = true;
				}
			}
			// is the char a number?
			else if (charValue >= 48 && charValue <= 57) {
				if (numberFlag == false) {
					groupCount++;
					numberFlag = true;
				}
			}
			// Is the char an allowed special character.
			else if (currentChar == '.' || currentChar == '-' || currentChar == '_' || currentChar == '+'
					|| currentChar == '!' || currentChar == '?' || currentChar == '=') {
				if (specialCharFlag == false) {
					groupCount++;
					specialCharFlag = true;
				}
			}
			// If the char is not allowed.
			else {
				return false;
			}

		}
		// If the password dosen't contain chars from atleast 3 groups.
		if (groupCount < 3) {
			return false;
		}

		return true;

	}

	public boolean isCprValid(String cpr) throws InputException {
		int[] validationNumber = new int[] { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		int[] cprArray = new int[10];

		if (cpr.length() != 10) {
			throw new InputException("The cpr number is not long enough.");
		}

		// Save the cpr number in an array.
		for (int i = 0; i < 10; i++) {
			cprArray[i] = Integer.parseInt(cpr.substring(i, i));
		}
		if (cprArray[0] > 3) {
			throw new InputException(cprArray[0] + "" + cprArray[1] + " is not a valid date.");
		}
		if ((cprArray[2] > 2 || (cprArray[2] > 0 && cprArray[3] > 2))) {
			throw new InputException(cprArray[2] + "" + cprArray[3] + "is not a valid month.");
		}

		// Calculate the controle cifre:
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += cprArray[i] * validationNumber[i];
		}

		int remainder = (sum % 11);

		if (remainder == 1) {
			throw new InputException("The cpr number is invalid.");
		}

		int controlCifre = 11 - remainder;

		if (controlCifre != cprArray[9]) {
			throw new InputException("The cpr number is invalid.");
		}

		return true;
	}

}
