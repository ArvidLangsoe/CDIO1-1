package functionLayer;

import dataAccessObjects.IUserDAO.DALException;
import dataTransferObjects.UserDTO;

public interface IDataVerifier {
	public void createUser(UserDTO user) throws WrongDataException;
	public UserDTO getUser(int userId) throws DALException;
	public void updateUser(UserDTO user) throws WrongDataException;
	
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



