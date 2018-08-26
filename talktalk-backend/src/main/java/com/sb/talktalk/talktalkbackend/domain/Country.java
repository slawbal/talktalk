package com.sb.talktalk.talktalkbackend.domain;

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
	
	
}
