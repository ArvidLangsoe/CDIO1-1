package boundary;


import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dto.Validator;
import dto.Validator.InputException;

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
		catch(InputException e)
		{
			show("Incorrect ID. Must be between 11 and 99.\n ");
			
			//Try again
			deleteUser();
		}
	}
	
	public int getId() throws InputException{
		
		show("Please enter a UserID: ");
		
		int id = getInt();
		
		Validator.validateUserID(id);

		return id;
	}
}
