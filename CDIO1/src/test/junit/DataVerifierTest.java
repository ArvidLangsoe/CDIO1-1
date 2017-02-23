package test.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import dal.DataVerifier;
import dto.UserDTO;
import dto.Validator;
import dto.Validator.InputException;

public class DataVerifierTest {
	UserDTO user;
	DataVerifier data;
	@Before
	public void setUp() throws Exception {
		user = new UserDTO();
		data = new DataVerifier(null);
	}

	@After
	public void tearDown() throws Exception {
		user = null;
		data = null;
	}

	@Test
	public void testGenerateInitials() {
		try{
			Validator.validateInitials(data.generateInitials("hej"));
			Validator.validateInitials(data.generateInitials("hej med"));
			Validator.validateInitials(data.generateInitials("hej med dig"));
			Validator.validateInitials(data.generateInitials("hej med dig jeg"));
			Validator.validateInitials(data.generateInitials("hej med dig jeg er"));
			
		}
		catch(InputException e){
		System.out.println(e);
		fail();
		}
	}

	@Test
	public void testGeneratePassword() throws InputException {
		int tester=0;
		int times =1000000;
		for(int i=0;i<times;i++)
		{
			if(Validator.validatePassword(data.generatePassword()))
			{
				tester++;
			}
		}
		if(tester == times);
	}

}
