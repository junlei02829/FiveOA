package com.five.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.five.entity.FivePhone;


public interface PhoneDao {

	List<String> findObject();
	
	
	int insertObject(Map<String, String> map);

	List<FivePhone> findPhoneByName(@Param("address") String address);
	
	List<FivePhone> findPhones();
}
