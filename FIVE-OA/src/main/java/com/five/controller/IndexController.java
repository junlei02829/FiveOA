package com.five.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;

/**
 * 进入住页面
 * @author lizhichao
 *
 */
@RequestMapping("/")
@Controller
public class IndexController {
	
	@RequestMapping("indexUI")
	public String indexUI() {
		return "index";
	}
	
	
	@RequestMapping("startUI")
	public String startUI() {
		return "common/start";
	}
	
	@RequestMapping("pageUI")
	public String pageUI() {
		return "common/page";
	}
	//下载excel连接
	@RequestMapping("downloadUI")
	public String uploadUI() {
		return "five/download_list";
	}
}

