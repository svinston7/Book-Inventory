package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user_table")
public class User {
    
    @Id
    @GeneratedValue
    private int userId;

    @NotBlank(message = "First name cannot be blank.")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabets.")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank.")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabets.")
    private String lastName;

    @NotBlank(message = "Phone number cannot be blank.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a 10-digit number.")
    private String phoneNumber;

    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only alphabets.")
    private String userName;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,20}$", 
             message = "Password must contain at least one uppercase letter, one number, and one special character.")
    private String password;

    private int roleNumber;

    // Constructors
    public User() {
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleNumber() {
        return roleNumber;
    }

    public void setRoleNumber(int roleNumber) {
        this.roleNumber = roleNumber;
    }

    // toString method
    @Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
                + phoneNumber + ", userName=" + userName + ", password=" + password + "]";
    }
}
