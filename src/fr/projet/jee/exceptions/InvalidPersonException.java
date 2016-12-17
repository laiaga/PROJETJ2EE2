package fr.projet.jee.exceptions;

/**
 * Exception thrown when the user tries to save an invalid person (e.g. id negative or null)
 *
 */
public class InvalidPersonException extends Exception {

	private static final long serialVersionUID = -7611276048965976920L;

	public InvalidPersonException(String message) {
		super(message);
	}
}
