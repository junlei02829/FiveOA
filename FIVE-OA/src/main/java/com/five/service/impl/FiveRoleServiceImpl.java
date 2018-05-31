package com.five.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.five.common.exception.ServiceException;
import com.five.dao.FiveAreaDao;
import com.five.dao.FiveDepartmentAreaDao;
import com.five.dao.FiveDepartmentDao;
import com.five.dao.FiveMenuDao;
import com.five.dao.FiveRoleDao;
import com.five.dao.FiveUserDao;
import com.five.dao.FiveUserRoleDao;
import com.five.entity.FiveEntityForZTree;
import com.five.entity.FiveRole;
import com.five.service.FiveRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 角色业务层：增删(禁用、启用)改查
 * 
 */
@Service
public class FiveRoleServiceImpl implements FiveRoleService{

	@Autowired
	FiveRoleDao fiveRoleDao;
	@Autowired
	FiveDepartmentDao fiveDepartmentDao;
	@Autowired
	FiveMenuDao fiveMenuDao;
	@Autowired
	FiveAreaDao fiveAreaDao;
	@Autowired
	FiveUserDao fiveUserDao;
	@Autowired
	FiveDepartmentAreaDao fiveDepartmentAreaDao;
	@Autowired
	FiveUserRoleDao fiveUserRoleDao;
	/**
	 * 查询包含roleName的角色
	 */
	@Override
	public PageInfo<FiveRole> findFiveRoleByName(Integer pageCurrent,String roleName) {
		//1验证
		if(StringUtils.isEmpty(roleName)){
			throw new ServiceException("角色名不能为空");
		}
		if(pageCurrent<1){
			throw new ServiceException("网页上页码错误");
		}
		PageHelper.startPage(pageCurrent, 2);
		//2执行返回
		 List<FiveRole> list = fiveRoleDao.findFiveRoleByName(roleName);
		 
		 PageInfo<FiveRole> info = new PageInfo<>(list);
		 return info;
	}

	/**
	 * 查询所有的角色
	 * @return
	 */
	@Override
	public PageInfo<FiveRole> getObjects() {
		List<FiveRole> list = fiveRoleDao.getObjects();
		PageInfo<FiveRole> info = new PageInfo<>(list);
		return info;
	}
	
	/**
	 * 禁用启用角色
	 */
	@Override
	@RequiresPermissions("oa:pull-right-container")
	public int deleteFlagById(Integer id, Integer deleteFlag) {
		//1验证
		if(id<1){
			throw new ServiceException("参数id异常,id="+id);
		}
		if(deleteFlag!=0&&deleteFlag!=1){
			throw new ServiceException("代表禁用的参数不等于0、不等于1;deleteFlag="+deleteFlag);
		}
		//2执行返回
		 return  fiveRoleDao.deleteFlagById(id, deleteFlag);
	
		 
	}

	/**
	 * 插入角色
	 */
	@Override
	public  int insertFiveRole(FiveRole fiveRole,Integer departmentId,Integer areaId,Integer userId){
		//1验证
		
		if(fiveRole==null){
			throw new ServiceException("插入的角色为空role="+fiveRole);
		}
		fiveRole.setCreatedTime(new Date());
		fiveRole.setUpdateTime(new Date());
		fiveRole.setCreatedName("1");
		fiveRole.setUpdateName("1");
		fiveRole.setDeleteFlag(1);
		
	
		//2执行返回
		int rows2 = fiveRoleDao.insertFiveRole(fiveRole);
		
		//角色和部门
		int rows1 = fiveRoleDao.insetObjectsToDepartmentRole(fiveRole.getId(), departmentId);
		//部门和区域
		 int rows3 = fiveDepartmentAreaDao.insertObjects(departmentId, areaId);
		//用户和角色
		 int rows4 = fiveUserRoleDao.insertObjectFromRole(userId, fiveRole.getId());
		if(rows1<1||rows2<1||rows3<1||rows4<1){
			throw new ServiceException("修改失败");
		}
		return 1;
	}

	/**
	 * 修改角色
	 */
	@Override
	public int updateFiveRoleById(FiveRole fiveRole) {
		//1验证
		if(fiveRole==null){
			throw new ServiceException("修改的角色为空role="+fiveRole);
		}
		//2执行返回
		return fiveRoleDao.updateFiveRoleById(fiveRole);
	}

	public List<FiveEntityForZTree> findZTreeNodesForDepartment() {
		List<FiveEntityForZTree> list = fiveDepartmentDao.getObjects();
		return list;
	}

	@Override
	public FiveRole FindObjectById(Integer id) {
		FiveRole fiveRole = fiveRoleDao.FindObjectById(id);
		return fiveRole;
	}
	
	
	public List<String> findZTreeNodes(){
		List<String> list = fiveMenuDao.findZTreeNodes();
		return list;
	}
	@Override
	public List<FiveRole> findFiveRoles() {
		
		List<FiveRole> roles = fiveRoleDao.findFiveRoles();
		
		return roles;
	}

	@Override
	public List<FiveEntityForZTree> findAllAreas() {
	
		List<FiveEntityForZTree> list = fiveAreaDao.findAllAreas();
		return list;
	}

	@Override
	public List<FiveEntityForZTree> findAllUsers() {
		List<FiveEntityForZTree> list = fiveUserDao.findAllUsers();
		
		return list;
	}

	


}
