package com.commerceApp.commerceApp.exceptions;

import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomisedExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public final ResponseEntity<Object> handleEmailAlreadyExistsException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public final ResponseEntity<Object> handleAllProductAlreadyExistsException(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductDoesNotExists.class)
    public final ResponseEntity<Object> handleAllProductDoesNotExists(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotActive.class)
    public final ResponseEntity<Object> handleAllProductNotActive(Exception ex, WebRequest request) throws Exception {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadCredentialException.class)
    public final ResponseEntity<Object> handleAllBadCredentials(Exception ex, HttpServletRequest httpServletRequest)throws Exception{
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), httpServletRequest.getPathInfo());
        return new ResponseEntity(errorDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDto errorDto = new ErrorDto("Validation failed", ex.getBindingResult().toString());
        return new ResponseEntity(errorDto, HttpStatus.BAD_REQUEST);
    }


}
