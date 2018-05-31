package com.five.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;
import com.five.entity.FiveDownLoadExcel;
import com.five.service.FiveDownLoadService;
import com.five.service.impl.FiveOutputServiceImpl;

@Controller
@RequestMapping("/downLoad/")
public class FiveDownLoadController {

	@Autowired
	private FiveDownLoadService fiveDownLoadService;
	@Autowired
	FiveOutputServiceImpl fiveOutputServiceImpl;
	
	
	@RequestMapping("downLoadUI")
	@ResponseBody
	public JsonResult downLoadUI(HttpServletResponse response,String tableName){
		String result = fiveOutputServiceImpl.doLoadResponse(response, tableName);
		if (result == null) {
			return new JsonResult("下载失败，文件不存在");
		} 
		return new JsonResult(result);
		
	}
	
	@RequestMapping("findAllTableName")
	@ResponseBody
	public JsonResult findAllTableName(){
		List<FiveDownLoadExcel> list = fiveDownLoadService.dofindAllTableName();
		return new JsonResult(list);
	}
}
