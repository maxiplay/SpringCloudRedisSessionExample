package com.worldline.springcloudarchetype.book.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Book
 * 
 * @author A525125
 *
 */
@Document
public class Book {

	@Id
	@ApiModelProperty(value = "Book ID", required = true)
	private String id;
	@ApiModelProperty(value = "Owner", required = true)
	private String owner;
	@ApiModelProperty(value = "Title", required = true)
	private String title;
	@ApiModelProperty(value = "Resume", required = true)
	private String resume;
	@ApiModelProperty(value = "Creation date", required = true)
	private Date creationDate;

	public String getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
