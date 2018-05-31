package com.five.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.five.common.exception.ServiceException;
import com.five.common.util.PingyinTest;
import com.five.common.util.PinyinTool;
import com.five.dao.PhoneDao;
import com.five.entity.FivePhone;
import com.five.service.FivePhoneService;



@Service
public class FivePhoneServiceImpl implements FivePhoneService {
	@Autowired
	private PhoneDao phonedao;
	@Autowired
	private PingyinTest pingyinTest;
	@Autowired
	private PinyinTool pinyintool;
	@Override
	public List<String> findObject() {
		List<String> list = phonedao.findObject();
		return list;
	}

	@Override
	public int insertObject(Map<String, String> map) {
		int io = 0;
		try {
			Map upperCase = pingyinTest.toUpperCase();
			io = phonedao.insertObject(upperCase);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return io;
	}

	@Override
	public List<FivePhone> findPhoneByName(String address) {
		
		if(address==null) 
			throw new ServiceException("用户不存在");
		
		
		return phonedao.findPhoneByName(address);
	}

	@Override
	public List<FivePhone> findPhones() {
		return phonedao.findPhones();
	}

}
