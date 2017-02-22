package functionLayer;

import dataAccessObjects.IUserDAO.DALException;
import dataTransferObjects.UserDTO;
import functionLayer.DataVerifier.WrongDataException;

public interface IDataVerifier {
	public void createUser(UserDTO user) throws WrongDataException;
	public UserDTO getUser(int userId) throws DALException;
	public void updateUser(UserDTO user) throws WrongDataException;
}

