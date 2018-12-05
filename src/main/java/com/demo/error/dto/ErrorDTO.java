/**
 * 
 */
package com.demo.error.dto;

import java.sql.Timestamp;

/**
 * 
 * Provides and Error DTO object
 * 
 * @author sulwayj
 *
 */
public class ErrorDTO {
	
	private  int errorCode;
	private  String errorMessage;
	private  String errorDescription;
	private  Timestamp timestamp;
	
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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
