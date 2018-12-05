package com.demo.error;

/**
 * Common Error Codes for the system
 * @author sulwayj
 *
 */
public enum CommonErrorCode implements ErrorCode {
	
	RESOURCE_NOT_FOUND(1001),
	IO_EXCEPTION(1002),
	TIMEOUT_EXCEPTION(1003),
	UNKNOWN_ERROR(2000);
	
	private final int mCode;

	private CommonErrorCode(int number) {
		this.mCode = number;
	}
	
	public int getErrorCode() {
		return mCode;
	}

}
