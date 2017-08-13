package com.noteanalyzer.mvc.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

public class RequestStatusModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3323477895326245420L;
	
	private String status;
	private String errorCode;
	private String errorMessage;
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	
}
