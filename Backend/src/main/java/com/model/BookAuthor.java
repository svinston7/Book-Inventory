package com.model;

public class BookAuthor {
	private String isbn;
	private int authorId;
	private String primaryAuthor;
	
	
	public BookAuthor(String isbn, int authorId, String primaryAuthor) {
		super();
		this.isbn = isbn;
		this.authorId = authorId;
		this.primaryAuthor = primaryAuthor;
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
	public String getPrimaryAuthor() {
		return primaryAuthor;
	}
	public void setPrimaryAuthor(String primaryAuthor) {
		this.primaryAuthor = primaryAuthor;
	}
	
	
}
