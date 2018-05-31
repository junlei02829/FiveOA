package com.five.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.five.entity.FiveLeave;

/**
 * 请假表的实体接口
 * 
 * @author lizhichao
 *
 */
public interface FiveLeaveDao {

	/**
	 * 无条件查询所有
	 * 
	 * @return
	 */
	List<FiveLeave> findList();

	/**
	 * 分页查询
	 * @param startIndex
	 * @param pageSize
	 * @param username
	 * @return
	 */
	List<FiveLeave> findPageObjects(@Param("username") String username);

	/**
	 * 查询总记录数
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username")String username);
	
	/**
	 * 添加方法
	 * @param entity
	 * @return
	 */
	int insertObject(FiveLeave entity);
	
	/**
	 * 修改方法
	 * @param entity
	 * @return
	 */
	int updateObject(FiveLeave entity);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	FiveLeave doFindObjectById(Integer id);
	
	/**
	 * 修改驳回信息
	 * @param entity
	 * @return
	 */
	int updateRemark(FiveLeave entity);
	
	/**
	 * 通过申请
	 * @param entity
	 * @return
	 */
	int updateAuthority(FiveLeave entity);
	
	
}
