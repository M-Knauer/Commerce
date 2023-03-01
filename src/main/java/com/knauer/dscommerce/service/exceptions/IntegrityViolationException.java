package com.knauer.dscommerce.service.exceptions;

public class IntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IntegrityViolationException(String msg) {
		super(msg);
	}
}
