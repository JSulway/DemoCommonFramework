/**
 * 
 */
package com.demo.error.handler;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;

import com.demo.error.CommonErrorCode;
import com.demo.error.CommonRuntimeException;
import com.demo.error.dto.ErrorDTO;
import com.demo.error.dto.ErrorListDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Transform the errors to be return to caller
 * @author sulwayj
 *
 */
public class ErrorTransformer {
	private final Logger logger = Logger.getLogger(ErrorTransformer.class);
	
	/**
	 * Transforms the error into json format 
	 * @param ex
	 * @return
	 */
	public String transformErrorToJSON(Exception ex) {
		ErrorDTO errorDTO = new ErrorDTO();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (ex instanceof CommonRuntimeException) {
    		CommonRuntimeException exCommon = (CommonRuntimeException) ex;
    		errorDTO.setErrorCode(exCommon.getErrorCode());
    		errorDTO.setErrorDescription(exCommon.getErrorDescription());
    		errorDTO.setErrorMessage(exCommon.getErrorMessage());
    		errorDTO.setTimestamp(exCommon.getTimestamp());
    	} else {
    		errorDTO.setErrorCode(CommonErrorCode.UNKNOWN_ERROR.getErrorCode());
    		if(ex.getCause() != null){	    			
    			errorDTO.setErrorDescription(ex.getCause().getMessage());
    		}else{
    			errorDTO.setErrorDescription(ex.getMessage());
    		}
    		errorDTO.setErrorMessage(ex.getMessage());
    		errorDTO.setTimestamp(Timestamp.valueOf(formater.format(new Date())));
    	}
		
		ErrorListDTO errorListDTO = new ErrorListDTO();
		errorListDTO.setErrorList(Arrays.asList(errorDTO));
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(formater);
		String jsonExString = null;
		try {
			jsonExString = mapper.writeValueAsString(errorListDTO);
		} catch (JsonProcessingException e) {
			jsonExString = "{\"errorList\":[{\"errorCode\":9999,\"errorMessage\":\"Unexpected exception\",\"errorDescription\":\"Unexpected exception\",\"timestamp\":\"" + Timestamp.valueOf(formater.format(new Date()))+ "\"\"}]}";
		}
		logger.info("jsonExString : " + jsonExString);
		return jsonExString;
		
	}
	
	/**
	 * Transforms the error into json format 
	 * @param ex
	 * @return
	 */
	public ErrorDTO transformErrorToErrorDto(Exception ex) {
		ErrorDTO errorDTO = new ErrorDTO();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (ex instanceof CommonRuntimeException) {
    		CommonRuntimeException exCommon = (CommonRuntimeException) ex;
    		errorDTO.setErrorCode(exCommon.getErrorCode());
    		errorDTO.setErrorDescription(exCommon.getErrorDescription());
    		errorDTO.setErrorMessage(exCommon.getErrorMessage());
    		errorDTO.setTimestamp(exCommon.getTimestamp());
    	} else {
    		errorDTO.setErrorCode(CommonErrorCode.UNKNOWN_ERROR.getErrorCode());
    		if(ex.getCause() != null){	    			
    			errorDTO.setErrorDescription(ex.getCause().getMessage());
    		}else{
    			errorDTO.setErrorDescription(ex.getMessage());
    		}
    		errorDTO.setErrorMessage(ex.getMessage());
    		errorDTO.setTimestamp(Timestamp.valueOf(formater.format(new Date())));
    	}
		return errorDTO;
		
	}

}
