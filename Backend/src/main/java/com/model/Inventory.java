package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int inventoryId;
	
	@ManyToOne
	@NotNull(message = "Book reference cannot be null")
    private Book book;
	@NotBlank(message = "isbn cannot be null or blank")
    @Pattern(regexp = "\\d{13}", message = "isbn must be a 13-digit number")
	
	private String isbn;
	@Min(value = 1, message = "Ranks must be at least 1")
	private int ranks;
	@NotNull(message = "Purchased status cannot be null")
	private boolean purchased;
	public Inventory(int inventoryId, Book book, String isbn, int ranks, boolean purchased) {
		super();
		this.inventoryId = inventoryId;
		this.book = book;
		this.isbn = isbn;
		this.ranks = ranks;
		this.purchased = purchased;
	}
	public Inventory() {
		super();
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getRanks() {
		return ranks;
	}
	public void setRanks(int ranks) {
		this.ranks = ranks;
	}
	public boolean isPurchased() {
		return purchased;
	}
	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}
	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", book=" + book + ", isbn=" + isbn + ", ranks=" + ranks
				+ ", purchased=" + purchased + "]";
	}
	
	
}
