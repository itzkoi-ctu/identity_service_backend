package com.koi.identity_service.exception;

public enum ErrorCode {
    INVALID_KEY(1001,"Invalid message key."),

    PASSWORD_INVALID(1004,"Password must be at least 6 characters."),
    USERNAME_INVALID(1003,"Username must be at least 8 characters."),
    UNCATEGORIZED_EXCEPTION(9999, "uncategorize exception erorr."),
    USER_EXISTED(1001, "User existed!"),
    USER_NOT_EXISTED(1001, "User not existed!"),
    UNAUTHENTICATED(1009, "Nonauthenticated!")

    ;

    private int code;
    private  String message;

    ErrorCode(int code, String message) {
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
