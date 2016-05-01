package com.lm.web.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;


public class BaseVo implements Serializable{
	
	public final static String VO_KEY = "list";
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -7247823509891712543L;
	private String rslt;
	private String msg;

	public BaseVo() {
		super();
	}

	public BaseVo(String ackResult, String msg) {
		super();
		this.rslt = ackResult;
		this.msg = msg;
	}

	public String getRslt() {
		return rslt;
	}
	
	public void setRslt(String result) {
		this.rslt = result;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
