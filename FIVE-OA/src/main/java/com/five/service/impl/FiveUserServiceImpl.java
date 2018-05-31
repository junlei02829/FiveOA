package com.five.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.five.common.exception.ServiceException;
import com.five.common.vo.UserRole;
import com.five.dao.FiveUserDao;
import com.five.dao.FiveUserRoleDao;
import com.five.entity.FiveUser;
import com.five.service.FiveUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;

/**
 * 
 * 用户服务实现类
 * 
 * @author 000
 *
 */
@Service
public class FiveUserServiceImpl implements FiveUserService {

	@Autowired
	private FiveUserDao userDao;
	@Autowired
	private FiveUserRoleDao userRoleDao;

	/**
	 * 分页查询所有的用户
	 */
	@Override
	public PageInfo<FiveUser> findObjects(Integer pageCurrent) {

		PageInfo<FiveUser> info;

		if (pageCurrent == null || pageCurrent < 1) {

			throw new ServiceException("pageCurrent不合法!!");
		}

		PageHelper.startPage(pageCurrent, 3);

		List<FiveUser> list;

		try {

			list = userDao.findObjects();

			System.out.println(list);

			info = new PageInfo<>(list);

		} catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("服务器维护中!!");
		}

		// System.out.println(info.getList());

		return info;
	}

	/**
	 * 
	 * 基于用户ID查询角色关系
	 * 
	 * @param userId
	 * @return
	 * 
	 * 		public List<Integer> findRoleIdsByUserId(Integer userId){
	 * 
	 * 
	 *         List<Integer> rolesIds = userRoleDao.findRoleIdsByUserId(userId);
	 * 
	 *         return rolesIds; }
	 */

	/**
	 * 添加用户
	 */
	@Override
	public int saveObject(FiveUser user, String roleIds) {

		int rows;

		if (user == null) {

			throw new ServiceException("添加的用户信息不能为空！");
		}

		String username = user.getUsername();

		List<FiveUser> list = userDao.findObjectByName(username);

		if (list.size() != 0) {

			throw new ServiceException("添加的用户信息已经存在！");
		}

		try {
			// 2.对用户密码进行加密
			String salt = UUID.randomUUID().toString();
			user.setSalt(salt);
			SimpleHash sh = new SimpleHash("MD5", user.getPassword(), salt);
			user.setPassword(sh.toString());

			user.setDelete_flag(1);
			user.setCreate_name("DDDD");
			user.setCreate_time(new Date());
			user.setSalt("AAA");
			user.setType(1);
			user.setUpdate_name("SSSS");
			user.setUpdate_time(new Date());

			// System.out.println(user);

			rows = userDao.insertObject(user);

			// System.out.println("userId:"+user.getId());

			Integer userId = user.getId();
			// 保存用户角色关系
			userRoleDao.insertObject(userId, roleIds.split(","));

		} catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("服务器维护中!!");
		}

		return rows;
	}

	/**
	 * 
	 * 修改用户
	 * 
	 * @param user
	 *            修改的信息
	 * @return
	 */
	@Override
	public int updateObject(FiveUser user, String roleIds) {

		int rows;

		if (user == null) {

			throw new ServiceException("修改的用户ID不能为空！");
		}

		try {

			rows = userDao.updateObject(user);

			Integer userId = user.getId();
			// 删除用户角色关系
			userRoleDao.deleteObject(userId);
			// 保存用户角色关系
			userRoleDao.insertObject(userId, roleIds.split(","));

		} catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("服务器维护中!!");
		}

		return rows;

	}

	/**
	 * 
	 * 基于id查询单个用户
	 */

	@Override
	public UserRole findObjectById(Integer id) {

		UserRole userRole = new UserRole();

		if (id == null || id < 1) {

			throw new ServiceException("用户ID不合法！");
		}

		FiveUser fiveUser = userDao.findObjectById(id);
		System.out.println("user:" + fiveUser);

		Integer userId = id;
		List<Integer> roleIds = userRoleDao.findRoleIdsByUserId(userId);

		System.out.println("rolesIds:" + roleIds);

		userRole.setFiveUser(fiveUser);
		userRole.setRoleIds(roleIds);

		return userRole;
	}

	/**
	 * 基于id修改用户状态
	 */
	@Override
	public int updateTypeById(Integer id) {

		int rows;

		if (id == null || id < 1) {

			throw new ServiceException("修改的用户ID不能为空！");
		}

		try {
			rows = userDao.updateTypeById(id);

		} catch (Exception e) {

			throw new ServiceException("服务器维护中!!");
		}

		return rows;
	}

	/**
	 * 基于id删除用户
	 */
	@Override
	public int deleteObjectById(Integer id) {

		int rows;

		if (id == null || id < 1) {

			throw new ServiceException("删除的用户ID不能为空！");
		}

		try {
			rows = userDao.deleteObjectById(id);

		} catch (Exception e) {

			throw new ServiceException("服务器维护中!!");
		}

		return rows;
	}

	/**
	 * 
	 * 基于username查询用户
	 */

	@Override
	public PageInfo<FiveUser> findObjectByName(String username) {

		PageInfo<FiveUser> info;

		PageHelper.startPage(1, 3);

		try {

			List<FiveUser> list = userDao.findObjectByName(username);

			if (list.size() == 0) {

				throw new ServiceException("用户不存在!!");
			}

			info = new PageInfo<>(list);

		} catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("服务器维护中!!");
		}

		return info;
	}

	@Override
	public FiveUser login(String username, String password, String kaptcha, HttpServletRequest request) {
		// 0.参数合法性验证
		if (StringUtils.isEmpty(username)) 
			throw new ServiceException("用户名不能为空");
		
		if (StringUtils.isEmpty(password))
			throw new ServiceException("密码不能为空");
		if (StringUtils.isEmpty(kaptcha)) 
			throw new ServiceException("验证码不能为空");

		// 1.获取Subject(主体)对象、判断是否通过认证
		Subject subject = SecurityUtils.getSubject();
		
		if (subject.isAuthenticated()) {
			return userDao.findIdByName(username);
		}
		String sessionNumber = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		/**
		 * 判断图片验证码是否填写正确
		 */
		if (!sessionNumber.toLowerCase().equals(kaptcha.toLowerCase())) {
			throw new ServiceException("验证码填写有误！");
		}

		// 2.封装用户名和密码(封装到一个令牌对象)
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, kaptcha);
		// 3.执行身份认证
		try {
			subject.login(token);
			// 此请求会提交给SecurityManager
			// SecurityManager会调用认证处理器Authenticator
			// 认证处理器会去访问相关Realm对象获取认证信息
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new ServiceException("用户名/密码/验证码不正确");
		}
		return  userDao.findIdByName(username);
	}

	@Override
	public List<FiveUser> findObjects() {

		return userDao.findObjects();
	}

	@Override
	public PageInfo<FiveUser> findObjectsByPage(Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertObject(FiveUser user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
