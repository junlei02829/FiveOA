package com.five.common.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.five.common.util.PinyinTool.Type;
import com.five.service.impl.FivePhoneServiceImpl;




/***
 * 判断汉字还是英文并转换为缩写字母
 * @author 
 *
 */
@Component
public class PingyinTest {

	public Map toUpperCase() throws Exception{
		String user_name=null;
		String str1=null;
		String address=null;
		PinyinTool tool = new PinyinTool();  


		FivePhoneServiceImpl  fivePhoneServiceImpl= new FivePhoneServiceImpl();
		List<String> list = fivePhoneServiceImpl.findObject();
		for (String str : list) {
			user_name=str;
		}
		if(user_name!=null) {


			//判断是汉字还是英文
			byte[] bytes = user_name.getBytes();  
			int i = bytes.length;//i为字节长度  
			int j = user_name.length();//j为字符长度  
			if(i!=j){
				str1=tool.toPinYin(user_name,"", Type.FIRSTUPPER);
			}

			char array[]= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
					'U','V','W','X','Y','Z'};        



			//将str转换为数组与array比较
			char[] charArray = str1.toCharArray();
		

			for(int x=0;x<charArray.length;x++) {
				for(int y=0;y<array.length;y++){


					if(array[x]==charArray[y]) {
						//str1每个汉语拼音的首字母大写的
						char ch=array[x];

						//str2所有汉语拼音的首字母大写;
						address=address+ch;

					}
				}			
			}


		}else {
			System.out.println("username为null");
		}


		
		Map<String,String> map = new HashMap<>();
		map.put(user_name, address);
		return map;


	}

}

