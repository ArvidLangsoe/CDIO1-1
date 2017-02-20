package Main;

import dataAccessObjects.IUserDAO;
import dataAccessObjects.UserDAO;
import presentationLayer.MenuAdministrator;
import presentationLayer.UserCreator;
import presentationLayer.UserEditor;
import presentationLayer.UserRemover;
import presentationLayer.UserViewer;

/**
 * The Main class is the where the program starts.
 * @author Group 22
 *
 */
public class Main {

	/**
	 * The main method initializes the objects needed to run the user 
	 * Administration program and afterwards starts the program.
	 * @param args is not used.
	 */
	public static void main(String[] args) 
	{
		IUserDAO data = new UserDAO(null);
		UserViewer view = new UserViewer(data);
		UserRemover delete = new UserRemover(data);
		UserCreator create = new UserCreator(data);
		UserEditor edit = new UserEditor(data);
		MenuAdministrator menu = new MenuAdministrator(create, delete, view, edit);
		menu.start();
	}

}
