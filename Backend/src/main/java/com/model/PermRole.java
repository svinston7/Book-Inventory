package com.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class PermRole {
	@Id
	private int roleNumber;
	private String permRole;
	
	 @OneToMany(mappedBy = "permRole", cascade = CascadeType.ALL)
	 private List<User> users;
	
	public PermRole() {}
	
	public PermRole(int roleNumber, String permRole) {
		super();
		this.roleNumber = roleNumber;
		this.permRole = permRole;
	}
	public int getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(int roleNumber) {
		this.roleNumber = roleNumber;
	}
	public String getPermRole() {
		return permRole;
	}
	public void setPermRole(String permRole) {
		this.permRole = permRole;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "PermRole [roleNumber=" + roleNumber + ", permRole=" + permRole + "]";
	}
	
	
}
