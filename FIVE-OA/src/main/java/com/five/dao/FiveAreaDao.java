package com.five.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.five.entity.FiveArea;
import com.five.entity.FiveEntityForZTree;

public interface FiveAreaDao {
	
	
	List<FiveArea> findPageObjects(@Param("name") String name,@Param("isAll") Boolean isAll);
	
	
	 FiveArea FindObjectById(@Param("id")Integer id);
	
	
	
	int validById(@Param("id")Integer id, @Param("valid")Integer valid);
	
	List<FiveEntityForZTree> findAllAreas();
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
}
