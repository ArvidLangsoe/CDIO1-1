package entity;

public interface IFun {
	String mangeData(String[] input) throws InputException;
	
	public class InputException extends Exception {
		private static final long serialVersionUID = 123;
		
		public InputException(String msg, Throwable e)
		{
			super(msg, e);
		}
		
		public InputException(String msg)
		{
			super(msg);
		}
	}
}
