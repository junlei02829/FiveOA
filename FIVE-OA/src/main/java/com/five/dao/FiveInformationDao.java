package com.five.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.five.entity.FiveInformation;

/**
 * 站内信dao接口
 * @author lizhichao
 *
 */
public interface FiveInformationDao {

	/**
	 * 分页查询
	 * @param startIndex
	 * @param pageSize
	 * @param username
	 * @return
	 */
	List<FiveInformation> findPageObjects(@Param("username") String username, @Param("receiptId")Integer receiptId);

	/**
	 * 查询总记录数
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username")String username ,@Param("receiptId")Integer receiptId);
	
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
