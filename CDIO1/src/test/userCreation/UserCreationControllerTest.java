package test.userCreation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import boundary.ITUI;
import controller.UserCreationController;
import entity.userCreation.IUserCreation;

public class UserCreationControllerTest {
	private ITUI tui;
	private IUserCreation userCreator;
	private UserCreationController con;

	@Before
	public void setUp() throws Exception {
		String[] validRoles = new String[] { "Role1,Role2,Role3,Role4" };
		userCreator = new UserCreatorStub(validRoles);
	}

	@After
	public void tearDown() throws Exception {
		tui = null;
		userCreator = null;
		con = null;
	}

	@Test
	public void testStartUserCreationNoExceptions() {
		String[] expectedAnswers = new String[] { "User creation has begun.",
				"Please enter the ID of the new user. It has to be between 11 and 99",
				"Please enter a username. The username has to have a length between 2 and 20.",
				"Please enter initials. The intials have to contain 2-4 letters.",
				"Please enter the users cpr number." };
		String[] userInputs = new String[] { "10", "name", "ini", "cpr", "1", "5" };

		tui = new TUIStub(userInputs);
		TUIStub tuiTest = (TUIStub) tui;
		con = new UserCreationController(userCreator, tui);
		con.startUserCreation();

		for (int i = 0; i < expectedAnswers.length; i++) {
			assertEquals("The messages: '" + expectedAnswers[i] + "' and '" + tuiTest.responses.get(i)
					+ " were not the same.", expectedAnswers[i], tuiTest.responses.get(i));
		}
	}

	@Test
	public void testStartUserCreationWithExceptions() {

	}

}
