package com.example.identity_service.exception;

import com.example.identity_service.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(1001);
//        apiResponse.setMessage(exception.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){
        String enumKey=exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value= Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIED_EXCEPTION.getCode());
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value=AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value= ParseException.class)
    ResponseEntity<ApiResponse> handlingParseExcetion(AppException exception){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setCode(ErrorCode.WRONGTOKEN.getCode());
        return ResponseEntity.badRequest().body(apiResponse);
    }

}
