package boundary;

import boundary.interfaces.IUserDeletetionBoundary;
import dal.IUserDAO;
import dal.IUserDAO.DALException;

public class UserDeletetionBoundary extends TUI implements IUserDeletetionBoundary{
	IUserDAO data;
	
	public UserDeletetionBoundary(IUserDAO data){
		this.data=data;
	}
	
	@Override
	public void deleteUser()
	{
		try
		{
			data.deleteUser(getId());
		}
		catch(DALException e)
		{
			show(e.getMessage());
			
			//Try again
			deleteUser();
		}
	}
	
	@Override
	public int getId(){
		
		show("Please enter a UserID: ");
		
		int id = getInt();
		
		while(11 <= id && id <= 99){
			id = getInt();
			
			show("Incorrect ID. Must be between 11 and 99.\n Please enter a valid UserID: ");
		}
		
		return id;
	}
	
	
}
