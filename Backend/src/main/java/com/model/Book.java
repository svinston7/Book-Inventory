package com.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


@Entity
public class Book {
	
	@Id
	@NotBlank(message = "ISBN cannot be blank")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "ISBN must be a valid 10 or 13-digit format")

    @Column(name = "isbn")  
    private String isbn;
	@NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Column(name = "title")  
    private String title;
	@NotBlank(message = "Description cannot be blank")
    @Size(max = 500, message = "Description must be up to 500 characters")

    @Column(name = "description")  
    private String description;
	@Positive(message = "Category ID must be a positive integer")
    @Column(name = "category_id") 
    private int categoryId;
	@NotBlank(message = "Edition cannot be blank")
    @Size(max = 50, message = "Edition must be up to 50 characters")

    @Column(name = "edition")  
    private String edition;
	@Positive(message = "Publisher ID must be a positive integer")
    @Column(name = "publisher_id")  
    private int publisherId;
	@NotBlank(message = "Image URL cannot be blank")
    @Pattern(regexp = "(http(s?):/)(/[^/]+)+\\.(?:jpg|jpeg|png|gif)", 
             message = "Image must be a valid URL ending with jpg, jpeg, png, or gif")

    @Column(name = "image")  
    private String image;
	
	@PositiveOrZero(message = "Price must be a positive number or zero")
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
