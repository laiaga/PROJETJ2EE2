package dao.interfaces;

import java.util.Collection;

import beans.Group;
import beans.Person;

public interface IPersonDao {
	// récupérer les groupes
	   Collection<Group> findAllGroups();

	   // récupérer les personnes
	   Collection<Person> findAllPersons(long groupId);

	   // lire une personne
	   Person findPerson(long id);

	   // modification ou ajout d'une nouvelle personne
	   void savePerson(Person p);

	   // modification ou ajout d'une nouvelle personne
	   void saveGroup(Group g);
}
