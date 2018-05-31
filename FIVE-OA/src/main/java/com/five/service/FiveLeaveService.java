package com.five.service;

import java.util.List;

import com.five.entity.FiveLeave;
import com.five.entity.LeaveDictionary;
import com.github.pagehelper.PageInfo;

/**
 * 请假表的业务接口
 * 
 * @author lizhichao
 *
 */
public interface FiveLeaveService {

	/**
	 * 无条件查询所有
	 * 
	 * @return
	 */
	List<FiveLeave> findList();

	/**
	 * 分页查询
	 * @param pageCurrent
	 * @param username
	 * @return
	 */
	PageInfo<FiveLeave> findPageObjects(Integer pageCurrent, String username);
	
	/**
	 * 根据id查询对应的信息
	 * @param id
	 * @return
	 */
	LeaveDictionary doFindLeaveDictionary(Integer id);
	
	/**
	 * 添加方法
	 * @param entity
	 * @return
	 */
	int insertObject(FiveLeave entity);
	
	/**
	 * 查询对应的信息
	 * @param id
	 * @return
	 */
	List<LeaveDictionary> doFindLeaveDictionaryList();
	
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
	 * 判断当前用户的权限
	 */
	int doLeaveAuthority();
	
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
