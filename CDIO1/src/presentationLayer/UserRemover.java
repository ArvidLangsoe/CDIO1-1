package presentationLayer;


import dal.IUserDAO;
import dal.IUserDAO.DALException;

/**
 * The class UserRemover extends TUI.
 * The responsibility of this class is to carry out the tasks of removing a user from the data.
 * @author Group 22
 *
 */
public class UserRemover extends TUI {
	
	//Instance variables.
	private IUserDAO data;
	
	/**
	 * Constructor.
	 * @param data The data access object to use.
	 */
	public UserRemover(IUserDAO data){
		this.data=data;
	}
	
	/**
	 * Deletes a user (UserDTO) by asking the user Administrator for user ID of the user to be removed from the data.
	 */
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
	
	/**
	 * Asks the user administrator for the user ID of the user (UserDTO) to be removed from the data.
	 * @return The user ID to be deleted.
	 */
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
