package template;

public class InvalidArgument extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidArgument(String message){
        super(message);
    }

    public InvalidArgument(String message, Throwable cause){
        super(message,cause);
    }

}