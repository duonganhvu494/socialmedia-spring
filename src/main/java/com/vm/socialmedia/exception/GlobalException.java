package com.vm.socialmedia.exception;


import com.vm.socialmedia.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setEC(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setEM(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity
                .status(ErrorCode.UNCATEGORIZED_EXCEPTION.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        ApiResponse apiResponse = new ApiResponse();

        ErrorCode errorCode = exception.getErrorCode();
        apiResponse.setEC(errorCode.getCode());
        apiResponse.setEM(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        String message = exception.getMostSpecificCause().getMessage();

        String invalidValue = null;
        if (message != null && message.contains("from String")) {
            int start = message.indexOf("from String \"") + 13;
            int end = message.indexOf("\"", start);
            if (start > 0 && end > start) {
                invalidValue = message.substring(start, end);
            }
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setEC(ErrorCode.INVALID_ENUM_VALUE.getCode());
        apiResponse.setEM("Giá trị enum không hợp lệ" + (invalidValue != null ? ": " + invalidValue : ""));

        return ResponseEntity
                .status(ErrorCode.INVALID_ENUM_VALUE.getStatusCode())
                .body(apiResponse);
    }
}
