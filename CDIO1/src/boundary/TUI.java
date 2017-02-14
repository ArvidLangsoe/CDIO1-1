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
		scan.close();
		return input;
	}

	@Override
	public int getInt() {
		try{
			String inputInt =getString();
			return Integer.parseInt(inputInt);


		}
		catch(Exception e){
			show("must be an integer");
			getInt();
		}
		return 0;

	}

	@Override
	public void show(String msg) {
		System.out.println(msg);

	}

}