package fr.projet.jee.exceptions;

/**
 * Exception thrown when the user tries to save an invalid group (e.g. id negative or null)
 *
 */
public class InvalidGroupException extends Exception {

	private static final long serialVersionUID = 4232926032963348441L;

	public InvalidGroupException(String message) {
		super(message);
	}
}
