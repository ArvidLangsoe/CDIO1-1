package presentationClasses;

import java.util.Scanner;

public class TUI implements UI
{
	private Scanner scan;
	public TUI(){
		scan =new Scanner(System.in);	
	}
	public String getString() {
		String input = scan.nextLine();
		return input;
	}

	@Override
	public int getInt() {
		try{
			String inputInt =getString();
			return Integer.parseInt(inputInt);


		}
		catch(NumberFormatException e){
			show("You must input an integer. Try again");
			return getInt();
		}

	}

	@Override
	public void show(String msg) {
		System.out.println(msg);

	}

}
