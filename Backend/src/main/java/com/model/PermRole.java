package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "roles")
public class PermRole {
    @Id
    @Min(value = 1, message = "Role number must be greater than or equal to 1.")
    private int roleNumber;

    @NotBlank(message = "Permission role cannot be blank.")
    @Size(min = 3, max = 50, message = "Permission role must be between 3 and 50 characters.")
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
