package com.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user_table")
public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String userName;
	private String password;
	
	
	@ManyToOne
    @JoinColumn(name = "RoleNumber", referencedColumnName = "RoleNumber")
    private PermRole permRole;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PurchaseLog> purchaseLogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ShoppingCart> shoppingCarts;
	
	public User() {
	}


	public User(int userId, String firstName, String lastName, String phoneNumber, String userName, String password,
			PermRole permRole) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.permRole = permRole;
	}
	
	
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
	public PermRole getRoleNumber() {
		return permRole;
	}
	public void setRoleNumber(PermRole  roleNumber) {
		this.permRole = roleNumber;
	}


	public PermRole getPermRole() {
		return permRole;
	}


	public void setPermRole(PermRole permRole) {
		this.permRole = permRole;
	}


	public List<PurchaseLog> getPurchaseLogs() {
		return purchaseLogs;
	}


	public void setPurchaseLogs(List<PurchaseLog> purchaseLogs) {
		this.purchaseLogs = purchaseLogs;
	}


	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}


	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", userName=" + userName + ", password=" + password + ", roleNumber=" + permRole
				+ "]";
	}
	
	
	

}
