package fr.projet.jee.dao.interfaces;

import java.util.Collection;

import fr.projet.jee.beans.Group;
import fr.projet.jee.beans.Person;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

public interface IPersonDao {
	/**
	 * r�cup�rer les groupes
	 * 
	 * @return l'ensemble des groupes existant
	 */
	Collection<Group> findAllGroups();

	/**
	 * r�cup�rer les personnes appartenant � un groupe
	 * 
	 * @param groupId le groupe dont on souaite trouver les membres
	 * @return les personnes appartenant � ce groupe
	 */
	Collection<Person> findAllPersons(long groupId);
	
	/**
	 * r�cup�rer un groupe donn�
	 * @param id l'id du groupe
	 * @return le groupe en question
	 */
	Group findGroup(long id);

	/**
	 * r�cup�rer une personne donn�e
	 * 
	 * @param id l'id de la personne 
	 * @return la personne en question
	 * @throws PersonDoesNotExistException thrown when a person with a PersonId=id doesn't exist
	 */
	Person findPerson(long id) throws PersonDoesNotExistException;

	/**
	 * modifier ou ajouter une personne
	 * 
	 * @param p la personne � modifier si elle est pr�sente dans la base, ajouter sinon
	 */
	void savePerson(Person p);

	/**
	 * sauvegarder un groupe en base
	 * 
	 * @param g le groupe � sauvegarder
	 */
	void saveGroup(Group g);

	/**
	 * supprimer le contenu de toutes les tables
	 */
	void clearTables();

	/**
	 * vider la table Persons - la version permettant de vider les groupes n'est
	 * pas expos�e car elle ne peut �tre utilis�e qu'en des endroits pr�cis pour
	 * ne pas briser la foreign key liant un groupe � des utilisateurs
	 */
	void clearPersons();
	
	/**
	 * Checks if a person exists
	 * @param id the person to be checked
	 * @return true if and only if the person exists
	 * 			false otherwise
	 */
	boolean personExists(long id);
	
	/**
	 * Checks if a group exists
	 * @param id the group to be checked
	 * @return true if and only if the group exists
	 * 			false otherwise
	 */
	boolean groupExists(long id);
	
	/**
	 * Deletes a given person
	 * @param id the id of the person to be deleted
	 */
	void deletePerson(long id);
}
