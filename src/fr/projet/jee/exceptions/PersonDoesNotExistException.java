package fr.projet.jee.exceptions;

/**
 * Exception thrown when the user tries to find a person not known in base
 */
public class PersonDoesNotExistException extends Exception {

	private static final long serialVersionUID = -8387005602731009424L;
	
	public PersonDoesNotExistException(String message) {
		super(message);
	}
}
