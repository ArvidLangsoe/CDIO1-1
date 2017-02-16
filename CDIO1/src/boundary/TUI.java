package boundary;

import java.util.Scanner;

import boundary.interfaces.UI;

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
			show("must be an integer. Try again");
			getInt();
		}
		return 0;

	}

	@Override
	public void show(String msg) {
		System.out.println(msg);

	}

}
