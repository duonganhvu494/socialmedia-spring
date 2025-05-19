package com.vm.socialmedia.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1, "Lỗi hệ thống!", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ENUM_VALUE(1, "Giá trị enum không hợp lệ", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1,"Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NON_EXISTED(1,"Không tìm thấy người dùng", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1, "Mật khẩu không chính xác" , HttpStatus.BAD_REQUEST),

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
