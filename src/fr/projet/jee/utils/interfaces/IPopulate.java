package fr.projet.jee.utils.interfaces;

import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;

/**
 * 
 * @author Alexandre Leonardi
 * 
 *         Methods to fill the database with randomly generated persons and
 *         groups cf utils.properties for the list of person names and group
 *         names
 *
 */
public interface IPopulate {

	/**
	 * Creates as much groups as possible given the group names given in the
	 * property file
	 * 
	 * @throws InvalidGroupException
	 */
	void createGroups() throws InvalidGroupException;

	/**
	 * Creates a given number of groupes which must be less or equal to the
	 * number of group names in the property file
	 * 
	 * @param nbGroups
	 *            the number of groups to be created
	 * @throws InvalidGroupException
	 */
	void createGroups(int nbGroups) throws InvalidGroupException;

	/**
	 * Creates as much persons as possible without creating 2 with the same name
	 * (according to the number of surnames and last names avaiable in the
	 * property file) and sorts them among a given number of groups
	 * 
	 * @param nbGroups
	 *            the number of groups among which we sort the persons
	 * @throws InvalidPersonException
	 */
	void createPersons(int nbGroups) throws InvalidPersonException;

	/**
	 * Creates a a given number of persons, which must be less or equal to the
	 * maximum authorized (see {@link #createPersons(int)}) and sorts them
	 * through groups
	 * 
	 * @param nbGroups
	 *            the number of groups among which we want to sort the persons
	 * @param nbPersons
	 *            the number of persons we want to create
	 * @throws InvalidPersonException
	 */
	void createPersons(int nbGroups, int nbPersons) throws InvalidPersonException;

	/**
	 * Creates the maximum number of groups (see {@link #createGroups()}) and
	 * the maximum number of persons (see {@link #createPersons(int)}) and sorts
	 * them through all the groups
	 */
	void createAll();
}
