package com.empresa.exception;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.empresa.dto.ApiErrorDto;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ObjectNotFoundExceptionHandler.class)
	public final ResponseEntity<ApiErrorDto> objectNotFoundExceptionHandler(ObjectNotFoundExceptionHandler ex, WebRequest request){
		ApiErrorDto error = new ApiErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ApiErrorDto>(error, HttpStatus.NOT_FOUND);			
	}
	
	@ExceptionHandler(ObjectAlreadyFoundHandler.class)
	public final ResponseEntity<ApiErrorDto> objectItsCreatedExceptionHandler(ObjectAlreadyFoundHandler ex, WebRequest request){
		ApiErrorDto error = new ApiErrorDto(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ApiErrorDto>(error, HttpStatus.NOT_ACCEPTABLE);			
	}
	
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ApiErrorDto> nullPointerExceptionHandler(NullPointerException ex, WebRequest request){
		ApiErrorDto error = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ApiErrorDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);			
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiErrorDto> exceptionHandler(Exception ex, WebRequest request){
		ApiErrorDto error = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ApiErrorDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);			
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiErrorDto error = new ApiErrorDto(status.name(), status.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ApiErrorDto error = new ApiErrorDto(status.name(), status.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException ex, WebRequest request) {
		ApiErrorDto error = new ApiErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String m = "";
		for( ObjectError err : ex.getBindingResult().getAllErrors()){
			m+=" - "+err.getDefaultMessage();
		}
		ApiErrorDto error = new ApiErrorDto(status.name(), status.toString(), m , request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiErrorDto error = new ApiErrorDto(status.name(), status.toString(), ex.getMessage(), request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<ApiErrorDto> emptyResultDataAccessExceptionHandler(EmptyResultDataAccessException ex, WebRequest request){
		ApiErrorDto error = new ApiErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.toString(), "Not found ...", request.getDescription(false));
        return new ResponseEntity<ApiErrorDto>(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ApiErrorDto> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex, WebRequest request){
		ApiErrorDto error = new ApiErrorDto(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.toString(), "Integrity database violation!  :;" , request.getDescription(false));
        return new ResponseEntity<ApiErrorDto>(error, HttpStatus.NOT_ACCEPTABLE);
    }
	
	@ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ApiErrorDto> noSuchElementExceptionHandler(NoSuchElementException ex, WebRequest request){
		ApiErrorDto error = new ApiErrorDto(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.toString(), "Object not found!", request.getDescription(false));
        return new ResponseEntity<ApiErrorDto>(error, HttpStatus.NOT_ACCEPTABLE);
    }
	
	
	
	
}
