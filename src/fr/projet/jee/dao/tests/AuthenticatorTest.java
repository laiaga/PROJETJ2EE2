package fr.projet.jee.dao.tests;

import static org.junit.Assert.assertEquals;

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
import fr.projet.jee.dao.interfaces.IAuthenticator;
import fr.projet.jee.dao.interfaces.IPersonDao;
import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
public class AuthenticatorTest {

	@Autowired
	private IPersonDao dao;
	
	@Autowired
	private IAuthenticator authenticator;

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
	public void testAuthenticate() throws IllegalArgumentException {
		try {
			dao.saveGroup(g1);
			dao.savePerson(p1);
			assertEquals(p1.getPersonId(), authenticator.authenticate(p1.getEmail(), p1.getPassword()));
		} catch (IllegalArgumentException | PersonDoesNotExistException | InvalidPersonException | InvalidGroupException e) {
			e.printStackTrace();
		}
		try {
			thrown.expect(IllegalArgumentException.class);
			authenticator.authenticate(p1.getEmail(), "wrongpwd");
		} catch (PersonDoesNotExistException e) {
			e.printStackTrace();
		}
	}

}
