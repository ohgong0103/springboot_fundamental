package com.example.identity_service.exception;

public enum ErrorCode {
    USER_EXISTS(1001, "User already exists"),

    UNCATEGORIED_EXCEPTION(9999,"Uncategorized error"),
    USERNAME_INVALID(1002,"User must be at least 3 characters"),
    PASSWORD_INVALID(1003,"Password must be at least 8 character")

    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
