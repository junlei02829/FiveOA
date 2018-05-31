package com.five.service;

import com.five.entity.FiveArea;
import com.github.pagehelper.PageInfo;

public interface FiveAreaService {
	PageInfo<FiveArea> findPageObjects(Integer pageCurrent, String name,Boolean isAll);

	int validById(Integer id, Integer valid);
	
	/**
	 * 添加方法
	 * @param entity
	 * @return
	 */
	int insertObject(FiveArea entity);
	
	/**
	 * 修改方法
	 * @param entity
	 * @return
	 */
	int updateObject(FiveArea entity);
	
	
	 FiveArea FindObjectById(Integer id);
		
	
	
}
