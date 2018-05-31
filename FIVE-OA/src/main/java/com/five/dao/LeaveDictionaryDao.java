package com.five.dao;

import java.util.List;

import com.five.entity.LeaveDictionary;

/**
 * 请假字典表接口
 * @author lizhichao
 *
 */
public interface LeaveDictionaryDao {

	/**
	 * 根据id查询对应的信息
	 * @param id
	 * @return
	 */
	LeaveDictionary doFindLeaveDictionary(Integer id);
	
	/**
	 * 查询对应的信息
	 * @param id
	 * @return
	 */
	List<LeaveDictionary> doFindLeaveDictionaryList();
	
}
