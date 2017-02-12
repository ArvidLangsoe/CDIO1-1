package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dal.IUserDAO.DALException;
import entity.IFun.InputException;

/*
 * Denne klasse er entity for User.
 * */

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private int	userId;              
	private static int nextUserId = 12; //Global userId count.
	private String userName;            //userName first and last name.
	private String ini; //Initials created from name
	private String cpr; 
	private String password;
	private List<String> roles; //List of roles a user can have.
	
	public UserDTO(String name, String cpr, ArrayList<String> roles) throws InputException{
		
		this.userId = createUserId();
		setUserName(name);
		setIni(name);
		setCpr(cpr);
		this.password = createPassword();
		this.roles = roles;
	}
	
	//Only used for system admin
	public UserDTO() 
	{
		this.userId = 11;
		this.userName = "System Admin";
		this.ini = "SyAd";
		this.cpr = "999999-9999";
		this.password = "SystemAdmin!9";
		this.roles.add("System Admin"); //Because system Admin is better than Admin.
		this.roles.add("Admin");
		this.roles.add("Pharmacist");
		this.roles.add("Foreman");
		this.roles.add("Operator");
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
	
	public int createUserId() throws InputException
	{
		this.userId = nextUserId;
		isUserIDValid(userId);
		nextUserId++;
		return userId;
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
	public void setIni(String name) throws InputException {
		
		String[] nameParts = name.split(" ");
		String newIni = "";
		ini = nameParts[0].substring(0, 2) + nameParts[1].substring(0, 2);
		isIniValid(ini);
		this.ini = newIni;
	}
	
	public boolean isIniValid(String ini) throws InputException {

		if (ini.length() < 2 || ini.length() > 4) {
			throw new InputException(
					"These initials are invalid. Initials has to be between 2 and 4 characthers long.");
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

	//Consider to delete this method. You shouldn't be able to get a password..
	public String getPassword() {
		return password;
	}

	public String createPassword()
	{
		return null;
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
				throw new InputException("This does not contain characthers from 3 different groups.\n The groups are: 'a-z' , 'A-Z','0-9', {'.', '-', '_', '+', '!', '?', '='}");
			}

		}
		// If the password dosen't contain chars from atleast 3 groups.
		if (groupCount < 3) {
			return false;
		}

		return true;

	}


	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	

	public void addRole(String role) throws InputException{
		isRoleValid(role);
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).equals(role)) {
				return;
			}
		}
		this.roles.add(role);
	}
	
	public boolean isRoleValid(String role) throws InputException {

		String[] validRoles= new String[] {"Admin", "Pharmacist", "Foreman", "Operator"};
		for(int i=0;i<validRoles.length;i++){
			if(!role.equals(validRoles[i])){
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
	public boolean removeRole(String role){
		return this.roles.remove(role);
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
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
	}
	
	
	
}
