package com.exception;

import java.time.LocalDateTime;


public class ErrorDetails {
	private String message;
    private int status;
    private LocalDateTime timestamp;

	public ErrorDetails(String message, int status, LocalDateTime timestamp) {
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

	public ErrorDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
