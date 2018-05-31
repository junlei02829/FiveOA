package com.five.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.exception.ServiceException;
import com.five.common.util.JsonResult;
import com.five.common.vo.UserRole;
import com.five.entity.FiveUser;
import com.five.service.FiveUserService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * 用户控制层
 */
@Controller
@RequestMapping("/user/")
public class FiveUserController {

	@Autowired
	private FiveUserService userService;

	/**
	 * 显示用户列表
	 * 
	 * @return
	 */

	@RequestMapping("listUI")
	public String doUserUI() {
		return "five/user_list";
	}

	/**
	 * 加载编辑列表
	 * 
	 * @return
	 */
	@RequestMapping("editUI")
	public String EditUI() {
		return "five/user_edit";
	}

	/**
	 * 无条件查询所有用户记录
	 * 
	 * @return
	 */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(Integer pageCurrent) {
		JsonResult instance = JsonResult.instance();
		PageInfo<FiveUser> info = userService.findObjects(pageCurrent);
		instance.setData(info);
		return instance;
	}

	/**
	 * 基于username查询所有用户记录
	 * 
	 * @return
	 */
	@RequestMapping("findObjectByName")
	@ResponseBody
	public JsonResult findObjectByName(String username) {
		JsonResult instance = JsonResult.instance();
		PageInfo<FiveUser> info = userService.findObjectByName(username);
		instance.setData(info);
		return instance;
	}

	/**
	 * 基于ID查询用户 用于修改用户
	 * 
	 * @return
	 */
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		JsonResult instance = JsonResult.instance();
		UserRole userRole = userService.findObjectById(id);
		instance.setData(userRole);
		return instance;
	}

	/**
	 * 
	 * 基于id修改用户的状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("doUpdateTypeById")
	@ResponseBody
	public JsonResult doUpdateTypeById(Integer id) {

		String msg;

		int rows = userService.updateTypeById(id);

		if (rows == 1) {

			msg = "修改状态成功！";
		} else {

			msg = "修改状态失败！";
		}

		return new JsonResult(msg);
	}

	/**
	 * 
	 * 基于id删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteObjectById")
	@ResponseBody
	public JsonResult deleteObjectById(Integer id) {

		String msg;

		int rows = userService.deleteObjectById(id);

		if (rows == 1) {

			msg = "删除成功！";
		} else {

			msg = "删除失败！";
		}

		return new JsonResult(msg);
	}

	/**
	 * 
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(FiveUser user, String roleIds) {
		String msg;
		int rows = userService.updateObject(user, roleIds);
		if (rows == 1) {
			msg = "修改用户成功！";
		} else {

			msg = "修改用户失败！";
		}

		return new JsonResult(msg);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("doSaveObjects")
	@ResponseBody
	public JsonResult doInsertObject(FiveUser user, String roleIds) {

		String msg;

		if (user == null) {

			throw new ServiceException("提交的user 不能为空!!");
		}

		if (roleIds == null) {

			throw new ServiceException("提交的roleIds 不能为空!!");
		}

		int rows = userService.saveObject(user, roleIds);

		if (rows == 1) {

			msg = "添加成功！";
		} else {

			msg = "添加失败！";
		}

		return new JsonResult(msg);
	}

	/**
	 * 基于id查询用户角色关系
	 * 
	 * @param user
	 * @return
	 * 
	 * 		@RequestMapping("doFindRoleIdsByUserId")
	 * @ResponseBody public JsonResult findRoleIdsByUserId(Integer userId){
	 * 
	 * 
	 *               List<Integer> rolesIds =
	 *               userService.findRoleIdsByUserId(userId);
	 * 
	 *               return new JsonResult(rolesIds, "查询成功！"); }
	 */

}
