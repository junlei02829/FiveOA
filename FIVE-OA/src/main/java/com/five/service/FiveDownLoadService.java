package com.five.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.five.entity.FiveDownLoadExcel;


public interface FiveDownLoadService {
	void doDownLoadExcel(String tableName);
//	HttpServletResponse response,
	List<FiveDownLoadExcel> dofindAllTableName();
}
