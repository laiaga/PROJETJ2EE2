package fr.projet.jee.dao.interfaces;

import java.util.Collection;

import fr.projet.jee.beans.Group;
import fr.projet.jee.beans.Person;
import fr.projet.jee.exceptions.GroupDoesNotExistException;
import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

public interface IPersonDao {
	/**
	 * récupérer les groupes
	 * 
	 * @return l'ensemble des groupes existant
	 */
	Collection<Group> findAllGroups();

	/**
	 * récupérer les personnes appartenant à un groupe
	 * 
	 * @param groupId
	 *            le groupe dont on souaite trouver les membres
	 * @return les personnes appartenant à ce groupe
	 */
	Collection<Person> findAllPersons(long groupId);

	/**
	 * Finds a given group
	 * @param id the id of the group to be found
	 * @return the group
	 * @throws GroupDoesNotExistException if no group with such id exists
	 */
	Group findGroup(long id) throws GroupDoesNotExistException;

	/**
	 * récupérer une personne donnée
	 * 
	 * @param id
	 *            l'id de la personne
	 * @return la personne en question
	 * @throws PersonDoesNotExistException
	 *             thrown when a person with a PersonId=id doesn't exist
	 */
	Person findPerson(long id) throws PersonDoesNotExistException;

	/**
	 * Saves or, if already present, modifies a person
	 * 
	 * @param p the person to be modified or saved in base
	 * @throws InvalidPersonException 
	 */
	void savePerson(Person p) throws InvalidPersonException;

	/**
	 * Saves a group in table GROUPS 
	 * 
	 * @param g the group to be saved in base
	 * @throws InvalidGroupException 
	 */
	void saveGroup(Group g) throws InvalidGroupException;

	/**
	 * Clears all tables in a controlled way that avoids integrity issues
	 */
	void clearTables();

	/**
	 * Clears table PERSONS - the version to clear GROUPS is not exposed because
	 * it is dangerous for the base's integrity
	 */
	void clearPersons();

	/**
	 * Checks if a person exists
	 * 
	 * @param id
	 *            the person to be checked
	 * @return true if and only if the person exists false otherwise
	 */
	boolean personExists(long id);

	/**
	 * Checks if a group exists
	 * 
	 * @param id
	 *            the group to be checked
	 * @return true if and only if the group exists false otherwise
	 */
	boolean groupExists(long id);

	/**
	 * Deletes a given person
	 * 
	 * @param id
	 *            the id of the person to be deleted
	 * @throws PersonDoesNotExistException
	 *             if trying to delete a person that is not present in base
	 */
	void deletePerson(long id) throws PersonDoesNotExistException;
}
