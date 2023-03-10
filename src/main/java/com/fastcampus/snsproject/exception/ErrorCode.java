package com.fastcampus.snsproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),

    USER_NOT_FOUNED(HttpStatus.NOT_FOUND, "User Not Founded"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Password is invalid"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post Not Founded"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "Permission is invalid"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is invalid")

    ;

    private HttpStatus status;
    private String message;
}
