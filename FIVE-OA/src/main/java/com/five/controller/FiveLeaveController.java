package com.five.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;
import com.five.entity.FiveLeave;
import com.five.entity.LeaveDictionary;
import com.five.service.FiveLeaveService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/fiveLeave")
public class FiveLeaveController {

	@Autowired
	private FiveLeaveService fiveLeaveService;

	/**
	 * 显示请假列表
	 * 
	 * @return
	 */
	@RequestMapping("LeaveUI")
	public String doLeaveUI() {
		return "five/leave_list";
	}

	/**
	 * 审核列表
	 * 
	 * @return
	 */
	//@RequiresPermissions("leave:approved")
	@RequestMapping("doLeaveAuthority")
	public String doLeaveAuthority() {
		return "five/five_leave_authority";
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
		PageInfo<FiveLeave> pageInfo = fiveLeaveService.findPageObjects(pageCurrent, username);
		jsonResult.setData(pageInfo);
		return jsonResult;
	}

	/**
	 * 根据查询字典表
	 * 
	 * @return
	 */
	@RequestMapping("doLeaveDictionary")
	@ResponseBody
	public JsonResult doLeaveDictionary(Integer id) {
		JsonResult jsonResult = JsonResult.instance();
		LeaveDictionary leaveDictionary = fiveLeaveService.doFindLeaveDictionary(id);
		jsonResult.setData(leaveDictionary);
		return jsonResult;
	}

	/**
	 * 查询字典表
	 * 
	 * @return
	 */
	@RequestMapping("doLeaveDictionaryList")
	@ResponseBody
	public JsonResult doLeaveDictionaryList() {
		JsonResult jsonResult = JsonResult.instance();
		List<LeaveDictionary> list = fiveLeaveService.doFindLeaveDictionaryList();
		jsonResult.setData(list);
		return jsonResult;
	}

	/**
	 * 返回添加/修改页面
	 * 
	 * @return
	 */
	@RequestMapping("EditUI")
	public String doEditUI() {
		return "five/five_leave_edit";
	}

	/**
	 * 添加方法
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doInsertObject(FiveLeave entity) {
		JsonResult jsonResult = JsonResult.instance();
		int row = fiveLeaveService.insertObject(entity);
		if (row > 0) {
			jsonResult.setMessage("添加成功!");
		} else {
			jsonResult.setMessage("添加失败!");
		}
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
		JsonResult jsonResult = JsonResult.instance();
		FiveLeave leave = fiveLeaveService.doFindObjectById(id);
		jsonResult.setData(leave);
		return jsonResult;
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(FiveLeave entity) {
		JsonResult jsonResult = JsonResult.instance();
		int row = fiveLeaveService.updateObject(entity);
		if (row > 0) {
			jsonResult.setMessage("修改成功!");
		} else {
			jsonResult.setMessage("修改失败!");
		}
		return jsonResult;
	}

	/*	*//**
			 * 请假处理判断权限
			 * 
			 * @return
			 *//*
				 * @RequiresPermissions("leave:approved")
				 * 
				 * @RequestMapping("doLeaveAuthority")
				 * 
				 * @ResponseBody public JsonResult doLeaveAuthority() { JsonResult jsonResult =
				 * JsonResult.instance(); fiveLeaveService.doLeaveAuthority(); return
				 * jsonResult; }
				 */

	/**
	 * 通过审核
	 * 
	 * @return
	 */
	@RequestMapping("doUpdateLeaveAuthority")
	@ResponseBody
	public JsonResult doUpdateLeaveAuthority(FiveLeave entity) {
		JsonResult jsonResult = JsonResult.instance();
		int rows = fiveLeaveService.updateAuthority(entity);
		if (rows > 0) {
			jsonResult.setMessage("审核成功");
		} else {
			jsonResult.setMessage("审核失败");
		}
		return jsonResult;
	}

	/**
	 * 修改驳回信息
	 * 
	 * @return
	 */
	@RequestMapping("doUpdateLeaveRemark")
	@ResponseBody
	public JsonResult doUpdateLeaveRemark(FiveLeave entity) {
		JsonResult jsonResult = JsonResult.instance();
		int rows = fiveLeaveService.updateRemark(entity);
		if (rows > 0) {
			jsonResult.setMessage("驳回成功");
		} else {
			jsonResult.setMessage("驳回失败");
		}
		return jsonResult;
	}

	/**
	 * @Description:转换页面提交的Date类型的参数
	 * @param binder
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true:允许输入空值，false:不能为空值
	}

}
