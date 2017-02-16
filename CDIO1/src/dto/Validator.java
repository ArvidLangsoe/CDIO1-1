package dto;

public class Validator {
	
	
	public static final String[] validRoles = new String[] { "Admin", "Pharmacist", "Foreman", "Operator" };
	
	public static boolean validateUserID(int userID) throws InputException {
		if (userID < 11 || userID > 99) {
			throw new InputException("This user id is invalid. User ID's has to be between 11 and 99");
		}
		return true;
	}
	
	public static boolean validateUsername(String username) throws InputException {
		if (username.length() < 2 || username.length() > 20) {
			throw new InputException(
					"This username is invalid. Usernames has to be between 2 and 20 characthers long.");
		}
		return true;
	}
	
	public static boolean validateInitials(String ini) throws InputException {
		if (ini.length() < 2 || ini.length() > 4) {
			throw new InputException("These initials are invalid. Initials has to be between 2 and 4 characthers long.");
		}
		return true;
	}
	
	public static boolean validateCPR(String cpr) throws InputException {
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
		
		// Calculate the validation number:
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
	
	public static boolean validatePassword(String password) throws InputException {
		boolean smallLetterFlag = false;
		boolean bigLetterFlag = false;
		boolean numberFlag = false;
		boolean specialCharFlag = false;
		int groupCount = 0;
		char currentChar;
		int charValue;
		
		if (password.length() < 6) {
			throw new InputException("This password is too short.");
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
				throw new InputException(currentChar + " is not a valid characther.");
			}
		}
		// If the password dosen't contain chars from atleast 3 groups.
		if (groupCount < 3) {
			throw new InputException(
					"This password does not contain characthers from 3 different groups.\n The groups are: 'a-z' , 'A-Z','0-9', {'.', '-', '_', '+', '!', '?', '='}");
		}
		return true;
	}
	
	public static boolean validateRole(String role) throws InputException {
		for (int i = 0; i < validRoles.length; i++) {
			if (!role.equals(validRoles[i])) {
				throw new InputException("This is not a valid role.");
			}
		}
		return true;
	}

	//Inner class - Exception class.
	public static class InputException extends Exception 
	{
		private static final long serialVersionUID = 7884927058176762594L;
		
		public InputException(String msg)
		{
			super(msg);
		}
	}
}


