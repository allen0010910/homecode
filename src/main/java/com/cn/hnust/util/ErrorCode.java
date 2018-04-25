package com.cn.hnust.util;

public enum ErrorCode {
	
	SYS_ERR("0000","系统错误，请联系管理员"),
	NULL_ERR("0001","不能为空"),
	UNKNOWN_ERROR("0002","未知异常");
	
	private String value;
	private String desc;
	
	private ErrorCode(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "["+this.value+"]"+this.desc;
	}
}
