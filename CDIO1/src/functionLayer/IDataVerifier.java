package functionLayer;

import dataAccessObjects.IUserDAO.DALException;
import dataTransferObjects.UserDTO;

public interface IDataVerifier {
	
	/**
	 * Creates a user and adds it to the data.
	 * @param user The user to be created.
	 */
	public void createUser(UserDTO user) throws WrongDataException;
	
	/**
	 * Returns a user from the data.
	 * 
	 * @param userID
	 *            the user ID of the user that you want to get.
	 * @return The user to be returned.
	 */
	public UserDTO getUser(int userId) throws DALException;
	
	/**
	 * Updates the data file if the UserDTO contains valid data.
	 * 
	 * @param user
	 *            The user to update.
	 * @throws WrongDataException
	 *             The exception to be thrown if the UserDTO contains invalid
	 *             data.
	 */
	public void updateUser(UserDTO user) throws WrongDataException;
	
	/**
	 * The WrongDataException class is an exception inner class used in the
	 * IDataVerifier class. The exception is thrown if the userDTO contains invalid data.
	 * @author Group 22
	 *
	 */
	public class WrongDataException extends Exception {
		
		// The serial id making us able to identify the object when saved and
		// loaded.
		private static final long serialVersionUID = -7195625358850756914L;

		/**
		 * Constructor.
		 * 
		 * @param msg
		 *            The error message.
		 */
		public WrongDataException(String msg)
		{
			super(msg);
		}
	}
}



