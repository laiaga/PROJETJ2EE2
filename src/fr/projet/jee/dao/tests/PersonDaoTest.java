package fr.projet.jee.dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.projet.jee.beans.Group;
import fr.projet.jee.beans.Person;
import fr.projet.jee.dao.interfaces.IPersonDao;
import fr.projet.jee.exceptions.GroupDoesNotExistException;
import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
public class PersonDaoTest {
	@Autowired
	private IPersonDao dao;

	private Group g1, g2;
	private Person p1, p2;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		dao.clearTables();

		g1 = new Group();
		g1.setGroupId(1);
		g1.setName("groupe test");

		g2 = new Group();
		g2.setGroupId(2);
		g2.setName("groupe test 2");

		p1 = new Person();
		p1.setEmail("un@test.com");
		p1.setPersonId(1);
		p1.setGroupId(1);
		p1.setPassword("pwd");

		p2 = new Person();
		p2.setEmail("deux@test.com");
		p2.setPersonId(2);
		p2.setGroupId(1);
		p2.setPassword("pwd");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAllGroups() {
		try {
			dao.saveGroup(g1);
			dao.saveGroup(g2);
		} catch (InvalidGroupException e) {
			e.printStackTrace();
		}

		List<Group> expected = new ArrayList<>();
		expected.add(g1);
		expected.add(g2);
		assertEquals(expected, dao.findAllGroups());
		
		try {
			dao.savePerson(p1);
		} catch (InvalidPersonException e) {
			e.printStackTrace();
		}

		g1.getPersonsList().add(p1);
		assertEquals(expected, dao.findAllGroups());
		
		dao.clearTables();
		assertEquals(new ArrayList<Group>(), dao.findAllGroups());
	}

	@Test
	public void testFindAllPersons() {
		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
			dao.savePerson(p2);
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}

		List<Person> expected = new ArrayList<>();
		expected.add(p1);
		expected.add(p2);

		assertEquals(expected, dao.findAllPersons(g1.getGroupId()));
		assertEquals(new ArrayList<Person>(), dao.findAllPersons(g2.getGroupId()));
	}

	@Test
	public void testFindPerson() throws PersonDoesNotExistException {
		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}

		Person tmp = dao.findPerson(p1.getPersonId());

		assertEquals(p1.getBirthDate(), tmp.getBirthDate());
		assertEquals(p1.getEmail(), tmp.getEmail());
		assertEquals(p1.getFirstName(), tmp.getFirstName());
		assertEquals(p1.getGroupId(), tmp.getGroupId());
		assertEquals(p1.getLastName(), tmp.getLastName());
		assertEquals(p1.getPassword(), tmp.getPassword());
		assertEquals(p1.getPersonId(), tmp.getPersonId());
		assertEquals(p1.getWebSite(), tmp.getWebSite());
	}

	@Test
	public void testFindPersonDoesNotExist() throws PersonDoesNotExistException {
		thrown.expect(PersonDoesNotExistException.class);
		dao.findPerson(p1.getPersonId());
	}

	@Test
	public void testFindGroup() {
		try {
			dao.saveGroup(g1);
			Group tmp = dao.findGroup(g1.getGroupId());
			assertEquals(g1.getGroupId(), tmp.getGroupId());
			assertEquals(g1.getName(), tmp.getName());
			assertEquals(null, tmp.getPersonsList());
			dao.savePerson(p1);
			List<Person> expected = new ArrayList<>();
			expected.add(p1);
			tmp = dao.findGroup(g1.getGroupId());
			assertEquals(expected, tmp.getPersonsList());
		} catch (InvalidGroupException | GroupDoesNotExistException | InvalidPersonException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFindGroupDoesNotExist() throws GroupDoesNotExistException {
		thrown.expect(GroupDoesNotExistException.class);
		dao.findGroup(g1.getGroupId());
	}

	@Test
	public void testSavePerson() {
		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
			assertTrue(dao.personExists(p1.getPersonId()));
			
			p1.setFirstName("jean");
			dao.savePerson(p1);
			assertEquals(p1.getFirstName(),dao.findPerson(p1.getPersonId()).getFirstName());
		} catch (InvalidGroupException | InvalidPersonException | PersonDoesNotExistException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSavePersonInvalidPersonException() throws InvalidPersonException {
		//A compléter avec plus de cas par la suite
		thrown.expect(InvalidPersonException.class);
		p1.setPersonId(0);
		dao.savePerson(p1);
	}

	@Test
	public void testSaveGroup() {
		try {
			dao.saveGroup(g1);
			assertTrue(dao.groupExists(g1.getGroupId()));
			g1.setName("toto");
			dao.saveGroup(g1);
			assertEquals(g1.getName(),dao.findGroup(g1.getGroupId()).getName());
		} catch (InvalidGroupException | GroupDoesNotExistException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testSaveGroupInvalidGroupException() throws InvalidGroupException {
		//A compléter avec plus de cas par la suite
		thrown.expect(InvalidGroupException.class);
		g1.setGroupId(0);
		dao.saveGroup(g1);
	}

	@Test
	public void testClearTables() {
		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}

		assertTrue(dao.groupExists(g1.getGroupId()));
		assertTrue(dao.personExists(p1.getPersonId()));
		dao.clearTables();
		assertFalse(dao.groupExists(g1.getGroupId()));
		assertFalse(dao.personExists(p1.getPersonId()));
	}

	@Test
	public void testClearPersons() {
		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}

		assertTrue(dao.personExists(p1.getPersonId()));
		dao.clearPersons();
		assertFalse(dao.personExists(p1.getPersonId()));
		dao.clearPersons();
	}

	@Test
	public void testPersonExists() {
		assertFalse(dao.personExists(p1.getPersonId()));

		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}

		assertTrue(dao.personExists(p1.getPersonId()));
	}

	@Test
	public void testGroupExists() {
		assertFalse(dao.groupExists(g1.getGroupId()));

		try {
			dao.saveGroup(g1);
		} catch (InvalidGroupException e) {
			e.printStackTrace();
		}

		assertTrue(dao.groupExists(g1.getGroupId()));
	}

	@Test
	public void testDeletePerson() {
		assertFalse(dao.personExists(p1.getPersonId()));

		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}

		assertTrue(dao.personExists(p1.getPersonId()));

		try {
			dao.deletePerson(p1.getPersonId());
		} catch (PersonDoesNotExistException e) {
			e.printStackTrace();
		}

		assertFalse(dao.personExists(p1.getPersonId()));
	}
	
	@Test
	public void testDeletePersonPersonDoesNotExistException() throws PersonDoesNotExistException {
		//TODO :A compléter avec plus de cas par la suite
		thrown.expect(PersonDoesNotExistException.class);
		dao.deletePerson(p1.getPersonId());
	}
	
	@Test
	public void testFindPersonByEmail() throws PersonDoesNotExistException {
		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
			assertEquals(p1, dao.findPersonByEmail(p1.getEmail()));
		} catch (InvalidGroupException | InvalidPersonException e) {
			e.printStackTrace();
		}
		thrown.expect(PersonDoesNotExistException.class);
		dao.findPersonByEmail(p2.getEmail());
	}
}
