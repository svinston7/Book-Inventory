package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class State {

    @Id
    @NotBlank(message = "State code cannot be blank.")
    @Size(max = 3, message = "State code must be at most 3 characters long.")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "State code must consist of 2 or 3 uppercase letters.")
    private String stateCode;

    @NotBlank(message = "State name cannot be blank.")
    @Size(max = 100, message = "State name must not exceed 100 characters.")
    private String stateName;

    // Constructors
    public State(String stateCode, String stateName) {
        super();
        this.stateCode = stateCode;
        this.stateName = stateName;
    }

    public State() {
        super();
    }

    // Getters and Setters
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
