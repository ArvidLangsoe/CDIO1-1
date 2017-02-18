package dal;

import dto.UserDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class UserDAO implements IUserDAO {

	// Instance variables
	private ArrayList<UserDTO> users;

	/**
	 * Constructor. Constructs a user Data Access object which contains a single
	 * user, the super admin.
	 */
	public UserDAO(String fileName){
		if (fileName == null)
		{
			users = new ArrayList<UserDTO>();
		}
		else
		{
			try 
			{
				users = loadUsers();
			}
			catch(DALException e)
			{
				System.out.println(e.getMessage());
			}
		}
		
	}

	/**
	 * Returns the user with the specified id if he exists, throws DALException
	 * otherwise.
	 * 
	 * @param userId
	 *            The id of the user that you want.
	 * @return The user with the given Id
	 */
	public UserDTO getUser(int userId) throws DALException {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserId() == userId) {
				return users.get(i);
			}
		}
		throw new DALException("There exists no user with user id: " + userId);
	}

	/**
	 * Returns a list of users in the DAO.
	 * 
	 * @return a list of users.
	 */
	public List<UserDTO> getUserList() throws DALException {
		return users;
	}

	/**
	 * Adds a user to the DAO object.
	 * 
	 * @param user.
	 *            The user to be added.
	 */
	public void createUser(UserDTO user) throws DALException {
		users.add(user);
		saveUsers(users);
	}

	/**
	 * Deletes a user from the DAO object with the specified userId.
	 * 
	 * @param userId
	 *            The id of the user to be deleted.
	 */
	public void deleteUser(int userId) throws DALException 
	{
		for (int i = 0; i < users.size(); i++) 
		{
			if (users.get(i).getUserId() == userId) 
			{
				users.remove(i);
				saveUsers(users);
				return;
			}
		}
		throw new DALException("There exists no user with user id: " + userId);
	}

	/**
	 * Updates a user.
	 * 
	 * @param user
	 *            The updated user.
	 */
	public void updateUser(UserDTO user) throws DALException {
		saveUsers(users);
	}
	
	public ArrayList<UserDTO> loadUsers() throws DALException {
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		
		ObjectInputStream OIS = null;
		try 
		{
			FileInputStream fIS = new FileInputStream("userData.txt");
			OIS = new ObjectInputStream(fIS);
			Object inObj = OIS.readObject();
			if (inObj instanceof ArrayList<?>)
			{
				users = (ArrayList<UserDTO>) inObj;
			} 
			else 
			{
				throw new DALException("Wrong object in file");
			}
		} 
		catch (FileNotFoundException e) 
		{
			return users;
		} 
		catch (IOException e) 
		{
			throw new DALException("Error while reading disk!", e);
		} 
		catch (ClassNotFoundException e) 
		{
			throw new DALException("Error while reading file - Class not found!", e);
		} 
		finally 
		{
			if (OIS!=null){
				try 
				{
					OIS.close();
				} 
				catch (IOException e) 
				{
					throw new DALException("Error closing pObjectStream!", e);
				}
			}
		}
		return users;
	}
	
	public void saveUsers(ArrayList<UserDTO> users) throws DALException {
		ObjectOutputStream OOS =null;
		try 
		{
			FileOutputStream FOS = new FileOutputStream("userData.txt");
			OOS = new ObjectOutputStream(FOS);
			OOS.writeObject(users);
		} 
		catch (FileNotFoundException e) 
		{
			throw new DALException("Error locating file", e);
		} 
		catch (IOException e) 
		{
			throw new DALException("Error writing to disk", e);
		} 
		finally 
		{
			if (OOS!=null) 
			{
				try 
				{
					OOS.close();
				} 
				catch (IOException e) 
				{
					throw new DALException("Unable to close ObjectStream", e);
				}
			}
		}	
	}
}
