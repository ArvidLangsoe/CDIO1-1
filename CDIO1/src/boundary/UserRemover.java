package boundary;


import dal.IUserDAO;
import dal.IUserDAO.DALException;

public class UserRemover extends TUI {
	private IUserDAO data;
	
	public UserRemover(IUserDAO data){
		this.data=data;
	}
	
	public void deleteUser()
	{
		try
		{
			int id=getId();
			data.deleteUser(id);
			show("Succesfully deleted user with id: "+id);
		}
		catch(DALException e)
		{
			show(e.getMessage());
			
			//Try again
			deleteUser();
		}
	}
	
	public int getId(){
		
		show("Please enter a UserID: ");
		
		int id = getInt();
		
		while(id <11 || id > 99){
			id = getInt();
			
			show("Incorrect ID. Must be between 11 and 99.\n Please enter a valid UserID: ");
		}		
		return id;
	}
}
