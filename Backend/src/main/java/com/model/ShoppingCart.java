package com.model;

import jakarta.persistence.Column;
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

    private String isbn;

    @ManyToOne
    private Book book;
    
    @Column(name = "user_name", nullable = false)
    private String userName;
    
    public ShoppingCart(String userName, Book book) {
        this.userName = userName;
        this.book = book;
    }

    public ShoppingCart(int id, String isbn, Book book, String userName) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.book = book;
		this.userName = userName;
	}

	public ShoppingCart() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
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
