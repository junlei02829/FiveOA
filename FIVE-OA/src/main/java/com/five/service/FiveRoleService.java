package com.five.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.five.entity.FiveDepartment;
import com.five.entity.FiveEntityForZTree;
import com.five.entity.FiveRole;
import com.github.pagehelper.PageInfo;
/**
 * 角色Service接口
 * @author 000
 *
 */
public interface FiveRoleService {

	/**
	 * 查询
	 * 这个方法要实现模糊查询角色;例如(输入经理:显示财务经理 ,销售经理,项目经理各种经理 )
	 * @param name--> 角色名
	 * @return
	 */
	PageInfo<FiveRole> findFiveRoleByName(Integer pageCurrent,String roleName);
	
	/**
	 * 查询所有的角色
	 * @return 所有的的角色
	 */
	PageInfo<FiveRole> getObjects();
	
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
	int insertFiveRole(FiveRole fiveRole,Integer departmentId,Integer areaId,Integer userId);
	
	/**
	 * 修改
	 * @param role-->实体封装
	 * @return
	 */
	int updateFiveRoleById(FiveRole fivRole); 
	
	List<String> findZTreeNodes(); 
	
	List<FiveEntityForZTree> findZTreeNodesForDepartment(); 
	/**
	 * 按id查询用户
	 * @param id
	 * @return
	 */
	FiveRole FindObjectById(Integer id);
	
	
	/**
	 * 查询所有的角色
	 * 
	 * @return
	 */
	List<FiveRole> findFiveRoles();
	
	/*查询所有的区域*/
	List<FiveEntityForZTree> findAllAreas();

	//查询所有用户
	List<FiveEntityForZTree> findAllUsers();

}
