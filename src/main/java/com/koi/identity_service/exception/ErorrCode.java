package com.koi.identity_service.exception;

public enum ErorrCode {
    INVALID_KEY(1001,"Invalid message key."),

    PASSWORD_INVALID(1004,"Password must be at least 6 characters."),
    USERNAME_INVALID(1003,"Username must be at least 8 characters."),
    UNCATEGORIZED_EXCEPTION(9999, "uncategorize exception erorr."),
    USER_EXISTED(1001, "User existed!");

    ;

    private int code;
    private  String message;

    ErorrCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
