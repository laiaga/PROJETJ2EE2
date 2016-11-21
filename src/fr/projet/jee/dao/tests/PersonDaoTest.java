package fr.projet.jee.dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
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

	@SuppressWarnings("deprecation")
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
		p1.setBirthDate(new Date(1991, 7, 21));
		p1.setEmail("un@test.com");
		p1.setFirstName("john");
		p1.setPersonId(1);
		p1.setGroupId(1);
		p1.setLastName("doe");
		p1.setPassword("pwd");
		p1.setWebSite("site.un.com");

		p2 = new Person();
		p2.setBirthDate(new Date(1952, 1, 17));
		p2.setEmail("deux@test.com");
		p2.setFirstName("will");
		p2.setPersonId(2);
		p2.setGroupId(1);
		p2.setLastName("smith");
		p2.setPassword("pwd");
		p2.setWebSite("site.deux.com");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAllGroups() {
		dao.saveGroup(g1);
		dao.saveGroup(g2);

		List<Group> expected = new ArrayList<>();
		expected.add(g1);
		expected.add(g2);
		assertEquals(expected, dao.findAllGroups());

		dao.clearTables();
		assertEquals(new ArrayList<Group>(), dao.findAllGroups());
	}

	@Test
	public void testFindAllPersons() {
		dao.saveGroup(g1);
		dao.savePerson(p1);
		dao.savePerson(p2);

		List<Person> expected = new ArrayList<>();
		expected.add(p1);
		expected.add(p2);

		assertEquals(expected, dao.findAllPersons(g1.getGroupId()));
		assertEquals(new ArrayList<Person>(), dao.findAllPersons(g2.getGroupId()));
	}

	@Test
	public void testFindPerson() throws PersonDoesNotExistException {
		dao.saveGroup(g1);
		dao.savePerson(p1);

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
		dao.saveGroup(g1);
		Group tmp = dao.findGroup(g1.getGroupId());
		assertEquals(g1.getGroupId(), tmp.getGroupId());
		assertEquals(g1.getName(), tmp.getName());
		assertEquals(g1.getPersonsList(), tmp.getPersonsList());
	}

	@Test
	public void testSavePerson() {
		dao.saveGroup(g1);
		dao.savePerson(p1);
		assertTrue(dao.personExists(p1.getPersonId()));
	}

	@Test
	public void testSaveGroup() {
		dao.saveGroup(g1);
		assertTrue(dao.groupExists(g1.getGroupId()));
	}

	@Test
	public void testClearTables() {
		dao.saveGroup(g1);
		dao.savePerson(p1);
		assertTrue(dao.groupExists(g1.getGroupId()));
		assertTrue(dao.personExists(p1.getPersonId()));
		dao.clearTables();
		assertFalse(dao.groupExists(g1.getGroupId()));
		assertFalse(dao.personExists(p1.getPersonId()));
	}

	@Test
	public void testClearPersons() {
		dao.saveGroup(g1);
		dao.savePerson(p1);
		assertTrue(dao.personExists(p1.getPersonId()));
		dao.clearPersons();
		assertFalse(dao.personExists(p1.getPersonId()));
	}

	@Test
	public void testPersonExists() {
		assertFalse(dao.personExists(p1.getPersonId()));
		dao.saveGroup(g1);
		dao.savePerson(p1);
		assertTrue(dao.personExists(p1.getPersonId()));
	}

	@Test
	public void testGroupExists() {
		assertFalse(dao.groupExists(g1.getGroupId()));
		dao.saveGroup(g1);
		assertTrue(dao.groupExists(g1.getGroupId()));
	}

	@Test
	public void testDeletePerson() {
		assertFalse(dao.personExists(p1.getPersonId()));
		dao.saveGroup(g1);
		dao.savePerson(p1);
		assertTrue(dao.personExists(p1.getPersonId()));
		dao.deletePerson(p1.getPersonId());
		assertFalse(dao.personExists(p1.getPersonId()));
	}

}
