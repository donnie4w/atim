package com.tim.exception;

public class TimException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;
	private Throwable cause;

	/**
	 * Constructs a JSONException with an explanatory message.
	 * 
	 * @param message
	 *            Detail about the reason for the exception.
	 */
	public TimException(String message) {
		super(message);
	}

	public TimException(Throwable t) {
		super(t.getMessage());
		this.cause = t;
	}

	public Throwable getCause() {
		return this.cause;
	}
}
