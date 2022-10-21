package com.codingboot.Core.domain.exception;

public class NotFoundException extends RuntimeException {

	private String message;

	public NotFoundException(String message) {
		super(message);
		this.message = message;
		System.out.print("message:"+message);
	}
}
