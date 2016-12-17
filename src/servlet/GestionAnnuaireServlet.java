package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.projet.jee.beans.Group;
import fr.projet.jee.beans.Person;
import fr.projet.jee.dao.PersonDao;
import fr.projet.jee.dao.interfaces.IPersonDao;
import fr.projet.jee.exceptions.PersonDoesNotExistException;

/**
 * Servlet implementation class GestionAnnuaireServlet
 */
@WebServlet("/GestionAnnuaireServlet")
public class GestionAnnuaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public final static String FIND_ALL_GROUPS = "FIND_ALL_GROUPS";
	public final static String FIND_ALL_PERSONS = "FIND_ALL_PERSONS";
	public final static String FIND_PERSON = "FIND_PERSON";
	public final static String DISPLAY_PERSON = "DISPLAY_PERSON";
	public final static String CONNECTION = "CONNECTION";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionAnnuaireServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("ACTION");


		if (action.equals(GestionAnnuaireServlet.FIND_ALL_GROUPS)) {
			Collection<Group> groups = GestionAnnuaireServlet.findAllGroups();
			request.setAttribute("groups", groups.toArray(new Group[groups.size()]));

			RequestDispatcher dispatcher = request.getRequestDispatcher("/FindAllGroups.jsp");
			dispatcher.forward(request, response);

		} else if (action.equals(GestionAnnuaireServlet.FIND_ALL_PERSONS)) {

			String GroupNameSelect = request.getParameter("GroupNameSelect");

			Collection<Group> groups = GestionAnnuaireServlet.findAllGroups();
			Group[] groupsTab = groups.toArray(new Group[groups.size()]);

			if (GroupNameSelect != null && !GroupNameSelect.isEmpty()) {
				Group[] groupsTab2 = new Group[groupsTab.length];
				int index = -1;
				for (int i = 0; i < groupsTab.length; i++) {
					if (groupsTab[i].getGroupId() == (Long.parseLong(GroupNameSelect))) {
						groupsTab2[0] = groupsTab[i];
						index = i;
						break;
					}
				}

				for (int i = 0, j = 1; i < groupsTab.length && j < groupsTab2.length; i++, j++) {
					if (i != index) {
						groupsTab2[j] = groupsTab[i];
					} else {
						j--;
					}
				}

				Collection<Person> PersonCollection = findAllPersons(Long.parseLong(GroupNameSelect));
				Person[] personsTab = PersonCollection.toArray(new Person[PersonCollection.size()]);

				request.setAttribute("persons", personsTab);
				request.setAttribute("GroupNameSelect", GroupNameSelect);
				request.setAttribute("groups", groupsTab2);
			} else if (groupsTab != null || groupsTab.length != 0) {
				request.setAttribute("groups", groupsTab);
				long groupId = groupsTab[0].getGroupId();

				Collection<Person> PersonCollection = findAllPersons(groupId);

				Person[] personsTab = PersonCollection.toArray(new Person[PersonCollection.size()]);

				request.setAttribute("persons", personsTab);
				request.setAttribute("GroupNameSelect", groupId + "");
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/FindAllPersons.jsp");
			dispatcher.forward(request, response);

		} else if (action.equals(GestionAnnuaireServlet.FIND_PERSON)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FindPerson.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals(GestionAnnuaireServlet.DISPLAY_PERSON)) {
			String mail = request.getParameter("mail");
			String id = request.getParameter("id");

			if ((mail == null || mail.isEmpty()) && (id == null || id.isEmpty())) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/DisplayResultFindPerson.jsp");
				dispatcher.forward(request, response);
			} else if (mail != null && !mail.isEmpty()) {
				Person person = findPerson(mail);
				Person[] persons = new Person[1];
				persons[0] = person;
				request.setAttribute("persons", persons);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/DisplayResultFindPerson.jsp");
				dispatcher.forward(request, response);

			} else if (id != null && !id.isEmpty()) {
				Person person = findPerson(Long.parseLong(id));
				Person[] persons = new Person[1];
				persons[0] = person;
				request.setAttribute("persons", persons);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/DisplayResultFindPerson.jsp");
				dispatcher.forward(request, response);
			}
		} else if (action.equals(GestionAnnuaireServlet.CONNECTION)) {
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");

			if ((mail == null || mail.isEmpty()) || (password == null || password.isEmpty())) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Connection.jsp");
				request.setAttribute("mail", mail);
				request.setAttribute("password", password);
				dispatcher.forward(request, response);
			} else if (mail != null && !mail.isEmpty()) {

				Person person = findPerson(mail);
				if (person != null && person.getPassword().equals(password)) {

				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}	

	public static Collection<Group> findAllGroups() {
		IPersonDao person = new PersonDao();
		return person.findAllGroups();
	}

	public static Collection<Person> findAllPersons(long groupId) {
		IPersonDao person = new fr.projet.jee.dao.PersonDao();
		return person.findAllPersons(groupId);
	}

	public static Person findPerson(String email) {
		IPersonDao person = new PersonDao();

		try {
			return person.findPersonByEmail(email);
		} catch (PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;
	}

	public static Person findPerson(long id) {
		IPersonDao person = new PersonDao();

		try {
			return person.findPerson(id);
		} catch (PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
