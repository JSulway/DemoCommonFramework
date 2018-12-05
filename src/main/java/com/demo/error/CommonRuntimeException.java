/**
 * 
 */
package com.demo.error;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

/**
 * This class provides custom unchecked exception class by extending the RuntimeException class 
 * 
 * @author jaiswar
 *
 */

public class CommonRuntimeException extends RuntimeException {
	
	private static final Logger logger = Logger.getLogger(CommonRuntimeException.class);
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int errorCode;
	private final String errorMessage;
	private final String errorDescription;
	private final Timestamp timestamp;

	public CommonRuntimeException(ErrorCode errorCode, Throwable cause, String message) {
		this.errorCode = errorCode.getErrorCode();
		this.errorMessage = cause.getMessage();
		this.errorDescription = "ErrorCode: " + errorCode + ". Error Message: " 
				+ message + ". ErrorDetail:" + errorMessage;
		this.timestamp = new Timestamp(System.currentTimeMillis());
		logger.error(errorDescription, cause);
	}
	
	public CommonRuntimeException(ErrorCode errorCode, Throwable cause) {
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
