package com.neoris;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseException.
 *
 * @author marco.slehiman
 */
public class BaseException extends Throwable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9029437753083157549L;

	/**
	 * Instantiates a new base exception.
	 */
	public BaseException() {
	}

	/**
	 * Instantiates a new base exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new base exception.
	 *
	 * @param message the message
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new base exception.
	 *
	 * @param cause the cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}

}
