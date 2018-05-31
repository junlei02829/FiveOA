package com.five.service;

import java.util.List;
import java.util.Map;

import com.five.entity.FivePhone;


public interface FivePhoneService {
	
	
	
	List<String> findObject();
	
	int insertObject(Map<String,String> map);

	List<FivePhone> findPhoneByName(String address);
	List<FivePhone> findPhones();
}
