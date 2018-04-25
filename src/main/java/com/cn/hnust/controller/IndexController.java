package com.cn.hnust.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.util.MyUtils;

import sun.misc.BASE64Decoder;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@RequestMapping(value = "")
	public String defaultIndex() {
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/uploadImg", method = { RequestMethod.POST })
	public @ResponseBody String uploadImg(HttpServletRequest request, @RequestParam(value = "img") MultipartFile pic) {
		// String username=request.getParameter("username");
		// Integer count=userService.insert(username, password);
		// 获取图片原始文件名
		String originalFileName = pic.getOriginalFilename();
		String name = "" + System.currentTimeMillis();
		// 获取后缀
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
		// 相对路径
		String path = "/WEB-INF/sources/upload/" + name + extension;
		// 绝对路径
		String url = request.getSession().getServletContext().getRealPath("") + path;
		// 网站路径
		String webUrl = request.getRequestURL() + path;
		webUrl = webUrl.replaceAll("http://", "").replaceAll("/uploadImg/WEB-INF", "");
		webUrl = "{\"webUrl\":\"" + webUrl + "\",\"path\":\"/sources/upload/" + name + extension + "\"}";
		File dir = new File(url);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 上传图片
		try {
			pic.transferTo(new File(url));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return webUrl;
	}

	@RequestMapping(value = "base64Upload", method = { RequestMethod.POST })
	public @ResponseBody String base64Upload(HttpServletRequest request) {
		String base64Str = request.getParameter("base64Str");
		if (MyUtils.StringIsNull(base64Str)) {
			return "请上传图片";
		}
		try {
			// 后缀
			String extension = base64Str.substring(base64Str.indexOf("/") + 1, base64Str.indexOf(";"));
			// 处理前缀，不然图片会损坏
			String imgContent = base64Str.substring(base64Str.indexOf("base64,") + 7, base64Str.length());
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bs = decoder.decodeBuffer(imgContent);
			for (int i = 0; i < bs.length; i++) {
				if (bs[i] < 0) {
					// 修正异常：+号可能会变成空格
					bs[i] += 256;
				}
			}
			String name = "" + System.currentTimeMillis();
			// 获取后缀
			extension = MyUtils.imageFormat(extension);
			// 相对路径
			String path = "/WEB-INF/sources/upload/";
			// 绝对路径
			String url = request.getSession().getServletContext().getRealPath("") + path + name + "." + extension;
			// 网站路径
			String webUrl = request.getRequestURL() + path + name + "." + extension;
			webUrl = webUrl.replaceAll("http://", "").replaceAll("/base64Upload/WEB-INF", "");
			webUrl = "{\"webUrl\":\"" + webUrl + "\",\"path\":\"/sources/upload/" + name + extension + "\"}";
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			OutputStream os = new FileOutputStream(new File(url));
			os.write(bs);
			os.flush();
			os.close();
			return webUrl;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
