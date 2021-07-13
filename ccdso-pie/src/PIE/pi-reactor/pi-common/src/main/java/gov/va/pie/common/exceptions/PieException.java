package gov.va.pie.common.exceptions;

public class PieException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PieException() {
	}

	public PieException(String message) {
		super(message);
	}

	public PieException(Throwable cause) {
		super(cause);
	}

	public PieException(String message, Throwable cause) {
		super(message, cause);
	}

	public PieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
