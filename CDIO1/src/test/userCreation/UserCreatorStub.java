package test.userCreation;

import dal.IUserDAO.DALException;
import entity.IFun.InputException;
import entity.userCreation.IUserCreation;

public class UserCreatorStub implements IUserCreation {
	private String[] validRoles;
	
	public UserCreatorStub(String[] validRoles){
		this.validRoles=validRoles;
		
	}
	
	@Override
	public void startUserCreation() {
		
		
	}

	@Override
	public void setUserID(int userID) throws InputException {
		if(userID>1000){
			new InputException("UserIDTest");
		}
		
	}

	@Override
	public void setUserName(String userName) throws InputException {
		if(userName.length()>20){
			new InputException("UserNameTest");
		}
	}

	@Override
	public void setIni(String ini) throws InputException {
		
		if(ini.length()>20){
			new InputException("IniTest");
		}
	}

	@Override
	public void setCpr(String cpr) throws InputException {
		// TODO Auto-generated method stub
		if(cpr.length()>20){
			new InputException("CprTest");
		}
	}

	@Override
	public void addRole(String role) throws InputException {
				return;
	}

	@Override
	public String[] getValidroles() {
		// TODO Auto-generated method stub
		return validRoles;
	}

	@Override
	public String endUserCreation() throws InputException, DALException {
		// TODO This method need to be tested when it has been completely used in the Creation Controller.
		return null;
	}

	
	
}
