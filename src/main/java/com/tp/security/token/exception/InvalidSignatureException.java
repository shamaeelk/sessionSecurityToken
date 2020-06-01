/**
 * 
 */
package com.tp.security.token.exception;

/**
 * Invalid signature exception class.
 * 
 * @author ii00083746
 *
 */
public class InvalidSignatureException extends Exception {
	
	private String message;

	public InvalidSignatureException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
