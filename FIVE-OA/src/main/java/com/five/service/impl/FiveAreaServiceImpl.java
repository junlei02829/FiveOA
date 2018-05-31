package com.five.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.five.common.exception.ServiceException;
import com.five.dao.FiveAreaDao;
import com.five.entity.FiveArea;
import com.five.service.FiveAreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class FiveAreaServiceImpl implements FiveAreaService{
	//分页每页行数
	int pageSize = 5;
	@Autowired
	private FiveAreaDao areaDao;
	@Override
	public PageInfo<FiveArea> findPageObjects(Integer pageCurrent, String name,Boolean isAll) {
		PageHelper.startPage(pageCurrent, pageSize);
		List<FiveArea> list = areaDao.findPageObjects(name,isAll);
		if(list==null) {
			throw new ServiceException("区域查询出错");
		}
		PageInfo<FiveArea> pageObject = new PageInfo<FiveArea>(list);
		return pageObject;
	}
	@Override
	public int validById(Integer id, Integer valid) {
		//1.合法性验证
		if(id==null||id<1)
		throw new ServiceException("数据不合法,id="+id);
		if(valid==null)
		throw new ServiceException("状态值不能为空");
		if(valid!=0&&valid!=1)
		throw new ServiceException("状态值不正确,valid="+valid);
		//2.执行更新操作
		int rows;
		try{
	    rows=areaDao.validById(id, valid);
		}catch(Throwable e){
		e.printStackTrace();
	    //报警
		throw new ServiceException("系统维护中");
		}
		//3.验证结果并处理
		if(rows==0)
		throw new ServiceException("数据可能已经不存在");
		return rows;
	}
	@Override
	public int insertObject(FiveArea entity) {
		// TODO Auto-generated method stub
		return areaDao.insertObject(entity);
	}
	@Override
	public int updateObject(FiveArea entity) {
		// TODO Auto-generated method stub
		return areaDao.updateObject(entity);
	}
	
	
	
	@Override
	public FiveArea FindObjectById(Integer id) {
		 FiveArea fiveArea = areaDao.FindObjectById(id);
		 System.out.println(fiveArea);
		 return fiveArea;
	}
}
