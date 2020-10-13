package com.intuit.craft.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice(assignableTypes = PaymentController.class)
public class PaymentControllerHandler {
	
	@Autowired
	MessageSource messageSource;
	
    private static final Logger logger = LoggerFactory.getLogger(PaymentControllerHandler.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseTO<ErrorMessageTO>> processMissingServletRequestParameterError(MissingServletRequestParameterException ex) {
        logger.error("Param is missing {}",ex);
        ResponseTO<ErrorMessageTO> errorMessageTOResponseTO = new ResponseTO<>();
        errorMessageTOResponseTO.setError(true);
        List<Error> errors = new ArrayList<>();
        Error error = new Error("Required parameter is missing "+ ex.getParameterName() + " param is missing");
        errors.add(error);
        errorMessageTOResponseTO.setErrors(errors);

        return new ResponseEntity<>(errorMessageTOResponseTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseTO<ErrorMessageTO>> processMissingServletRequestParameterError(MethodArgumentTypeMismatchException ex) {
        logger.error("Field missmatch {}",ex);
        ResponseTO<ErrorMessageTO> errorMessageTOResponseTO = new ResponseTO<>();
        errorMessageTOResponseTO.setError(true);
        List<Error> errors = new ArrayList<>();
        Error error = new Error("Field missmatch "+ ex.getName() + " type mismatch ");
        errors.add(error);
        errorMessageTOResponseTO.setErrors(errors);
        return new ResponseEntity<>(errorMessageTOResponseTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseTO<ErrorMessageTO>> processExceptionHandler(Exception ex) {
        logger.error("Exception occured due to:- {}",ex);
        ResponseTO<ErrorMessageTO> errorMessageTOResponseTO = new ResponseTO<>();
        errorMessageTOResponseTO.setError(true);
        List<Error> errors = new ArrayList<>();
        Error error = new Error("Server internal Error occured "+ ex.getMessage());
        errors.add(error);
        errorMessageTOResponseTO.setErrors(errors);
        return new ResponseEntity<>(errorMessageTOResponseTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseTO<ErrorMessageTO>> processMsgNotRedabeExceptionHandler(Exception ex) {
        logger.error("Exception occured due to:- {}",ex);
        ResponseTO<ErrorMessageTO> errorMessageTOResponseTO = new ResponseTO<>();
        errorMessageTOResponseTO.setError(true);
        List<Error> errors = new ArrayList<>();
        Error error = new Error("Bad Request "+ ex.getMessage());
        errors.add(error);
        errorMessageTOResponseTO.setErrors(errors);
        return new ResponseEntity<>(errorMessageTOResponseTO, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseTO<ErrorMessageTO>> processResourceAccessException(Exception ex) {
        logger.error("Request timed out exception occured :- {}",ex);
        ResponseTO<ErrorMessageTO> errorMessageTOResponseTO = new ResponseTO<>();
        errorMessageTOResponseTO.setError(true);
        String errorMessage = messageSource.getMessage("Request time out", null, Locale.getDefault());
        List<Error> errors = new ArrayList<>();
        Error error = new Error("Request time out "+ errorMessage);
        errors.add(error);
        errorMessageTOResponseTO.setErrors(errors);
        return new ResponseEntity<>(errorMessageTOResponseTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseTO<ErrorMessageTO>> processNullPointerException(NullPointerException ex) {
        logger.error("Param is missing {}",ex);
        ResponseTO<ErrorMessageTO> errorMessageTOResponseTO = new ResponseTO<>();
        errorMessageTOResponseTO.setError(true);
        List<Error> errors = new ArrayList<>();
        Error error = new Error("Missing field parameter "+ ex.getMessage());
        errors.add(error);
        errorMessageTOResponseTO.setErrors(errors);

        return new ResponseEntity<>(errorMessageTOResponseTO, HttpStatus.BAD_REQUEST);
    }
}
