package fr.projet.jee.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import fr.projet.jee.beans.Group;
import fr.projet.jee.beans.Person;
import fr.projet.jee.dao.interfaces.IPersonDao;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

@Service
public class PersonDao implements IPersonDao {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/annuaire?useSSL=false";

	private static final String USER = "root";
	private static final String PASS = "root";

	private Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
		return connection;
	}

	@Override
	public Collection<Group> findAllGroups() {
		Collection<Group> listGroups = new ArrayList<>();

		try (Connection connection = createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from GROUPS");

			while (resultSet.next()) {
				Group group = new Group();
				group.setGroupId(resultSet.getLong("GroupId"));
				group.setName(resultSet.getString("Name"));
				listGroups.add(group);
				// TODO : gestion des personnes qui font partie du groupe
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listGroups;
	}

	@Override
	public Collection<Person> findAllPersons(long groupId) {
		Collection<Person> listPersons = new ArrayList<>();

		try (Connection connection = createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from PERSONS where GroupId = " + groupId);

			while (resultSet.next()) {
				Person person = new Person();
				person.setPersonId(resultSet.getLong("PersonId"));
				person.setFirstName(resultSet.getString("FirstName"));
				person.setLastName(resultSet.getString("LastName"));
				person.setEmail(resultSet.getString("Email"));
				person.setWebSite(resultSet.getString("WebSite"));
				person.setBirthDate(resultSet.getDate("BirthDate"));
				person.setPassword(resultSet.getString("Password"));
				person.setGroupId(resultSet.getLong("GroupId"));
				listPersons.add(person);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listPersons;
	}

	@Override
	public Person findPerson(long id) throws PersonDoesNotExistException {
		Person person = new Person();

		try (Connection connection = createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from PERSONS where PersonId=" + id + ";");

			if (resultSet.next()) {
				person.setPersonId(resultSet.getLong("PersonId"));
				person.setFirstName(resultSet.getString("FirstName"));
				person.setLastName(resultSet.getString("LastName"));
				person.setEmail(resultSet.getString("Email"));
				person.setWebSite(resultSet.getString("WebSite"));
				person.setBirthDate(resultSet.getDate("BirthDate"));
				person.setPassword(resultSet.getString("Password"));
				person.setGroupId(resultSet.getLong("GroupId"));
			} else {
				throw new PersonDoesNotExistException("There is no person of id=" + id + " in base !");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return person;
	}

	@Override
	public void savePerson(Person person) {
		try (Connection connection = createConnection()) {
			String sql;
			sql = "INSERT INTO PERSONS(PersonId,FirstName,LastName,Email,WebSite,BirthDate,Password,GroupId)"
					+ " VALUES(?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, person.getPersonId());
			preparedStatement.setString(2, person.getFirstName());
			preparedStatement.setString(3, person.getLastName());
			preparedStatement.setString(4, person.getEmail());
			preparedStatement.setString(5, person.getWebSite());
			preparedStatement.setDate(6, new java.sql.Date(person.getBirthDate().getTime()));
			preparedStatement.setString(7, person.getPassword());
			preparedStatement.setLong(8, person.getGroupId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void saveGroup(Group group) {
		try (Connection connection = createConnection()) {
			String sql;
			sql = "INSERT INTO GROUPS(GroupId, Name) VALUES(?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, group.getGroupId());
			preparedStatement.setString(2, group.getName());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void clearTables() {
		clearPersons();
		clearGroups();
	}

	@Override
	public void clearPersons() {
		try (Connection connection = createConnection()) {
			String sql;
			Statement statement = connection.createStatement();
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet resultSet = metaData.getTables(null, null, "PERSONS", new String[] { "TABLE" });

			if (resultSet.next()) {
				sql = "TRUNCATE TABLE PERSONS;";
				statement.executeUpdate(sql);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Vide la table GROUPS Cette opération est risquée car elle ne prend pas en
	 * compte les groupes éventuellement référencés par des personnes via la clé
	 * étrangère
	 */
	private void clearGroups() {
		try (Connection connection = createConnection()) {
			String sql;
			Statement statement = connection.createStatement();
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet resultSet = metaData.getTables(null, null, "GROUPS", new String[] { "TABLE" });

			if (resultSet.next()) {
				sql = "SET FOREIGN_KEY_CHECKS = 0; ";
				statement.executeUpdate(sql);
				sql = "TRUNCATE TABLE GROUPS;";
				statement.executeUpdate(sql);
				sql = "SET FOREIGN_KEY_CHECKS = 1;";
				statement.executeUpdate(sql);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Group findGroup(long id) {
		Group group = new Group();
		try (Connection connection = createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from GROUPS where GroupId=" + id + ";");
			while (resultSet.next()) {
				group.setName(resultSet.getString("Name"));
				group.setGroupId(resultSet.getInt("GroupId"));
				// TODO : gestion des personnes qui font partie du groupe
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;
	}

	@Override
	public boolean personExists(long id) {
		boolean ret = false;

		try (Connection connection = createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select PersonId=" + id + " from PERSONS;");

			ret = resultSet.next();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public boolean groupExists(long id) {
		boolean ret = false;

		try (Connection connection = createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select GroupId=" + id + " from GROUPS;");

			ret = resultSet.next();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public void deletePerson(long id) {
		try (Connection connection = createConnection()) {
			Statement statement = connection.createStatement();
			if (personExists(id)) {
				statement.executeUpdate("delete from PERSONS where PersonId=" + id +";");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}