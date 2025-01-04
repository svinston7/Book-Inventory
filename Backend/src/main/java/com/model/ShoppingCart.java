package com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "ISBN cannot be blank.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ISBN must contain only letters and numbers.")
    private String isbn;

    @ManyToOne
    @NotNull(message = "Book cannot be null.")
    private Book book;

    @Column(name = "user_name", nullable = false)
    @NotBlank(message = "User name cannot be blank.")
    @Size(max = 100, message = "User name cannot exceed 100 characters.")
    private String userName;

    // Constructors
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

    // Getters and Setters
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
