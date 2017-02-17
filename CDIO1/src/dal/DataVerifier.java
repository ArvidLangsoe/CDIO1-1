package dal;

import dto.Validator;
import dto.Validator.InputException;

public class DataVerifier {
	
	
	public DataVerifier(IUserDAO data );
	
	public void createUser(UserDTO user) throws WrongDataException{
		//TODO: Check if userid is already taken, throw exceptions if not.
		
		//TODO: Add a generated password and intials to the user.
		
		//TODO: Validate all the variables in UserDTO, throw exceptions if not.
		
		//TODO: Add to the data.
	}


	public void updateUser(UserDTO user)  throws WrongDataException{
		//TODO: Validate all the variables in UserDTO, throw exceptions if not.
		
		//TODO: Add to the data.
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
}
