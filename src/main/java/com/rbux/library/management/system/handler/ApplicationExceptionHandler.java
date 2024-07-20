package com.rbux.library.management.system.handler;

import com.rbux.library.management.system.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    /**
     * method to handle global IllegalArgumentException
     * @param ex IllegalArgumentException
     * @return ErrorResponseDto
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage(), HttpStatus.BAD_GATEWAY.value()),HttpStatus.BAD_GATEWAY);
    }

    /**
     * Method to handle RuntimeException
     * @param ex RuntimeException
     * @return ErrorResponseDto
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage(), HttpStatus.BAD_GATEWAY.value()),HttpStatus.BAD_GATEWAY);
    }
}
