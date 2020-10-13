package com.intuit.craft.demo.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ResponseTO<T extends Serializable> implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The error.
     */
    private boolean error;

    /**
     * The errors.
     */
    private List<Error> errors;

    /**
     * The data.
     */
    private T data;

    public ResponseTO(T result) {
    }
}