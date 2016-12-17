package fr.projet.jee.utils.interfaces;

import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;

public interface IPopulate {
	void createGroups() throws InvalidGroupException;
	void createGroups(int nbGroups) throws InvalidGroupException;
	void createPersons(int nbGroups) throws InvalidPersonException;
	void createPersons(int nbGroups, int nbPersons) throws InvalidPersonException;
	void createAll();
}
