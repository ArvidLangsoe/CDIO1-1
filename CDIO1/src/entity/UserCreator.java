package entity;

import java.util.List;

import dal.IUserDAO;

public class UserCreator {
	
	private IUserDAO data;
	
	public UserCreator(IUserDAO data){
		this.data=data;
	}
	
	
	public String createUser(String userName,String ini,String cpr,List<String> roles){
		
		
		return null;
	}
	
	
	public boolean isCprValid(String cpr){
		int[] validationNumber=new int[]{4,3,2,7,6,5,4,3,2};
		int[] cprArray =new int[10];
		
		//Save the cpr number in an array.
		for (int i=0;i<10;i++){
			cprArray[i]=Integer.parseInt(cpr.substring(i, i));
		}
		
		//Calculate the controle cifre:
		int sum=0;
		for(int i=0;i<9;i++){
			sum+=cprArray[i]*validationNumber[i];
		}
		
		int remainder= (sum%11);
		
		if(remainder==1){
			return false;
		}
		
		int controlCifre = 11-remainder;
		
		if(controlCifre!=cprArray[9]){
			return false;
		}
		
		return true;
	}
	
}
