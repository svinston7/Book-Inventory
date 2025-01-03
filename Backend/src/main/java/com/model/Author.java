package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Author {

	@Id
	private int authorId;
	private String firstName;
	private String lastName;
	private String photo;
	
	public Author() {}
	
	public Author(int authorId, String firstName, String lastName, String photo) {
		super();
		this.authorId = authorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
	}
	
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", firstName=" + firstName + ", lastName=" + lastName + ", photo="
				+ photo + "]";
	}
	
	
	
}
