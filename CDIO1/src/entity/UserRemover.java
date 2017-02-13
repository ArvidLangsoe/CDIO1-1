package entity;

import dal.IUserDAO;
import dal.IUserDAO.DALException;

public class UserRemover{
	
	IUserDAO data;
	
	public UserRemover(IUserDAO data){
		this.data=data;
	}

	public void deleteUser(int userID) throws  DALException {
		data.deleteUser(userID);
	}

}
