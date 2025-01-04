package com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
public class Reviewer {

    @Id
    @NotNull(message = "Reviewer ID cannot be null.")
    private int reviewerId;

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets.")
    private String name;

    @NotBlank(message = "Employed By cannot be blank.")
    @Size(max = 100, message = "Employed By cannot exceed 100 characters.")
    private String employedBy;

    @ManyToOne
    @JoinColumn(name = "book_review_id")
    private BookReview bookReview;

    // Constructors
    public Reviewer() {
        super();
    }

    public Reviewer(int reviewerId, String name, String employedBy) {
        super();
        this.reviewerId = reviewerId;
        this.name = name;
        this.employedBy = employedBy;
    }

    // Getters and Setters
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

    public BookReview getBookReview() {
        return bookReview;
    }

    public void setBookReview(BookReview bookReview) {
        this.bookReview = bookReview;
    }

    // toString
    @Override
    public String toString() {
        return "Reviewer [reviewerId=" + reviewerId + ", name=" + name + ", employedBy=" + employedBy + "]";
    }
}
