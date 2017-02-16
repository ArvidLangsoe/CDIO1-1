package Main;

import boundary.*;
import dal.*;

public class Main {

	public static void main(String[] args) {
			IUserDAO data = new UserDAO(null);
			UserViewer userViewer = new UserViewer(data);
			UserDeletetionBoundary userDeletion = new UserDeletetionBoundary(data);
			UserCreationBoundary UserCreationBoundary = new UserCreationBoundary(data);
			
	}

}
