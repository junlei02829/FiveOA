package com.five.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.five.entity.FiveLog;

public interface FiveLogDao {
	/**
	 * 保存日志到数据库
	 * @param log 
	 */
	int saveLog(FiveLog log);


	/**
	 * 从数据库查询日志到页面显示
	 */
	List<FiveLog> findPageObjects(@Param("username")String username);

	int getRowCount(@Param("username")String username);
}
