package com.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Book {
	
	@Id

    @Column(name = "isbn")  
    private String isbn;

    @Column(name = "title")  
    private String title;

    @Column(name = "description")  
    private String description;

    @Column(name = "category_id") 
    private int categoryId;

    @Column(name = "edition")  
    private String edition;

    @Column(name = "publisher_id")  
    private int publisherId;

    @Column(name = "image")  
    private String image;
  
	private float price;
  
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Book() {}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	

	public String getTitle() {
		return title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getPublisherId() {
		return publisherId;
	}




	public Book(String isbn, String title, String description, int categoryId, String edition, int publisherId,
			String image) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.categoryId = categoryId;
		this.edition = edition;
		this.publisherId = publisherId;
		this.image = image;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", categoryId=" + categoryId
				+ ", edition=" + edition + ", publisherId=" + publisherId + ", image=" + image + ", price=" + price
				+ "]";
	}




	
	
	

}
