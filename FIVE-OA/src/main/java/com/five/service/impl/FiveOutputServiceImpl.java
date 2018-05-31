package com.five.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.five.service.FiveDownLoadService;

@Service
public class FiveOutputServiceImpl {
	
	@Autowired
	private FiveDownLoadService fiveDownLoadService;

	/**
	 * 这个类用来在浏览器执行下载响应
	 * 
	 * @throws IOException
	 * 
	 */
	public String doLoadResponse(HttpServletResponse response, String tableName) {
		
//		tableName = "five_address";
		System.out.println("tableName="+tableName);
		fiveDownLoadService.doDownLoadExcel(tableName);
		
		String path = "D:\\downLoadExcel\\" + tableName + ".xls";
		OutputStream out = null;
		byte[] content;
		File file = new File(path);
		if (file.exists()) {
		try {
			InputStream is = new FileInputStream(file);
			response.reset();// 清除response中的缓存信息
			response.setHeader("Content-Disposition", "attachment;filename='" + tableName + ".xls");
			response.setContentType("application/force-download");
			response.setHeader("Content-Length", String.valueOf(file.length()));// 告诉浏览器下载文件的大小
			out = response.getOutputStream();
			content = new byte[1024];
			int length = 0;
			while ((length = is.read(content)) != -1) {
				out.write(content, 0, length);
			}
			out.write(content);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				out = null;
			}
		}
		return "download finish!";
		}else
		return null;
	}
}