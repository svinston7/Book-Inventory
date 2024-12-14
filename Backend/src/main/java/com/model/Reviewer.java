package com.model;

public class Reviewer {
	private int reviewerId;
	private String name;
	private String employedBy;
	
	
	public Reviewer(int reviewerId, String name, String employedBy) {
		super();
		this.reviewerId = reviewerId;
		this.name = name;
		this.employedBy = employedBy;
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
	

}
