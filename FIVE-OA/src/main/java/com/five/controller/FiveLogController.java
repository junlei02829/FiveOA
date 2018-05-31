package com.five.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;
import com.five.entity.FiveLog;
import com.five.service.FiveLogService;
import com.github.pagehelper.PageInfo;

@RequestMapping("/log/")
@Controller
public class FiveLogController {
	
	@Autowired
	private FiveLogService fiveLogService;
	@RequestMapping("listUI")
	public String listUI() {
		return "five/log_list";
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		System.out.println(username+"\\"+pageCurrent);
		JsonResult result = JsonResult.instance();
		PageInfo<FiveLog> pageInfo = fiveLogService.findPageLogs(username,pageCurrent);
		result.setData(pageInfo);
		
		return result;
	}
}