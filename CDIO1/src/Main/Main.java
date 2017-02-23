package Main;

import dataAccessObjects.IUserDAO;
import dataAccessObjects.UserDAO;
import presentationLayer.MenuAdministrator;
import presentationLayer.UserCreator;
import presentationLayer.UserEditor;
import presentationLayer.UserRemover;
import presentationLayer.UserViewer;

/**
 * The Main class has the responsibility of intializing the objects used by the program and afterwards call the start method.
 * @author Group 22
 *
 */
public class Main {

	/**
	 * The main method initializes the objects needed to run the user 
	 * Administration program and afterwards starts the program.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
			IUserDAO data = new UserDAO(null);
			IDataVerifier func= new DataVerifier(data);
			UserViewer view = new UserViewer(data);
			UserRemover delete = new UserRemover(data);
			UserCreator create = new UserCreator(func);
			UserEditor edit = new UserEditor(func);
			MenuAdministrator menu = new MenuAdministrator(create, delete, view, edit);
			menu.start();
	}

}
