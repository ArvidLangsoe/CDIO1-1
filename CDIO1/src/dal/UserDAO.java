package dal;

import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Denne klasse skal snakke med databasen (CRUD operations).
 * */

public class UserDAO implements IUserDAO {

	// Instance variables
	private ArrayList<UserDTO> users;

	/**
	 * Constructor. Constructs a user Data Access object which contains a single
	 * user, the super admin.
	 */
	public UserDAO() {
		users = new ArrayList<UserDTO>();

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
	public List<UserDTO> getUserList() {
		return users;
	}

	/**
	 * Adds a user to the DAO object.
	 * 
	 * @param user.
	 *            The user to be added.
	 */
	public void createUser(UserDTO user) {
		users.add(user);
	}

	/**
	 * Deletes a user from the DAO object with the specified userId. You cannot
	 * delete the System Admin.
	 * 
	 * @param userId
	 *            The id of the user to be deleted.
	 */
	public void deleteUser(int userId) throws DALException {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserId() == userId) {
				if (!users.get(i).hasRole("System Admin")) {
					users.remove(i);
					return;
				} else {
					throw new DALException("You cannot delete the System Admin user.");
				}
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
	public void updateUser(UserDTO user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserId() == user.getUserId()) {
				users.remove(i);
				users.add(user);
			}
		}
	}
}
