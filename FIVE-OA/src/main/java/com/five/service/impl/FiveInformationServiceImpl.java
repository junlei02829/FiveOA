package com.five.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.five.common.exception.ServiceException;
import com.five.common.util.ShiroUtils;
import com.five.dao.FiveInformationDao;
import com.five.entity.FiveInformation;
import com.five.entity.FiveUser;
import com.five.service.FiveInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 业务接口实现类
 * @author lizhichao
 *
 */
@Service
public class FiveInformationServiceImpl  implements FiveInformationService{

	@Autowired
	private FiveInformationDao fiveInformationDao;
	
	@Override
	public PageInfo<FiveInformation> findPageObjects(Integer pageCurrent, String username) {
		if(pageCurrent==null || pageCurrent<=0) {
			throw new ServiceException("参数异常");
		}
		FiveUser fiveUser = ShiroUtils.getPrincipal();
		// 设置每页初始大小
		int pageSize = 5;
		PageHelper.startPage(pageCurrent, pageSize);
		List<FiveInformation> list = fiveInformationDao.findPageObjects(username,fiveUser.getId());
		//封装分页
		PageInfo<FiveInformation> pageInfo = new PageInfo<FiveInformation>(list);
		
		return pageInfo;
	}

	@Override
	public int insertObject(FiveInformation entity) {
		if(StringUtils.isEmpty(entity.getContent()))
			throw new ServiceException("内容不能为空");
		if(entity.getReceiptId()==null) {
			entity.setReceiptId(0);
		}
			
		FiveUser fiveUser = ShiroUtils.getPrincipal();
		entity.setUsername(fiveUser.getUsername());
		return fiveInformationDao.insertObject(entity);
	
	}

	@Override
	public FiveInformation doFindObjectById(Integer id) {
		if(StringUtils.isEmpty(id))
			throw new ServiceException("参数异常");
		return fiveInformationDao.doFindObjectById(id);
	}

	@Override
	public List<FiveInformation> doFindPageObjects() {
		// TODO Auto-generated method stub
		return fiveInformationDao.doFindPageObjects();
	}

}
