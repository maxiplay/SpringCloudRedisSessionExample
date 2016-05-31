package com.worldline.springcloudarchetype.userdatamodel.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonView;

@Document
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7455921124066073751L;

	@Id
	@ApiModelProperty(value = "User ID", required = true)
	private String id;

	@ApiModelProperty(value = "Login", required = true)
	@JsonView(UserView.Protected.class)
	@Indexed(unique = true)
	private String login;

	@ApiModelProperty(hidden = true)
	private String password;

	@ApiModelProperty(value = "Firstname", required = true)
	@JsonView(UserView.Protected.class)
	private String firstName;

	@ApiModelProperty(value = "Lastname", required = true)
	@JsonView(UserView.Protected.class)
	private String lastName;

	@ApiModelProperty(value = "Role", required = true)
	@JsonView(UserView.Protected.class)
	private Role role;

	@ApiModelProperty(value = "Books", required = true)
	@JsonView(UserView.Protected.class)
	private List<String> books;

	@ApiModelProperty(value = "Theme", required = true)
	@JsonView(UserView.Protected.class)
	private String theme;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<String> getBooks() {
		return books;
	}

	public void setBooks(List<String> books) {
		this.books = books;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}