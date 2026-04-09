package com.example.demo.model;

import java.util.List;

public class ApiResponse {
	private String status;
	private List<ErrorDetail> errors;
	
	public ApiResponse(String status, List<ErrorDetail> errors) {
		this.status = status;
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetail> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", errors=" + errors + "]";
	}
	
	
}
