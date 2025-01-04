package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
@Entity
public class BookAuthor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@NotBlank(message = "ISBN cannot be null or empty")
    @Size(max = 13, message = "ISBN should be at most 13 characters")
	
	private String isbn;
	@NotNull(message = "author ID cannot be null")
	@Positive(message = "author ID must be a positive number")
	private int authorId;


    @Column(name = "PrimaryAuthor")
    @NotNull(message = "PrimaryAuthor flag cannot be null")
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
