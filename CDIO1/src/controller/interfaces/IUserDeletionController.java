package controller.interfaces;

import dal.IUserDAO.DALException;

public interface IUserDeletionController {

	// Get a userId from the boundary and deletes the corresponding userDTO.
	public void deleteUser() throws DALException;
	
}
