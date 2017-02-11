package entity;


import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dto.UserDTO;
import entity.IFun.InputException;

public class UserCreator {

	private IUserDAO data;
	private UserDTO newUser;

	public UserCreator(IUserDAO data) {
		this.data = data;
	}

	public void startUserCreation() {
		newUser = new UserDTO();
	}

	public void setUserID(int userID) throws InputException {

		try {
			UserDTO user = data.getUser(userID);
			if (user != null) {
				throw new InputException("This user id is already taken.");
			}

		} catch (DALException e) {
			
		}

		newUser.setUserId(userID);
	}

	public void setUserName(String userName) throws InputException {
		newUser.setUserName(userName);
	}

	public void setIni(String ini) throws InputException {
		newUser.setIni(ini);
	}

	public void setCpr(String cpr) throws InputException {
		newUser.setCpr(cpr);
	}

	public void addRole(String role) throws InputException {
		newUser.addRole(role);
	}
	
	public String[] getValidroles(){
		return newUser.validRoles;
	}

	public String endUserCreation() throws InputException,DALException {

		newUser.setPassword(newUser.generateValidPassword());

		data.createUser(newUser);

		return "Succes.";
	}

	

}
