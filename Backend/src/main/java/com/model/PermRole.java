package com.model;

public class PermRole {
	private int roleNumber;
	private String permRole;
	
	
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
	
	
}
