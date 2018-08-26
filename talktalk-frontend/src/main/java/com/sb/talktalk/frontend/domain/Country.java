package com.sb.talktalk.frontend.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Country {
	private String name;
	private String flag;
	
	public Country(final String name, final String flag) {
		this.name = name;
		this.flag = flag;
	}
	
	public Country() {
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getFlag() {
		return this.flag;
	}
	public void setFlag(final String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Country country = (Country) o;

		return new EqualsBuilder()
				.append(name, country.name)
				.append(flag, country.flag)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(name)
				.append(flag)
				.toHashCode();
	}
}
