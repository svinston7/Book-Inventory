package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class ShoppingCart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
	
    private String userName;
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String isbn;

    @ManyToOne
    private Book book;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	
    
    
}
