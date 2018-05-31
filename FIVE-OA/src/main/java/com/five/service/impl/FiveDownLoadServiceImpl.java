package com.five.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.five.common.exception.ServiceException;
import com.five.dao.FiveDownLoadExcelDao;
import com.five.entity.FiveDownLoadExcel;
import com.five.service.FiveDownLoadService;
@Service
public class FiveDownLoadServiceImpl implements FiveDownLoadService {
	
	@Autowired
	private FiveDownLoadExcelDao fiveDownLoadExcelDao;

	/**
	 * 此方法用来执行将mysql数据库的表单内容下载到主机d盘downLoadExcel文件夹中
	 * @throws FileNotFoundException 
	 * HttpServletResponse response,
	 */
	@Override
	public void doDownLoadExcel(String tableName){
		
		String tablename = "five_address";
		
		// 声明文件路径
		String path = "D:\\downLoadExcel\\"+tableName + ".xls";
		// 创建Excel的文档对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建Excel的表单(sheet)
		HSSFSheet sheet = workbook.createSheet();

		// 获取表头
		// String table = "'"+names+"'";
		List<FiveDownLoadExcel> tableHeaderList = fiveDownLoadExcelDao.findTableHeader(tableName);
		HSSFRow row = sheet.createRow(0);// 创建行,从0开始
		int i = 0;
		List<String> headerElements = new ArrayList<>();
		for (FiveDownLoadExcel fiveDownLoadExcel : tableHeaderList) {
			String header = fiveDownLoadExcel.getColumn_name();
			row.createCell(i++).setCellValue(header);
			headerElements.add(header);
		}
		// System.out.println(headerElements);
		// 获取表内容

		// System.out.println(table);
		List<Map<String, Object>> contentList = fiveDownLoadExcelDao.findUserObject(tableName);
		for (Map<String, Object> map : contentList) {
			System.out.println(map);
		}
		// 定义行
		int rowNum = 1;

		// 将查询到的表内容添加到sheet对象中
		for (Map<String, Object> map : contentList) {
			row = sheet.createRow(rowNum);
			for (int index = 0; index < headerElements.size(); index++) {
				// System.out.println("haha1="+headerElements.get(index));
				// System.out.println("haha2="+map.get("createdTime").toString());
				String fieldElements = null;
				try {
					fieldElements = map.get(headerElements.get(index)).toString();
				} catch (NullPointerException e) {
//					e.printStackTrace();
					System.out.println("空指针异常!");
					continue;

				}
				row.createCell(index).setCellValue(fieldElements);
			}
			if (rowNum < contentList.size()) {
				rowNum++;
			}

		}

		
				// 创建输出流
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(path);
					// 将workbook的内容写入到测试00.xls文件的test表单中
					workbook.write(out);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						out = null;
					}
				}

			}	
	
	

	@Override
	public List<FiveDownLoadExcel> dofindAllTableName() {
//		PageHelper.startPage(1, 5);
		List<FiveDownLoadExcel> list = fiveDownLoadExcelDao.findAllTableName();
		return list;
	}

}
