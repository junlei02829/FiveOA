package com.five.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传工具类
 * @author lizhichao
 *
 */
public class Utility {
	
	private static final String UPLOAD_PATH = "/upload.properties";
	
	/**
	 * 判断上传文件是否为空 不为空上传
	 * 为空则不上传
	 * @param uploadDir
	 * @param files
	 * @param request
	 * @return
	 */
	public static synchronized Map<String, Object> getMultipartUpload(String uploadDir,MultipartFile[] files, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> listServerPath = new ArrayList<String>();
		List<String> listFileName=new ArrayList<String>();
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				if (file != null && !file.isEmpty()) {
					String path = Utility.getSingleUpload(uploadDir, file, request);
					if (path==null) {// 如果一个文件没有上传成功，则全部设为上传失败
						resultMap.put("result", "error");
						resultMap.put("listFileName", listFileName);
						resultMap.put("listServerPath", listServerPath);
						break;
					} else {
						listFileName.add(file.getOriginalFilename());
						listServerPath.add(path);
						resultMap.put("result", "success");
						//文件名称
						resultMap.put("listFileName", listFileName);
						//服务器存储路径 d:....
						resultMap.put("listServerPath", listServerPath);
					}
				}else{
					resultMap.clear();
					resultMap.put("result", "error");
					break;
				}
			}
		}
		
		return resultMap;
	}

	
	
	
	/**
	 * 上传文件
	 * @param uploadDir
	 * @param file
	 * @param request
	 * @return
	 */
	public static synchronized String getSingleUpload(String uploadDir, MultipartFile file,
			HttpServletRequest request) {
		
		Map<String,String> map = new HashMap<>();
		
		if (file == null || file.isEmpty()) {
			//logger.info("上传文件为空");
			return null;
		}
		
		
		String name = Utility.getUUID() + file.getOriginalFilename().replaceAll(" ","");// 生成唯一的文件名称
		// 按照日期存储文件
		
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String root =PropertiesUtil.getInstance().loadProperties(UPLOAD_PATH, "upload_path_root");
		
		String dir =PropertiesUtil.getInstance().loadProperties(UPLOAD_PATH, "upload_path_dir");
		
		String project =PropertiesUtil.getInstance().loadProperties(UPLOAD_PATH, "upload_path_project");
		
		
		File uploadsDir = new File(root+dir);
		
		if(!uploadsDir.exists() && !uploadsDir.isDirectory()){
			uploadsDir.mkdir();
		}
		
		dir+=project;
		
		File projectDir = new File(root+dir);
		
		if(!projectDir.exists() && !projectDir.isDirectory()){
			projectDir.mkdir();
		}
		
		dir += sFormat.format(date)+"/";
		
		File dateDir = new File(root+dir);
		
		if(!dateDir.exists() && !dateDir.isDirectory()){
			dateDir.mkdir();
		}
		try {
			file.transferTo(new File(root+dir+name));
			return dir+name;
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return null;
	}
	
	
	
	/**
	 * 生成唯一的UUID,主要用户实体类的主键字段
	 * 
	 * @return 唯一的 UUID
	 */
	public static String getUUID() {

		return java.util.UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
	
	
	/**
	 * 下载文件
	 * @param filePath
	 * @param request
	 * @param response
	 * @return
	 */
	public static synchronized String downloadSingleFile(String filePath,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (StringUtils.isNoneBlank(filePath)) {
			//服务器根路径
			String root =PropertiesUtil.getInstance().loadProperties(UPLOAD_PATH, "upload_path_root");
			
			filePath=root+filePath;
            
			File file = new File(filePath);
            
			if(!file.exists()){
            	return "none";
            }
			
            String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
            if (file.exists()) {
               response.setContentType("application/force-download");// 设置强制下载不打开
               response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
               byte[] buffer = new byte[1024];
                 FileInputStream fis = null;
                 BufferedInputStream bis = null;
                 try {
                     fis = new FileInputStream(file);
                     bis = new BufferedInputStream(fis);
                     OutputStream os = response.getOutputStream();
                     int i = bis.read(buffer);
                     while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                     }
                    os.close();
             } catch (Exception e) {
                  e.printStackTrace();
                } finally {
                	  try {
                		 if (bis != null) {
                             bis.close();
                		 }
                		 if (fis != null) {
                			 fis.close();
                         }
                    }catch (IOException e) {
                         e.printStackTrace();
                     }
                }
           }
         }else{
        	 return "fail";
         }
		return null;
	}
	
	
	/**
	 * 文件打包
	 * @param filePath
	 * @return
	 */
	public static synchronized  String downloadtemp(String filePath) {
		String[] filepath = filePath.split(",");
		File[] file = new File[filepath.length];
		// 服务器根路径
		String root = PropertiesUtil.getInstance().loadProperties(UPLOAD_PATH, "upload_path_root");
		String dir = PropertiesUtil.getInstance().loadProperties(UPLOAD_PATH, "upload_path_dir");
		String project = PropertiesUtil.getInstance().loadProperties(UPLOAD_PATH, "upload_path_project");

		// 如果一级目录不存在，创建
		File temp = new File(root + dir);
		if (!temp.exists() && !temp.isDirectory()) {
			temp.mkdir();
		}
		dir += project;
		// 如果二级目录不存在，创建
		temp = new File(root + dir);
		if (!temp.exists() && !temp.isDirectory()) {
			temp.mkdir();
		}

		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dir += sFormat.format(date) + "/";

		// 如果三级目录不存在，创建
		temp = new File(root + dir);
		if (!temp.exists() && !temp.isDirectory()) {
			temp.mkdir();
		}
		String filepa=dir+ sFormat.format(new Date())+"-"+Utility.getUUID()+".zip";
		try {
			sFormat=  new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
			// 下载的压缩包的路径
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(root+filepa));
			byte[] buffer = new byte[1024];
			for (int i = 0; i < filepath.length; i++) {
				file[i] = new File(root + filepath[i]);
			}

			for (int i = 0; i < file.length; i++) {
				FileInputStream fis = new FileInputStream(file[i]);
				out.putNextEntry(new ZipEntry(file[i].getName()));
				// 设置压缩文件内的字符编码，不然会变成乱码
				// out.setEncoding("GBK");
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filepa;
	}
	
	
	
	
	
}
