package fr.projet.jee.dao.interfaces;

import fr.projet.jee.exceptions.PersonDoesNotExistException;

/**
 * 
 * @author Alexandre Leonardi
 * 
 *         Used to authenticate an user, recognizing him as one of the saved
 *         persons in the database
 *
 */
public interface IAuthenticator {
	/**
	 * Searchs for a given person and then tries to connect this person
	 * 
	 * @param email
	 *            the email of the person
	 * @param password
	 *            the supposed password of the person
	 * @return the person's id if she is found and her password was
	 *         corresponding to the one given as argument -1 otherwise
	 * @throws IllegalArgumentException
	 *             if the password given in argument doesn't match the person's
	 *             one
	 * @throws PersonDoesNotExistException
	 *             if no person with this email exists in base
	 */
	long authenticate(String email, String password) throws IllegalArgumentException, PersonDoesNotExistException;
}
