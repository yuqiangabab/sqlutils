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
		//ȥ��ǰ��ո�
		str.trim();
		if(str == ""){
			new RuntimeException("�����ַ���Ϊ��");
		}
		//ת����Сд
		str = str.toLowerCase();
		//��ȡת��������
		List<Converter> ConverterList = loadConverters();
		//����ת����ת��
		for(Converter converter : ConverterList){
			str = converter.execute(str);
		}
		return str;
	}
	private static List<Converter> loadConverters(){
		//�������ڴ��converter�ļ���
		List<Converter> converterList = new ArrayList<Converter>();
		try{
			//��ȡ�����ļ�
			FileInputStream fis = null;
			fis = new FileInputStream(convertersUrl);
			byte[] b = new byte[1024];
			int len = 0;
			String contervers = "";
			while((len = fis.read(b)) != -1){
				contervers += new String(b,0,len);
			}
			fis.close();	
			//�ָ��ַ�������ȡת��������
			String[] converterNames = contervers.split("\r\n");
			for(String converterName : converterNames){
				//���䴴��ÿ��ת������
				Class clazz = Class.forName(converterName);
				//���뼯����
				converterList.add((Converter)clazz.newInstance());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return converterList;
	}
}
