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
	
	@ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "AuthorID")
    private Author author;

    @Column(name = "PrimaryAuthor")
    private String primaryAuthor;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getPrimaryAuthor() {
		return primaryAuthor;
	}

	public void setPrimaryAuthor(String primaryAuthor) {
		this.primaryAuthor = primaryAuthor;
	}
    
    
	
}
