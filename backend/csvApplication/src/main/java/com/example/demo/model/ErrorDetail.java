package com.example.demo.model;

public class ErrorDetail {
	private int row;
	private String message;
	
	public ErrorDetail() {
		
	}
	
	public ErrorDetail(int row, String message) {
		this.row = row;
		this.message = message;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorDetail [row=" + row + ", message=" + message + "]";
	}
	
	
}
