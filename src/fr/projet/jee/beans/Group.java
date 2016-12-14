package fr.projet.jee.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Group {

	private String name;
	private long groupId;
	private List<Person> personsList;

	@PostConstruct
	public void init() {

	}

	@PreDestroy
	public void close() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public List<Person> getPersonsList() {
		return personsList;
	}

	public void setPersonsList(List<Person> personsList) {
		this.personsList = personsList;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Group) {
			Group group = (Group) other;
			return group.getGroupId() == this.getGroupId();
		} else {
			throw new IllegalArgumentException();
		}
	}
}
