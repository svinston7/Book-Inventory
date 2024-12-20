package com.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Reviewer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewerId;
	private String name;
	private String employedBy;
	
	@ManyToOne
    @JoinColumn(name = "book_review_id")
    private BookReview bookReview;
	
	public Reviewer() {
		super();
	}


	public Reviewer(int reviewerId, String name, String employedBy) {
		super();
		this.reviewerId = reviewerId;
		this.name = name;
		this.employedBy = employedBy;
	}
	


	public BookReview getBookReview() {
		return bookReview;
	}


	public void setBookReview(BookReview bookReview) {
		this.bookReview = bookReview;
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
