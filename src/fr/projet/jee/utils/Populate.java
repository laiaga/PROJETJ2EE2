package fr.projet.jee.utils;

import java.sql.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.projet.jee.beans.Group;
import fr.projet.jee.beans.Person;
import fr.projet.jee.dao.interfaces.IPersonDao;
import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;
import fr.projet.jee.utils.interfaces.IPopulate;

@Service
public class Populate implements IPopulate {
	@Autowired
	private IPersonDao dao;
	
	@Value("${surnames}")
	private String[] surnames;
	
	@Value("${names}")
	private String[] names;
	
	@Value("${groups}")
	private String[] groups;
	
	private Random random = new Random();
	
	@Override
	public void createGroups() throws InvalidGroupException {
		int idGroup = 1;
		for (String groupName : groups) {
			Group group = new Group();
			group.setGroupId(idGroup);
			group.setName(groupName);
			dao.saveGroup(group);
			idGroup++;
		}
	}

	@Override
	public void createGroups(int nbGroups) throws InvalidGroupException {
		if (nbGroups > groups.length) {
			throw new IllegalArgumentException("You can create a maximum of " + groups.length + " groups");
		} 
		
		for (int i=0 ; i<nbGroups ; i++) {
			Group group = new Group();
			group.setGroupId(i+1);
			group.setName(groups[i]);
			dao.saveGroup(group);
		}
		
	}

	@Override
	public void createPersons(int nbGroups) throws InvalidPersonException {
		int personId = 1;
		if (nbGroups > groups.length) {
			throw new IllegalArgumentException();
		} 
		for (String surname : surnames) {
			for (String name : names) {
				Person person = new Person();
				person.setPersonId(personId);
				person.setFirstName(surname);
				person.setLastName(name);
				person.setGroupId(personId % nbGroups + 1);
				person.setEmail(surname + "." + name + "@etu.univ-amu.fr");
				person.setBirthDate(Date.valueOf("1990-12-28"));
				person.setPassword(Integer.toString(random.nextInt()));
				person.setWebSite("website.fr");
				dao.savePerson(person);
				personId++;
			}
		}
		
	}

	@Override
	public void createPersons(int nbGroups, int nbPersons) throws InvalidPersonException {
		if (nbGroups > groups.length) {
			throw new IllegalArgumentException();
		} 
		for (int i=0 ; i<nbPersons ; i++) {
			Person person = new Person();
			person.setPersonId(i+1);
			person.setFirstName(surnames[random.nextInt(surnames.length)]);
			person.setLastName(names[random.nextInt(names.length)]);
			person.setGroupId((i+1) % nbGroups + 1);
			person.setEmail(person.getFirstName() + "." + person.getLastName() + "@etu.univ-amu.fr");
			person.setBirthDate(Date.valueOf("1990-12-28"));
			person.setPassword(Integer.toString(random.nextInt()));
			person.setWebSite("website.fr");
			dao.savePerson(person);
		}
		
	}
	
	@Override
	public void createAll() {
		try {
			createGroups();
			createPersons(groups.length);
		} catch (InvalidGroupException e) {
			e.printStackTrace();
		} catch (InvalidPersonException e) {
			e.printStackTrace();
		}
		
	}
}
