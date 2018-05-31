package com.five.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;


/**
 * 
 * @ClassName: PropertiesUtil
 * @Description: 读取properties文件的配置信息--采用单例模式
 * @author: Wilson 1053288979@qq.com
 * @date: 2016年9月27日 下午4:25:26
 */
public class PropertiesUtil {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(PropertiesUtil.class);
	   private static PropertiesUtil instance = null;

	   private PropertiesUtil() { }
	   /**
	    * 
	   * @Title: getInstance
	   * @Description: 双重验证获得实例，避免重复加锁
	   * @return
	   * @return: PropertiesUtil
	    */
	   public  static PropertiesUtil getInstance() {
	      if(instance == null) {
	    	  synchronized(PropertiesUtil.class) {
	            PropertiesUtil temp = instance;
	            if(temp == null) {
	               temp = new PropertiesUtil();
	               instance = temp;
	            }
	         }
	      }

	      return instance;
	   }

	/**
	 * 
	* @Title: loadProperties
	* @Description: 读取配置文件中的属性值
	* @param path
	* @param attributesName
	* @return
	* @return: String
	 */
	public String loadProperties(String path, String attributesName) {
		Properties properties = new Properties();
		InputStream input = null;
		try {
			input = this.getClass().getResourceAsStream(path);// 加载Java项目根路径下的配置文件
			properties.load(input);// 加载属性文件

			return properties.getProperty(attributesName).trim();
		} catch (IOException io) {
			logger.error("读取properties文件失败");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
