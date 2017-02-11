package test.userCreation;

import boundary.ITUI;

public class TUIStub implements ITUI{

	private String[] userInputs;
	private int index;
	
	public TUIStub(String[] userInputs){
		this.userInputs=userInputs;
		index=0;
		
	}
	@Override
	public String getUserInput() {
		return userInputs[index++];
	}

	@Override
	public void showResponse(String response) {
		
	}

}
