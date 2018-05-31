package com.five.entity;

import java.io.Serializable;
/**
 * 这个类用来封装查询出来的数据库中表的名称和表中字段的名称
 * @column_name 表中字段名
 * @table_name 数据库中表名
 * @author Juvia
 *
 */
public class FiveDownLoadExcel implements Serializable {
	
	private static final long serialVersionUID = -8301554929049623094L;

	@Override
	public String toString() {
		return "SysUser [column_name=" + column_name + ", table_name=" + table_name + "]";
	}

	private String column_name;
	private String table_name;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
}
