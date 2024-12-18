package com.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Reviewer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewerId;
	private String name;
	private String employedBy;
	
	 @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
	    private List<BookReview> bookReviews;
	
	public Reviewer() {
		super();
	}


	public Reviewer(int reviewerId, String name, String employedBy) {
		super();
		this.reviewerId = reviewerId;
		this.name = name;
		this.employedBy = employedBy;
	}
	
	
	public List<BookReview> getBookReviews() {
		return bookReviews;
	}


	public void setBookReviews(List<BookReview> bookReviews) {
		this.bookReviews = bookReviews;
	}


	public int getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(int reviewerId) {
		this.reviewerId = reviewerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployedBy() {
		return employedBy;
	}
	public void setEmployedBy(String employedBy) {
		this.employedBy = employedBy;
	}


	@Override
	public String toString() {
		return "Reviewer [reviewerId=" + reviewerId + ", name=" + name + ", employedBy=" + employedBy + "]";
	}
	

}
