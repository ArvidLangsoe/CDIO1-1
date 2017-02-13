package dto;

public class InputException extends Exception {
	private static final long serialVersionUID = 123;
	
	public InputException(String msg)
	{
		super(msg);
	}
}