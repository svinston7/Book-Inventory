package com.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class BookReview {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
	
    private String isbn;
    private int reviewerId;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Comments",length = 1000)
    private String comments;

    
    
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

	

	public BookReview() {
		super();
	}

	@Override
	public String toString() {
		return "BookReview [id=" + id + ", isbn=" + isbn + ", reviewerId=" + reviewerId + ", rating=" + rating
				+ ", comments=" + comments + "]";
	}

	
    
    
	
	
}
