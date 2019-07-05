package template;

public class IllegalVariable extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IllegalVariable ( String message ) { super ( message ); }

	public IllegalVariable ( String message, Throwable cause ) { super ( message,cause ); }


}
