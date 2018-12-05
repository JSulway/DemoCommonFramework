/**
 * 
 */
package com.demo.error.dto;

import java.util.List;

/**
 * 
 * Provides an error list dto
 * 
 * @author sulwayj
 *
 */
public class ErrorListDTO {
	
	private  List<ErrorDTO>  errorList;

	/**
	 * @return the errorList
	 */
	public List<ErrorDTO> getErrorList() {
		return errorList;
	}

	/**
	 * @param errorList the errorList to set
	 */
	public void setErrorList(List<ErrorDTO> errorList) {
		this.errorList = errorList;
	}
}
