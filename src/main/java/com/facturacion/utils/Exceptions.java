package com.facturacion.utils;

import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class Exceptions {
	
	final static Logger logger = LogManager.getLogger(Exceptions.class);

	@ExceptionHandler({PersistenceException.class, TransactionSystemException.class})
	public ResponseEntity<?> exceptionPersistent(Exception e) {
		System.out.print(e.getCause());
		logger.error("Error!",e);
		
		String message = "";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
	    if (e.getCause() instanceof RollbackException) {
	    	
	    	RollbackException rollbackException = (RollbackException) e.getCause();
	    	
	    	if (rollbackException.getCause() instanceof ConstraintViolationException) {
	    		
	    		Set<ConstraintViolation<?>> violations = ((ConstraintViolationException)rollbackException.getCause()).getConstraintViolations();
		    	
	    		for (ConstraintViolation<?> v : violations) {
		    		message = v.getMessage();
		    		break;
		    	}
		    	
	    	}
	    	
	    	if (message != "") {
	    		
				status = HttpStatus.BAD_REQUEST;
				
			} 
	    	
	    } else if (e.getCause() instanceof EntityNotFoundException) {
	    	
	    	message = "Registro no encontrado";
	    	status  = HttpStatus.NOT_FOUND;
	    	
	    }
	    
	    if (message == "") {
			
			message = "Error al realizar la transacción";
			
		}
	    
	    return new ResponseEntity<>(message, status);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> deleteRecord(Exception e) {
		
		logger.error("Error!",e);
		
		String message = "Registro no encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		   
	    return new ResponseEntity<>(message, status);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> httpMessageNotReadableException(Exception e) {
		
		logger.error("Error!",e);
		
		String message = "Parámetros no válidos";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		   
	    return new ResponseEntity<>(message, status);
	}
	
}
