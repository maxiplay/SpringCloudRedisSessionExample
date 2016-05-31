package com.worldline.springcloudarchetype.userdatamodel.model;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4194014633253706583L;
	private String title;
	private Set<Right> rights;

	public String getTitle() {
		return title;
		
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

}
