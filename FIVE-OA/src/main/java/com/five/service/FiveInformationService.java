package com.five.service;

import java.util.List;

import com.five.entity.FiveInformation;
import com.github.pagehelper.PageInfo;

/**
 * 站内信业务层接口
 * @author lizhichao
 *
 */
public interface FiveInformationService {

	/**
	 * 分页查询
	 * @param pageCurrent
	 * @param username
	 * @return
	 */
	PageInfo<FiveInformation> findPageObjects(Integer pageCurrent, String username);

	/**
	 * 添加方法
	 * @param entity
	 * @return
	 */
	int insertObject(FiveInformation entity);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	FiveInformation doFindObjectById(Integer id);
	
	/**
	 * 公告查询
	 * @return
	 */
	List<FiveInformation> doFindPageObjects();
}
