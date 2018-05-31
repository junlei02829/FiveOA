package com.five.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;
import com.five.service.FivePhoneService;


@Controller
@RequestMapping("/five/")
public class PhoneController {
	@Autowired
	private FivePhoneService service;

	@RequestMapping("phone")
	public String listUI() {
		return "five/phone";
	}

	@RequestMapping("address")
	@ResponseBody
	public JsonResult address(String address){
		JsonResult instance = JsonResult.instance();
		instance.setData(service.findPhoneByName(address));
		return instance;
	}
	
	@RequestMapping("doFindPhones")
	@ResponseBody
	public JsonResult findPhones(){
		JsonResult instance = JsonResult.instance();
		instance.setData(service.findPhones());
		return instance;
	}

}
