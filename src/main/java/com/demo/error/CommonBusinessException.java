/**
 * 
 */
package com.demo.error;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

/**
 * This class provides custom checked exception class by extending the Exception class 
 * @author sulwayj
 *
 */
public class CommonBusinessException extends Exception {
	private static final Logger logger = Logger.getLogger(CommonBusinessException.class);
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int errorCode;
	private final String errorMessage;
	private final String errorDescription;
	private final Timestamp timestamp;
	
	/**
	 * 
	 * @param errorCode
	 * @param cause
	 * @param message
	 */
	public CommonBusinessException(ErrorCode errorCode, Throwable cause, String message) {
		this.errorCode = errorCode.getErrorCode();
		this.errorMessage = cause.getMessage();
		this.errorDescription = "ErrorCode: " + errorCode + ". Error Message: " 
								+ message + ". ErrorDetail:" + errorMessage;
		this.timestamp = new Timestamp(System.currentTimeMillis());
		logger.error(errorDescription, cause);
	}
	
	/**
	 * 
	 * @param errorCode
	 * @param cause
	 */
	public CommonBusinessException(ErrorCode errorCode, Throwable cause) {
		this.errorCode = errorCode.getErrorCode();
		this.errorMessage = cause.getMessage();
		this.errorDescription = "ErrorCode: " + errorCode +  ". ErrorDetail:" + errorMessage;
		this.timestamp = new Timestamp(System.currentTimeMillis());
		logger.error(errorDescription, cause);
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	}
