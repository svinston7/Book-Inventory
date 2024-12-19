package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class PermRole {
	@Id
	private int roleNumber;
	private String permRole;
	
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

	@Override
	public String toString() {
		return "PermRole [roleNumber=" + roleNumber + ", permRole=" + permRole + "]";
	}
	
	
}
