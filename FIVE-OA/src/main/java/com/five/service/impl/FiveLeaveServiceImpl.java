package com.five.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.five.common.exception.ServiceException;
import com.five.common.util.ShiroUtils;
import com.five.common.vo.FiveLeaveRoles;
import com.five.dao.FiveLeaveDao;
import com.five.dao.FiveLeaveRolesDao;
import com.five.dao.LeaveDictionaryDao;
import com.five.entity.FiveLeave;
import com.five.entity.FiveUser;
import com.five.entity.LeaveDictionary;
import com.five.service.FiveLeaveService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 请假表的业务接口实现类
 * 
 * @author lizhichao
 *
 */
@Service("fiveLeaveService")
public class FiveLeaveServiceImpl implements FiveLeaveService {

	@Autowired
	private FiveLeaveDao fiveLeaveDao;
	@Autowired
	private LeaveDictionaryDao leaveDictionaryDao;
	@Autowired
	private FiveLeaveRolesDao fiveLeaveRolesDao;
	
	
	@Override
	public List<FiveLeave> findList() {

		return fiveLeaveDao.findList();
	}

	@Override
	public PageInfo<FiveLeave> findPageObjects(Integer pageCurrent, String username) {
		if(pageCurrent==null || pageCurrent<=0) {
			throw new ServiceException("参数异常");
		}
		// 设置每页初始大小
		int pageSize = 5;
		PageHelper.startPage(pageCurrent, pageSize);
		List<FiveLeave> list = fiveLeaveDao.findPageObjects(username);
		//封装分页
		PageInfo<FiveLeave> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	
	@Override
	public LeaveDictionary doFindLeaveDictionary(Integer id) {
		return leaveDictionaryDao.doFindLeaveDictionary(id);
	}

	@Override
	public int insertObject(FiveLeave entity) {
		
		if(StringUtils.isEmpty(entity))
			throw new ServiceException("参数不能为空");
		//后期获取当前登录用户
		FiveUser fiveUser = ShiroUtils.getPrincipal();
		entity.setUsername(fiveUser.getUsername());
		entity.setUserId(fiveUser.getId());
		int rows = fiveLeaveDao.insertObject(entity);
		if(rows<=0)
			throw new ServiceException("添加失败");
		return rows;
	}

	@Override
	public List<LeaveDictionary> doFindLeaveDictionaryList() {
		return leaveDictionaryDao.doFindLeaveDictionaryList();
	}

	@Override
	public int updateObject(FiveLeave entity) {
		if(StringUtils.isEmpty(entity))
			throw new ServiceException("参数不能为空");
		return fiveLeaveDao.updateObject(entity);
	}

	@Override
	public FiveLeave doFindObjectById(Integer id) {
		if(StringUtils.isEmpty(id))
			throw new ServiceException("参数不能为空");
		return fiveLeaveDao.doFindObjectById(id);
	}

	@Override
	public int doLeaveAuthority() {
		//获取当前登录用户的id
		
		//判断当前登录用户的权限是一级还是二级审核对象
		
		//修改请假条的状态 readAuthority 
//		private static final long first = 0;//驳回
//		private static final long second = 1;//默认状态表示刚提交申请
//		private static final long third = 2;//一级审核通过
//		private static final long fourth = 3;//二级审核
//		private static final long fifth = 4;//审核成功

		//
		
		return 0;
	}

	@Override
	public int updateRemark(FiveLeave entity) {
		if(StringUtils.isEmpty(entity.getId()))
			throw new ServiceException("参数异常");
		if(StringUtils.isEmpty(entity.getRemark()))
			throw new ServiceException("驳回信息不能为空");
		entity.setReadAuthority(FiveLeave.first);//修改阅读状态
		int rows = fiveLeaveDao.updateRemark(entity);
		return rows;
	}

	@Override
	public int updateAuthority(FiveLeave entity) {
		if(StringUtils.isEmpty(entity.getId()))
			throw new ServiceException("参数异常");
		FiveUser fiveUser = ShiroUtils.getPrincipal();  
		int flag=0;
		//查询用户的角色名
		List<FiveLeaveRoles> roleName = fiveLeaveRolesDao.findRoleName(fiveUser.getId());
	
		for (FiveLeaveRoles fls : roleName) {
			if(fls.getRoleName().equals("副经理")) {
				flag=1;break;
			}
			if(fls.getRoleName().equals("总经理")) {
				flag=2;break;
			}
		}
		if(flag==0)
			throw new ServiceException("您没有权限");
		//一级审核 2
		if(flag==1) {
			entity.setReadAuthority(FiveLeave.third);
		}
		//二级审核3
		if(flag==2) {
			entity.setReadAuthority(FiveLeave.fourth);
		}
		
		int rows = fiveLeaveDao.updateAuthority(entity);
		return rows;
	}


	

}
