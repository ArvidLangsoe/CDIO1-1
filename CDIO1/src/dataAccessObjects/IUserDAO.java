package dataAccessObjects;

import java.util.List;

import dataTransferObjects.UserDTO;

/**
 * Interface IUserDAO has the responsibility of making sure that 
 * the UserDAO class has the correct methods to access and update the data in the system.
 * @author Group 22
 *
 */
public interface IUserDAO 
{
	//Methods in the interface.
	/**
	 * Returns a user from the data with the given user ID.
	 * @param userId The ID of the user which needs to be returned.
	 * @return The user with the given ID:
	 * @throws DALException The exception to be thrown if something goes wrong in the data.
	 */
	public UserDTO getUser(int userId) throws DALException;
	
	/**
	 * Returns a list of all users in the data.
	 * @return The list of users.
	 * @throws DALException The exception to be thrown if something goes wrong in the data.
	 */
	public List<UserDTO> getUserList() throws DALException;
	
	/**
	 * Adds and saves a new user to the data.
	 * @param user The user to be added.
	 * @throws DALException The exception to be thrown if something goes wrong in the data.
	 */
	public void createUser(UserDTO user) throws DALException;
	
	/**
	 * Updates a user and save the update in the data.
	 * @param user The user to be updated.
	 * @throws DALException The exception to be thrown if something goes wrong in the data.
	 */
	public void updateUser(UserDTO user) throws DALException;
	
	/**
	 * Deletes and removes the user from the data.
	 * @param userId The ID of the user which needs to be deleted.
	 * @throws DALException The exception to be thrown if something goes wrong in the data.
	 */
	public void deleteUser(int userId) throws DALException;
	
	/**
	 * DALException is an Inner class which extends Exception. The exception needs to be thown if something goes wrong in the data.
	 * @author Group 22
	 *
	 */
	public class DALException extends Exception 
	{
		private static final long serialVersionUID = 7355418246336739229L;
		
		/**
		 * Constructor
		 * @param msg The error message to pass on.
		 * @param e The exception to pass on.
		 */
		public DALException(String msg, Throwable e) 
		{
			super(msg, e);
		}
		
		/**
		 * Constrcutor
		 * @param msg The error message to pass on.
		 */
		public DALException(String msg) 
		{
			super(msg);
		}
	}
}
