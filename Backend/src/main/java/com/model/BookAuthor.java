package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookAuthor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String isbn;
	private int authorId;


    @Column(name = "PrimaryAuthor")
    private boolean primaryAuthor;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public boolean getPrimaryAuthor() {
		return primaryAuthor;
	}

	public void setPrimaryAuthor(boolean primaryAuthor) {
		this.primaryAuthor = primaryAuthor;
	}
    
    
	
}
