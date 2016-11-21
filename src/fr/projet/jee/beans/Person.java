package fr.projet.jee.beans;

import java.sql.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Person {
	
	private long personId;
	private String firstName;
	private String lastName;
	private String email;
	private String webSite;
	private Date birthDate;
	private String password;
	private long groupId;

	@PostConstruct
	public void init() {
		
	}
	
	@PreDestroy
	public void close() {
		
	}
	
	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Person) {
			Person person = (Person) other;
			return person.getPersonId() == this.getPersonId();
		} else {
			throw new IllegalArgumentException();
		}
	}
	
}
	
	