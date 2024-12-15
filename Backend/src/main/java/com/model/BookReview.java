package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BookReview {
	@Id
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

	@Override
	public String toString() {
		return "BookReview [isbn=" + isbn + ", reviewerId=" + reviewerId + ", rating=" + rating + ", comments="
				+ comments + "]";
	}
	
	
}
