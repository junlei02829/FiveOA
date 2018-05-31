package com.five.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.five.entity.FiveEntityForZTree;
import com.five.entity.FiveRole;

/**
 * 角色Dao接口
 * @author 000
 *
 */
public interface FiveRoleDao {

	/*********************主要是玄今男写，下面部分石军磊************************/
	/**
	 * 查询
	 * 这个方法要实现模糊查询角色;例如(输入经理:显示财务经理 ,销售经理,项目经理各种经理 )
	 * @param name--> 角色名
	 * @return
	 */
	List<FiveRole> findFiveRoleByName(String roleName);
	
	/**
	 * 查询所有的角色
	 * @return 所有的的角色
	 */
	List<FiveRole> getObjects();

	/**
	 * 按id查询角色
	 * @param id -->角色id
	 * @return
	 */
	FiveRole FindObjectById(Integer id);
	
	/**
	 * 禁用启用角色
	 * @param id--> 角色id
	 * @param valid--> 禁用启用
	 * @return
	 */
	int deleteFlagById(@Param("id")Integer id ,@Param("deleteFlag")Integer deleteFlag);
	
	/**
	 * 插入
	 * @param role-->实体封装
	 * @return
	 */
	int insertFiveRole(FiveRole role);
	
	/**
	 * 修改
	 * @param role-->实体封装
	 * @return
	 */
	int updateFiveRoleById(FiveRole role); 
	
	int insetObjectsToDepartmentRole(@Param("roleId")Integer roleId,@Param("departmentId")Integer departmentId);
	
	/*=========================================下面石军磊写==========================*/
	/**
	 * 
	 * 查询所有角色
	 */
	List<FiveRole> findFiveRoles();
	/*查询所有的区域*/
	List<FiveEntityForZTree> findAllAreas();
}
