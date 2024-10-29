package com.koi.identity_service.exception;

import com.koi.identity_service.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHander {
    @ExceptionHandler(value= Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse= new ApiResponse();

        apiResponse.setCode(ErorrCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErorrCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);

    }
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){

        String enumKey= exception.getFieldError().getDefaultMessage();
        ErorrCode erorrCode= ErorrCode.INVALID_KEY;

        try{
            erorrCode= ErorrCode.valueOf(enumKey);
        }catch(IllegalArgumentException ex){

        }
        ApiResponse apiResponse= new ApiResponse();


        apiResponse.setMessage(erorrCode.getMessage());
        apiResponse.setCode(erorrCode.getCode());

        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value= AppException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(AppException exception){
        ErorrCode erorrCode= exception.getErorrCode();
        ApiResponse apiResponse= new ApiResponse();

        apiResponse.setCode(erorrCode.getCode());
        apiResponse.setMessage(erorrCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);

    }

}
