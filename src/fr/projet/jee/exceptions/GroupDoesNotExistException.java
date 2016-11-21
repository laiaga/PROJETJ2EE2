package fr.projet.jee.exceptions;

/**
 * Exception thrown when the user tries to find a group not known in base
 */
public class GroupDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 543541602675585260L;

	public GroupDoesNotExistException(String message) {
		super(message);
	}
}
