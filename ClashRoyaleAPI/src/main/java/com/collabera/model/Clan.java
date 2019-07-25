package com.collabera.model;

/**
 * @author rutpatel
 *
 */
public class Clan {

	String name;

	public Clan() {
	}

	public Clan(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Clan [name=" + name + "]";
	}

}
