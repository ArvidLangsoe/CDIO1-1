package dal;

import java.util.List;

import dataTransferObjects.UserDTO;

public interface IUserDAO 
{
	//Methods in the interface.
	public UserDTO getUser(int userId) throws DALException;
	public List<UserDTO> getUserList() throws DALException;
	public void createUser(UserDTO user) throws DALException;
	public void updateUser(UserDTO user) throws DALException;
	public void deleteUser(int userId) throws DALException;
	
	//Inner class.
	public class DALException extends Exception 
	{
		private static final long serialVersionUID = 7355418246336739229L;
		
		public DALException(String msg, Throwable e) 
		{
			super(msg, e);
		}
		
		public DALException(String msg) 
		{
			super(msg);
		}
	}
}
