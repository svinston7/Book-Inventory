package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookReview {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Composite key can be managed separately

    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "ReviewerID")
    private Reviewer reviewer;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Comments")
    private String comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Reviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
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
		return "BookReview [id=" + id + ", book=" + book + ", reviewer=" + reviewer + ", rating=" + rating
				+ ", comments=" + comments + "]";
	}
    
    
	
	
}
