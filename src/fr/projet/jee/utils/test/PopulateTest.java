package fr.projet.jee.utils.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.projet.jee.exceptions.InvalidGroupException;
import fr.projet.jee.exceptions.InvalidPersonException;
import fr.projet.jee.utils.interfaces.IPopulate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
public class PopulateTest {
	@Autowired
	private IPopulate populate;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws InvalidPersonException, InvalidGroupException {
		populate.createAll();
	}

}
