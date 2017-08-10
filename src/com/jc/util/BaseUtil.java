package com.jc.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.jc.util.converter.Converter;
/**
 * 
 * @author Administrator
 *
 */
public class BaseUtil{
	private static String convertersUrl;

	protected static void setConvertersUrl(String convertersUrl) {
		BaseUtil.convertersUrl = convertersUrl;
	}

	protected static String converte(String str){
		//去除前后空格
		str.trim();
		if(str == ""){
			new RuntimeException("传入字符串为空");
		}
		//转换成小写
		str = str.toLowerCase();
		//获取转换器集合
		List<Converter> ConverterList = loadConverters();
		//调用转换器转换
		for(Converter converter : ConverterList){
			str = converter.execute(str);
		}
		return str;
	}
	private static List<Converter> loadConverters(){
		//创建用于存放converter的集合
		List<Converter> converterList = new ArrayList<Converter>();
		try{
			//读取配置文件
			FileInputStream fis = null;
			fis = new FileInputStream(convertersUrl);
			byte[] b = new byte[1024];
			int len = 0;
			String contervers = "";
			while((len = fis.read(b)) != -1){
				contervers += new String(b,0,len);
			}
			fis.close();	
			//分割字符串，获取转换器名字
			String[] converterNames = contervers.split("\r\n");
			for(String converterName : converterNames){
				//反射创建每个转换器类
				Class clazz = Class.forName(converterName);
				//放入集合中
				converterList.add((Converter)clazz.newInstance());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return converterList;
	}
}
