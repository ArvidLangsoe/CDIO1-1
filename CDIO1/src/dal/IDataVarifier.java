package dal;

import dal.DataVerifier.WrongDataException;
import dal.IUserDAO.DALException;
import dto.UserDTO;

public interface IDataVarifier {
	public void createUser(UserDTO user) throws WrongDataException;
	public UserDTO getUser(int userId) throws DALException;
	public void updateUser(UserDTO user) throws WrongDataException;
}
