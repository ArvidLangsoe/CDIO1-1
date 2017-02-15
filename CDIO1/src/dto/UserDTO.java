package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import boundary.TUI;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 4545864587995944260L;
	public final String[] validRoles = new String[] { "Admin", "Pharmacist", "Foreman", "Operator" };
	private int userId; 
	private String userName;
	private String ini;
	private String cpr;
	private String password;
	private List<String> roles;

	public UserDTO() {
		this.roles = new ArrayList<>();
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) throws InputException {
		isUserIDValid(userId);
		this.userId = userId;
	}

	public boolean isUserIDValid(int userID) throws InputException {
		if (userID < 11 || userID > 99) {
			throw new InputException("This user id is invalid. User ID's has to be between 11 and 99");
		}
		return true;
	}


	public String getUserName() {
		return userName;
	}

	
	public void setUserName(String userName) throws InputException {
		isUserNameValid(userName);
		this.userName = userName;
	}

	public boolean isUserNameValid(String userName) throws InputException {

		if (userName.length() < 2 || userName.length() > 20) {
			throw new InputException(
					"This username is invalid. Usernames has to be between 2 and 20 characthers long.");
		}
		return true;
	}

	public String getIni() {
		return ini;
	}
	
	public String generateInitials (String name)
	{
		String[] nameParts = name.split(" ");
		String newIni = "";
		if (nameParts.length == 1)
		{
			newIni = nameParts[0].substring(0, 2);
		}
		else
		{
			for(String namePart : nameParts)
			{
				newIni = newIni + namePart.substring(0, 1);
			}
		}
		return newIni;
	}

	public void setIni(String ini) throws InputException {
		isIniValid(ini);
		this.ini = ini;
	}

	public boolean isIniValid(String ini) throws InputException {

		if (ini.length() < 2 || ini.length() > 4) {
			throw new InputException("These initials are invalid. Initials has to be between 2 and 4 characthers long.");
		}
		return true;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) throws InputException{
		isCprValid(cpr);
		this.cpr = cpr;
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

	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) throws InputException {
		isPasswordValid(password);
		this.password = password;
	}

	public boolean isPasswordValid(String password) throws InputException {
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
	
	public String generateValidPassword() {
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
				isPasswordValid(password);
				passwordValid = true;
			} catch (InputException e) {

			}
		}

		return password;
	}

	public List<String> getRoles() {
		return roles;
	}


	public void addRole(String role) throws InputException {
		isRoleValid(role);
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).equals(role)) {
				return;
			}
		}
		this.roles.add(role);
	}


	public boolean isRoleValid(String role) throws InputException {

		
		for (int i = 0; i < validRoles.length; i++) {
			if (!role.equals(validRoles[i])) {
				throw new InputException("This is not a valid role.");
			}
		}
		return true;
	}

	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public void removeRole(String role) throws InputException{
		isRoleValid(role);
		
		if(!this.roles.remove(role))
			throw new InputException("This user doesn't have role: "+role);
	}
	
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public void removeAllRoles() {
		this.roles.clear();
	}
	
	
	public boolean hasRole(String role)
	{
		boolean hasRole = false;
		for(int i = 0; i < roles.size(); i++)
		{
			if (roles.get(i).equals(role))
			{
				hasRole = true;
			}
		}
		return hasRole;
	}

	@Override
	public String toString() {
		String newString = "UserDTO [userId =" + userId + ", userName =" + userName + ", ini =" + ini + ", roles = ";
		
		for(String role : roles)
		{
			newString = newString + role;
		}
		
		return newString + "]";
	}

}
