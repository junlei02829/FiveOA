package com.five.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;
import com.five.entity.FiveDepartment;
import com.five.entity.FiveEntityForZTree;
import com.five.entity.FiveRole;
import com.five.service.FiveRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 角色控制层
 * @author 000
 * 用JsonResult封装返回值
 */
@Controller
@RequestMapping("/role/")
public class FiveRoleController {

	@Autowired
	FiveRoleService fiveRoleService;
	/**
	 * 查询
	 * 查询角色名包含roleName的所有角色(模糊查询)
	 * @param roleName-->角色名
	 * @return
	 */
	@RequestMapping("doFindFiveRoleByName")
	@ResponseBody
	public JsonResult doFindFiveRoleByName(Integer pageCurrent,String roleName){

		JsonResult jsonResult=JsonResult.instance();

		PageInfo<FiveRole> pageInfo = fiveRoleService.findFiveRoleByName(pageCurrent,roleName);
		jsonResult.setData(pageInfo);

		return jsonResult;
	}

	/**
	 * 禁用启用(类似删除)
	 * @param id-->从前台获取的角色id
	 * @param deleteFlag-->要写入数据库的0或1(前端改值，服务器性能好~…~)
	 * @return
	 */
	@RequestMapping("doDeleteFlagById")
	@ResponseBody
	public JsonResult doDeleteFlagById(Integer id, Integer deleteFlag){
		JsonResult jsonResult=JsonResult.instance();
		System.out.println("id="+id+"deleteFlag="+deleteFlag);
		fiveRoleService.deleteFlagById(id, deleteFlag);
		return jsonResult;
	}

	/**
	 * 插入
	 * @param fiveRole-->从前期获取的角色类型的参数
	 * @return
	 */
	@RequestMapping("doInsertFiveRole")
	@ResponseBody
	public JsonResult doInsertFiveRole(FiveRole fiveRole,Integer departmentId,Integer areaId,Integer userId){
		System.out.println(fiveRole);
		JsonResult jsonResult=JsonResult.instance();
		fiveRoleService.insertFiveRole(fiveRole, departmentId,areaId,userId);
		return jsonResult;
	}

	/**
	 * 修改
	 * @param fiveRole-->从前端获取的角色类型的参数
	 * @return
	 */
	@RequestMapping("doUpdateFiveRoleById")
	@ResponseBody
	public JsonResult doUpdateFiveRoleById(FiveRole fiveRole){
		JsonResult jsonResult=JsonResult.instance();
		fiveRoleService.updateFiveRoleById(fiveRole);
		return jsonResult;
	}

	/*============================listUI()================================*/
	@RequestMapping("listUI")
	public String listUI(){
		return "/five/role_list";
	}
	/*=============================doFindPageObjects======================*/
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent){

		PageHelper.startPage(pageCurrent, 2);

		JsonResult jsonResult = JsonResult.instance();

		PageInfo<FiveRole> objects = fiveRoleService.getObjects();
		System.out.println(objects);
		jsonResult.setData(objects);
		return jsonResult;
	}
	/*=============================editUI()================================*/
	@RequestMapping("editUI")
	public String editUI(){
		return "/five/role_edit";
	}
	/*===================================doFindZTreeNodes()==================*/
	
	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public JsonResult doFindZTreeNodes(){
		List<String> list = fiveRoleService.findZTreeNodes();
		System.out.println(list);
		JsonResult jsonResult = JsonResult.instance();
		jsonResult.setData(list);
		return jsonResult;
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		
		FiveRole fiveRole = fiveRoleService.FindObjectById(id);
		JsonResult jsonResult = JsonResult.instance();
		jsonResult.setData(fiveRole);
		return jsonResult;
		
	}
	/*=====================================findZTreeNodesForDepartment================*/
	@RequestMapping("doFindZTreeNodesForDepartment")
	@ResponseBody
	public JsonResult findZTreeNodesForDepartment(){
		List<FiveEntityForZTree> list = fiveRoleService.findZTreeNodesForDepartment();
		System.out.println(list);
		JsonResult jsonResult = JsonResult.instance();
		jsonResult.setData(list);
		return jsonResult;
	}
	@RequestMapping("doFindFiveRoles")
	@ResponseBody
	public JsonResult doFindFiveRoles(){
		
		JsonResult jsonResult=JsonResult.instance();
		
		List<FiveRole> fiveRoles = fiveRoleService.findFiveRoles();
		jsonResult.setData(fiveRoles);
		
		return jsonResult;
	}
	@RequestMapping("doFindAllAreas")
	@ResponseBody
	public JsonResult doFindAllAreas(){
		
		JsonResult jsonResult=JsonResult.instance();
		
		List<FiveEntityForZTree> list = fiveRoleService.findAllAreas();
		System.out.println(list);
		jsonResult.setData(list);
		
		return jsonResult;
	}
	@RequestMapping("doFindAllUsers")
	@ResponseBody
	public JsonResult doFindAllUsers(){
		
		JsonResult jsonResult=JsonResult.instance();
		
		List<FiveEntityForZTree> list = fiveRoleService.findAllUsers();
		System.out.println(list);
		jsonResult.setData(list);
		
		return jsonResult;
	}
	
}
