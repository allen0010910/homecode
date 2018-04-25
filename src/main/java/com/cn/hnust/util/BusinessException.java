package com.cn.hnust.util;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8244909942447978793L;
	
	private String code;
	private String msg;
	
	public BusinessException(ErrorCode obj){
		super(obj.toString());
		msg=obj.getDesc();
		code=obj.getValue();
	}
	
	public BusinessException(String msg){
		this.msg=msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
