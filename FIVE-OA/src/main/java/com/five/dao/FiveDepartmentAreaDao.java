package com.five.dao;

import org.apache.ibatis.annotations.Param;

public interface FiveDepartmentAreaDao {

   int	insertObjects(@Param("departmentId")Integer departmentId,@Param("areaId")Integer areaId);
}
