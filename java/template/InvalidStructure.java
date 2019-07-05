package template;

public class InvalidStructure extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public InvalidStructure (String message){
        super(message);
    }

    public InvalidStructure (String message, Throwable cause){
        super(message,cause);
    }


}
