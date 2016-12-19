package fr.projet.jee.utils.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.projet.jee.beans.Group;
import fr.projet.jee.beans.Person;
import fr.projet.jee.dao.interfaces.IPersonDao;
import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;
import fr.projet.jee.utils.interfaces.IPopulate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
@Component
public class PopulateTest {

	@Autowired
	private IPopulate populate;
	
	@Autowired
	private IPersonDao dao;
	
	@Value("${groups}")
	private String[] groups;
	
	@Value("${surnames}")
	private String[] surnames;
	
	@Value("${names}")
	private String[] names;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		dao.clearTables();
	}

	@Test
	public void testCreateGroups() {
		try {
			assertTrue(dao.findAllGroups().isEmpty());
			populate.createGroups();
			List<Group> res = dao.findAllGroups();
			List<String> names = new ArrayList<>();
			
			for (Group group : res) {
				names.add(group.getName());
			}
			
			for (int i=0 ; i<groups.length ; i++) {
				assertTrue(names.contains(groups[i]));
			}
		} catch (InvalidGroupException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateGroupsInt() {
		try {
			assertTrue(dao.findAllGroups().isEmpty());
			populate.createGroups(3);
			List<Group> res = dao.findAllGroups();
			List<String> actualNames = new ArrayList<>();
			List<String> possibleNames = Arrays.asList(groups);
			
			for (Group group : res) {
				actualNames.add(group.getName());
			}
			
			for (String name : actualNames) {
				assertTrue(possibleNames.contains(name));
			}
		} catch (InvalidGroupException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateGroupsIntIllegalArgumentException() {
		try {
			thrown.expect(IllegalArgumentException.class);
			populate.createGroups(groups.length+1);
		} catch (InvalidGroupException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreatePersons() {
		assertTrue(dao.findAllGroups().isEmpty());
		try {
			populate.createGroups();
			populate.createPersons(groups.length);
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}
		List<Group> groupList = dao.findAllGroups();
		List<Person> personList = new ArrayList<>();
		for (Group group : groupList) {
			personList.addAll(dao.findAllPersons(group.getGroupId()));
		}
		assertEquals(names.length*surnames.length, personList.size());
	}
	
	@Test
	public void testCreatePersonsIllegalArgumentException() {
		try {
			thrown.expect(IllegalArgumentException.class);
			populate.createPersons(groups.length+1);
		} catch (InvalidPersonException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreatePersonsInt() {
		assertTrue(dao.findAllGroups().isEmpty());
		try {
			populate.createGroups();
			populate.createPersons(groups.length, 10);
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}
		List<Group> groupList = dao.findAllGroups();
		List<Person> personList = new ArrayList<>();
		for (Group group : groupList) {
			personList.addAll(dao.findAllPersons(group.getGroupId()));
		}
		assertEquals(10, personList.size());
	}
	
	@Test
	public void testCreatePersonsIntIllegalArgumentException() {
		try {
			thrown.expect(IllegalArgumentException.class);
			populate.createPersons(groups.length+1,10);
		} catch (InvalidPersonException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateAll() {
		assertTrue(dao.findAllGroups().isEmpty());
		populate.createAll();
		List<Group> groupList = dao.findAllGroups();
		List<Person> personList = new ArrayList<>();
		for (Group group : groupList) {
			personList.addAll(dao.findAllPersons(group.getGroupId()));
		}
		assertEquals(groups.length,groupList.size());
		assertEquals(names.length*surnames.length, personList.size());
	}

}
