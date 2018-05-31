package com.five.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;
import com.five.entity.FiveInformation;
import com.five.entity.FiveUser;
import com.five.service.FiveInformationService;
import com.five.service.FiveUserService;
import com.github.pagehelper.PageInfo;

/**
 * 站内信控制层
 * 
 * @author lizhichao
 *
 */
@Controller
@RequestMapping("fiveInformation")
public class FiveInformationController {

	@Autowired
	private FiveInformationService fiveInformationService;
	@Autowired
	private FiveUserService fiveUserService;

	/**
	 * 站内信
	 * 
	 * @return
	 */
	@RequestMapping("doInformationUI")
	public String doInformationUI() {
		return "five/five_information";
	}

	/**
	 * 公告
	 * 
	 * @return
	 */
	@RequestMapping("doInfoUI")
	public String doInfoUI() {
		return "five/five_info";
	}
	
	
	/**
	 * 站内添加信页面
	 * 
	 * @return
	 */
	@RequestMapping("doInformationAddUI")
	public String doInformationAddUI() {
		return "five/five_information_add";
	}

	/**
	 * 返回请假表的所有信息
	 * 
	 * @return
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindObjects(Integer pageCurrent, String username) {
		JsonResult jsonResult = JsonResult.instance();
		PageInfo<FiveInformation> pageInfo = fiveInformationService.findPageObjects(pageCurrent, username);
		jsonResult.setData(pageInfo);
		return jsonResult;
	}
	
	
	/**
	 * 查詢
	 * 
	 * @return
	 */
	@RequestMapping("doFindsObjects")
	@ResponseBody
	public JsonResult doFindPageObjects() {
		JsonResult jsonResult = JsonResult.instance();
		List<FiveInformation> list = fiveInformationService.doFindPageObjects();
		jsonResult.setData(list);
		return jsonResult;
	}
	
	
	
	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		JsonResult result = JsonResult.instance();
		FiveInformation information = fiveInformationService.doFindObjectById(id);
		result.setData(information);
		return result;
	}

	/**
	 * 添加方法
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doInsertObject(FiveInformation entity) {
		JsonResult jsonResult = JsonResult.instance();
		int row = fiveInformationService.insertObject(entity);
		if (row > 0) {
			jsonResult.setMessage("发送成功!");
		} else {
			jsonResult.setMessage("发送失败!");
		}
		return jsonResult;
	}
	
	/**
	 * 返回所有用户
	 * @return
	 */
	@RequestMapping("doUserList")
	@ResponseBody
	public JsonResult doUserList() {
		JsonResult jsonResult = JsonResult.instance();
		List<FiveUser> list = fiveUserService.findObjects();
		jsonResult.setData(list);
		return jsonResult;
	}
	
	

}
