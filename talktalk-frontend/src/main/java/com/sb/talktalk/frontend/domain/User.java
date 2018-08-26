package com.sb.talktalk.frontend.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

	private String name;
	private String login;
	private int age;
	private Country country;
	private String location;
	private String sessionID;

	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getLogin() {
		return this.login;
	}
	public void setLogin(final String login) {
		this.login = login;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(final int age) {
		this.age = age;
	}
	public Country getCountry() {
		return this.country;
	}
	public void setCountry(final Country country) {
		this.country = country;
	}
	public String getLocation() {
		return this.location;
	}
	public void setLocation(final String location) {
		this.location = location;
	}
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", login='" + login + '\'' +
				", age=" + age +
				", country=" + country +
				", sessionID='" + sessionID + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return new EqualsBuilder()
				.append(age, user.age)
				.append(name, user.name)
				.append(login, user.login)
				.append(country, user.country)
				.append(location, user.location)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(name)
				.append(login)
				.append(age)
				.append(country)
				.append(location)
				.toHashCode();
	}
}
