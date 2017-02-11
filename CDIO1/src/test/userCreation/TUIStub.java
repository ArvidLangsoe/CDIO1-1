package test.userCreation;

import java.util.ArrayList;

import boundary.ITUI;

public class TUIStub implements ITUI{

	private String[] userInputs;
	private int index;
	
	
	public ArrayList<String> responses;
	
	public TUIStub(String[] userInputs){
		this.userInputs=userInputs;
		index=0;
		responses=new ArrayList<String>();
		
	}
	@Override
	public String getUserInput() {
		return userInputs[index++];
	}

	@Override
	public void showResponse(String response) {
		responses.add(response);
	}

}
