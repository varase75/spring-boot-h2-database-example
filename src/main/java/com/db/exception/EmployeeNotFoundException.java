package com.db.exception;

public class EmployeeNotFoundException extends DBException {

    private static final String EXCEPTION_CODE = "EMPLOYEE_NOT_FOUND";

	public EmployeeNotFoundException(String message, Class sourceClass) {
		super(createError(message, sourceClass));
	}

	private static ErrorResponse createError(String message, Class sourceClass) {
		return new ErrorResponse(EXCEPTION_CODE, message, ErrorResponse.Severity.ERROR, sourceClass.toString());
	}

}