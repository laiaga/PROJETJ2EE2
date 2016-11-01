package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import beans.Group;
import beans.Person;
import dao.interfaces.IPersonDao;

public class PersonDao implements IPersonDao {

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/annuaire";

	// Database credentials
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
			resultSet = statement.executeQuery("select * from group");
			while (resultSet.next()) {
				Group group = new Group();
				group.setIdGroup(resultSet.getLong("idGroup"));
				group.setName(resultSet.getString("nomGroup"));
				listGroups.add(group);
				// TODO : gestion des personnes qui font partie du groupe
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
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

			resultSet = statement.executeQuery("select * from person where idGroup = " + groupId);
			while (resultSet.next()) {
				Person person = new Person();
				person.setIdPerson(resultSet.getLong("idPerson"));
				person.setFirstName(resultSet.getString("firstName"));
				person.setLastName(resultSet.getString("lastName"));
				person.setEmail(resultSet.getString("email"));
				person.setWebSite(resultSet.getString("webSite"));
				person.setBirthDate(resultSet.getDate("birthDate"));
				person.setPassword(resultSet.getString("password"));
				person.setIdGroup(resultSet.getLong("idGroup"));
				listPersons.add(person);
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return listPersons;
	}

	@Override
	public Person findPerson(long id) {
		Person person = new Person();

		try (Connection connection = createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from person where idPerson = " + id);
			while (resultSet.next()) {
				person.setIdPerson(resultSet.getLong("idPerson"));
				person.setFirstName(resultSet.getString("firstName"));
				person.setLastName(resultSet.getString("lastName"));
				person.setEmail(resultSet.getString("email"));
				person.setWebSite(resultSet.getString("webSite"));
				person.setBirthDate(resultSet.getDate("birthDate"));
				person.setPassword(resultSet.getString("password"));
				person.setIdGroup(resultSet.getLong("idGroup"));
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		// TODO : ajouter une exception PersonNotFound ?
		return person;
	}

	@Override
	public void savePerson(Person person) {
		try (Connection connection = createConnection()) {
			String sql;
			sql = "INSERT INTO PERSON(idPerson,firstName,LastName,email,webSite,birthDate,password,idGroup)"
					+ " VALUES(?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(0, person.getIdPerson());
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setString(4, person.getWebSite());
			preparedStatement.setDate(5, new java.sql.Date(person.getBirthDate().getTime()));
			preparedStatement.setString(6, person.getPassword());
			preparedStatement.setLong(7, person.getIdGroup());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

	}

	@Override
	public void saveGroup(Group group) {
		try (Connection connection = createConnection()) {
			String sql;
			sql = "INSERT INTO GROUP(idGroup, nomGroup) VALUES(?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(0, group.getIdGroup());
			preparedStatement.setString(1, group.getName());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

	}

}