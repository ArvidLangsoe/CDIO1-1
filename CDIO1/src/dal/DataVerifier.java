package dal;
import dto.Validator;
import dto.Validator.InputException;

import org.omg.PortableServer.IdAssignmentPolicyValue;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dto.UserDTO;

public class DataVerifier implements IDataVerifier
{

	private IUserDAO data;

	public DataVerifier(IUserDAO data) 
	{
		this.data =data;
	}


	public void createUser(UserDTO user) throws WrongDataException{
		
		try {//TODO: Check if userid is already taken, throw exceptions if not.	✓
			//if it returns an exception then the userID is not used
			data.getUser(user.getUserId());
			
			//throws an exception if the userID is taken
			throw new WrongDataException("This userID is already taken: "+user.getUserId());
			
		} catch (DALException userID) {

			//sets a newly generated password
			user.setPassword(generatePassword());
			
			//sets initials acording to their username
			user.setIni(generateInitials(user.getUserName()));
			
			//validates if alle the variables are legal
			try{
				validate(user);
			}catch(WrongDataException validate){
				throw new WrongDataException(validate.getMessage());
			}
			
			//creates the user.
			try{
				data.createUser(user);
			}catch (DALException e){
				throw new WrongDataException(e.getMessage());
			}

		}
	}
	public UserDTO getUser(int userId) throws DALException
	{
			return data.getUser(userId);	
	}

	public void updateUser(UserDTO user) throws WrongDataException{

		//validates if all the new data is legal
		try{
			validate(user);
		}catch(WrongDataException validate){
			throw new WrongDataException(validate.getMessage());
		}

		//Updates the user
		try{
			data.updateUser(user);
		}catch (DALException e){
			throw new WrongDataException(e.getMessage());
		}

	}

	private void validate(UserDTO user) throws WrongDataException
	{
		//Validates if the username is legal
		try {
			Validator.validateUsername(user.getUserName());
		} catch (InputException e) {
			throw new WrongDataException(e.getMessage());
		}
		
		//Validates if the CPR is legal
		try {
			Validator.validateCPR(user.getCpr());
		} catch (InputException e) {
			throw new WrongDataException(e.getMessage());
		}
//				try {
//					Validator.validateRole(user.get());
//				} catch (InputException e) {
//					throw new WrongDataException(e.getMessage());
//				}
		
		//Validates if the UserID is legal
		try {
			Validator.validateUserID(user.getUserId());
		} catch (InputException e) {
			throw new WrongDataException(e.getMessage());
		}
		
		//Validates if the password is legal
		try {
			Validator.validatePassword(user.getPassword());
		} catch (InputException e) {
			throw new WrongDataException(e.getMessage());
		}
		
		//Validates if the user initials is legal
		try {
			Validator.validateInitials(user.getIni());
		} catch (InputException e) {
			throw new WrongDataException(e.getMessage());
		}

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

	public String generatePassword() {
		String password = "";
		int passLength = 8;
		boolean passwordValid = false;
		while (!passwordValid) {
			password = "";
			for (int i = 0; i < passLength; i++) 
			{
				char newCharacther;
				int randGroup = (int) (Math.random() * 100);

				// Add a special characther
				if (randGroup < 5) 
				{
					String specialCharacthers = ".-_+!?=";
					int rand = (int) (Math.random() * specialCharacthers.length());
					newCharacther = specialCharacthers.charAt(rand);
				}
				// Add a small letter.
				else if (randGroup < 30) 
				{
					int rand = (int) (Math.random() * (122 - 97 + 1) + 97);
					newCharacther = (char) rand;
				}
				// Add a large letter.
				else if (randGroup < 55) 
				{
					int rand = (int) (Math.random() * (90 - 65 + 1) + 65);
					newCharacther = (char) rand;
				}
				// Add a number.
				else 
				{
					int rand = (int) (Math.random() * (57 - 48 + 1) + 48);
					newCharacther = (char) rand;
				}

				password += newCharacther + "";
			}
			try 
			{
				Validator.validatePassword(password);
				passwordValid = true;
			} 
			catch (InputException e) 
			{

			}
		}
		return password;
	}

	public class WrongDataException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7195625358850756914L;

		public WrongDataException(String msg)
		{
			super(msg);
		}
	}
}

