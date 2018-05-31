package com.five.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.five.common.exception.ServiceException;
import com.five.dao.FiveLogDao;
import com.five.entity.FiveLog;
import com.five.service.FiveLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("fiveLogService")
public class FiveLogServiceImpl implements FiveLogService {
	@Autowired
	private FiveLogDao fiveLogDao;
	
	@Override
	public PageInfo<FiveLog> findPageLogs(String username, Integer pageCurrent) {
		 //1.验证参数合法性
		if(pageCurrent==null||pageCurrent<1)
			throw new ServiceException("参数不合法");
		//2.查询总记录数
		int rowCount=fiveLogDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("没有记录");
	    //2.查询当前页记录
		//2.1定义页面大小(每页最多现实多少条记录)
		int pageSize=10;
		//2.3查询当前数据
		PageHelper.startPage(pageCurrent,pageSize);
		List<FiveLog> list=fiveLogDao.findPageObjects(username);
		//3.封装数据
		PageInfo<FiveLog> pageInfo = new PageInfo<FiveLog>(list);
		
		return pageInfo;
	}
}
