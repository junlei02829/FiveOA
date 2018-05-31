package com.five.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.five.entity.FiveDownLoadExcel;

public interface FiveDownLoadExcelDao {
	// 查询表内容
	List<Map<String, Object>> findUserObject(@Param(value = "tableName") String tableName);

	// 查询表字段名
	List<FiveDownLoadExcel> findTableHeader(@Param("tableName") String tableName);

	// 查询数据库中所有表名
	List<FiveDownLoadExcel> findAllTableName();
}
