package com.five.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;
import com.five.entity.FiveArea;
import com.five.service.FiveAreaService;

@Controller
@RequestMapping("/area/")
public class FiveAreaController {
	@Autowired
    private FiveAreaService areaService;
	@RequestMapping("listUI")
	public String listUI(){
		return "five/area_list";
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent,String name,Boolean isAll) {
		return new JsonResult(areaService.findPageObjects(pageCurrent,name,isAll));
	}
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id,Integer valid){areaService.validById(id, valid);
		return new JsonResult();
	}
	
	
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		
		return new JsonResult(areaService.FindObjectById(id));
	}
	
	
	/**
	 * 添加方法
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doInsertObject(FiveArea entity) {
		JsonResult jsonResult = JsonResult.instance();
		int row = areaService.insertObject(entity);
		if (row > 0) {
			jsonResult.setMessage("添加成功!");
		} else {
			jsonResult.setMessage("添加失败!");
		}
		return jsonResult;
	}
	
	
	
	/**
	 * 修改
	 * @param id
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(FiveArea entity) {
		JsonResult jsonResult = JsonResult.instance();
		int row = areaService.updateObject(entity);
		if (row > 0) {
			jsonResult.setMessage("修改成功!");
		} else {
			jsonResult.setMessage("修改失败!");
		}
		return jsonResult;
	}
	
	
	
	
}
