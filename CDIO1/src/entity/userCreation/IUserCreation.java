package entity.userCreation;

import dal.IUserDAO.DALException;

import entity.IFun.InputException;

public interface IUserCreation {
	public void startUserCreation();

	public void setUserID(int userID) throws InputException;

	public void setUserName(String userName) throws InputException;

	public void setIni(String ini) throws InputException;

	public void setCpr(String cpr) throws InputException;

	public void addRole(String role) throws InputException;

	public String[] getValidroles();

	public String endUserCreation() throws InputException, DALException;

}
