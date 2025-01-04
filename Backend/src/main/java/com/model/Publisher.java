package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Publisher {
    @Id
    @Column(name = "publisher_id")
    @Min(value = 1, message = "Publisher ID must be a positive number.")
    private int publisherId;

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
    private String name;

    @NotBlank(message = "City cannot be blank.")
    @Size(max = 50, message = "City name must not exceed 50 characters.")
    private String city;

    @NotBlank(message = "State code cannot be blank.")
    @Pattern(regexp = "^[A-Z]{2}$", message = "State code must be exactly two uppercase letters.")
    @Column(name = "state_code")
    private String stateCode;

    public Publisher(int publisherId, String name, String city, String stateCode) {
        super();
        this.publisherId = publisherId;
        this.name = name;
        this.city = city;
        this.stateCode = stateCode;
    }

    public Publisher() {
        super();
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
