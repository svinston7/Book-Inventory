package com.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
public class BookReview {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
	
	@NotBlank(message = "isbn cannot be null or blank")
    private String isbn;
	@NotNull(message = "reviewer id cannot be null")
    @Min(value = 1, message = "reviewer id must be a positive number")
	
	
    private int reviewerId;

    @Column(name = "Rating")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    
    private int rating;

    @Column(name = "Comments",length = 1000)
    @Size(max = 1000, message = "Comments cannot exceed 1000 characters")
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
