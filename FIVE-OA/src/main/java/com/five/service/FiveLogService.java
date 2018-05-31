package com.five.service;
import com.five.entity.FiveLog;
import com.github.pagehelper.PageInfo;

public interface FiveLogService {

	PageInfo<FiveLog> findPageLogs(
    		String username,
    		Integer pageCurrent);
}
