package com.cn.hnust.util;

public class MyUtils {
	public static boolean StringIsNull(String str){
		if(str !=null && !str.equals("")){
			return false;
		}
		return true;
	}
	
	public static String imageFormat(String extension){
		if(extension.equals("jpeg")){
			extension="jpg";
		}else if(extension.equals("png")){
			extension="png";
		}else if(extension.equals("gif")){
			extension="gif";
		}
		return extension;
	}
}
