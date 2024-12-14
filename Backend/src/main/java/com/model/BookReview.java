package com.model;

public class BookReview {
	private String isbn;
	private int reviewerId;
	private int rating;
	private String comments;
	
	
	public BookReview(String isbn, int reviewerId, int rating, String comments) {
		super();
		this.isbn = isbn;
		this.reviewerId = reviewerId;
		this.rating = rating;
		this.comments = comments;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(int reviewerId) {
		this.reviewerId = reviewerId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
