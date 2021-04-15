package com.db.exception;

public class DBException extends RuntimeException {

	private final ErrorResponse error;

    protected DBException(ErrorResponse error, Throwable cause) {
        super(error.getMessage(), cause);
		this.error = error;
    }

    protected DBException(ErrorResponse error) {
        super(error.getMessage());
		this.error = error;
    }

    public ErrorResponse getError() {
        return this.error;
    }

}