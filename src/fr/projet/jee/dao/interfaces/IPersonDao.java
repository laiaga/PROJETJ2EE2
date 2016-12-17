package fr.projet.jee.dao.interfaces;

import java.util.List;

import fr.projet.jee.beans.Group;
import fr.projet.jee.beans.Person;
import fr.projet.jee.exceptions.GroupDoesNotExistException;
import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

public interface IPersonDao {
	/**
	 * Finds all groups in base
	 * 
	 * @return a list of all groups in base
	 */
	List<Group> findAllGroups();

	/**
	 * Finds all persons that are part of a given group
	 * 
	 * @param groupId
	 *            the group of which we want the members
	 * @return a list the members of this group
	 */
	List<Person> findAllPersons(long groupId);

	/**
	 * Finds a given group
	 * 
	 * @param id
	 *            the id of the group to be found
	 * @return the group
	 * @throws GroupDoesNotExistException
	 *             if no group with such id exists
	 */
	Group findGroup(long id) throws GroupDoesNotExistException;

	/**
	 * Finds a given person based on his id
	 * 
	 * @param id
	 *            the person's id
	 * @return the searched person
	 * @throws PersonDoesNotExistException
	 *             thrown when a person with a PersonId=id doesn't exist
	 */
	Person findPerson(long id) throws PersonDoesNotExistException;

	/**
	 * Finds a given person based on his email address
	 * 
	 * @param email
	 *            the person's email address
	 * @return the searched person
	 * @throws PersonDoesNotExistException
	 *             thrown when a person with a Email=email doesn't exist
	 */
	Person findPersonByEmail(String email) throws PersonDoesNotExistException;

	/**
	 * Saves or, if already present, modifies a person
	 * 
	 * @param p
	 *            the person to be modified or saved in base
	 * @throws InvalidPersonException if the person does not fill required fields, or with unauthorized values
	 */
	void savePerson(Person p) throws InvalidPersonException;

	/**
	 * Saves a group in table GROUPS
	 * 
	 * @param g
	 *            the group to be saved in base
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
