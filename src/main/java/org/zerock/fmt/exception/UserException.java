package org.zerock.fmt.exception;

//회원관련 Exception 
public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserException(String message) {
		super(message);
	}//constructor 1
	
	public UserException(Exception e) {
		super(e);
	}//constructor 2
	
	
}//end class
