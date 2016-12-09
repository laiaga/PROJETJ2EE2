package fr.projet.jee.dao;

import org.springframework.beans.factory.annotation.Autowired;

import fr.projet.jee.beans.Person;
import fr.projet.jee.dao.interfaces.IPersonDao;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

public class Authenticator {
	@Autowired
	private static IPersonDao dao;

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
	static long authenticate(String email, String password)
			throws IllegalArgumentException, PersonDoesNotExistException {
		Person person = dao.findPersonByEmail(email);

		if (person != null) {
			if (person.getPassword().equals(password)) {
				return person.getPersonId();
			} else {
				throw new IllegalArgumentException("Invalid password.");
			}
		} else {
			return -1;
		}
	}
}
