package com.five.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.five.common.util.JsonResult;
import com.five.common.util.Utility;

/**
 * 上传控制器
 * 
 * @author lizhichao
 *
 */
@RequestMapping("/upload")
@Controller
public class UploadController {

	@RequestMapping("/uploadFiles")
	@ResponseBody
	public JsonResult uploadFiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
		JsonResult jsonResult = JsonResult.instance();
		boolean flag = true;
		String name = "";
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
			if (fileName.contains(",")) {
				flag = false;
				name = fileName;
				break;
			}
		}
		if (flag == true) {
			Map<String, Object> resultMap = Utility.getMultipartUpload("upload", files, request);
			if (resultMap.get("result") != null && resultMap.get("result").toString().equals("success")) {
				jsonResult.setData(resultMap);
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("leaveAnnexName", name);
			map.put("fail", "1");
			jsonResult.setData(map);
		}

		return jsonResult;
	}

	/**
	 * 下载文件
	 * 
	 * @param leaveAnnexPath
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/download")
	public JsonResult downloadFile(@RequestParam("leaveAnnexPath") String leaveAnnexPath, HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = JsonResult.instance();
		String result = Utility.downloadSingleFile(leaveAnnexPath, request, response);
		if (result == null) {
			jsonResult.setMessage("下载失败，文件不存在");
		} else if (result.equals("fail") || result.equals("none")) {
			jsonResult.setMessage("下载失败，文件不存在");

		}
		return jsonResult;
	}

	/**
	 * 批量下载 并压缩
	 * @param leaveAnnexPath
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/downloadAll")
	public JsonResult downloadFileAll(@RequestParam("leaveAnnexPath") String leaveAnnexPath, HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = JsonResult.instance();
			String downloadtemp = Utility.downloadtemp(leaveAnnexPath);
			String result = Utility.downloadSingleFile(downloadtemp, request, response);
			if (result != null) {
				jsonResult.setMessage("下载成功");
			} else{
				jsonResult.setMessage("下载失败，文件不存在");
			}

		return jsonResult;
	}
	
}
