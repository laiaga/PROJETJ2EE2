package fr.projet.jee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.jee.beans.Person;
import fr.projet.jee.dao.interfaces.IAuthenticator;
import fr.projet.jee.dao.interfaces.IPersonDao;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

@Service
class Authenticator implements IAuthenticator{
	@Autowired
	private IPersonDao dao;

	@Override
	public long authenticate(String email, String password)
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
