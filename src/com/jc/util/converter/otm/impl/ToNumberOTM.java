package com.jc.util.converter.otm.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jc.util.converter.otm.ConverterOTM;
public class ToNumberOTM implements ConverterOTM {

	public String execute(String str) {
		//���ùؼ���
		String keyword = "to_number";
		if(str.contains(keyword)){
			int start = 0;
			while(true){
				start = str.indexOf(keyword,start);
				//���û��to_number���˳�
				if(start == -1){
					break;
				}
				char[] chars = str.toCharArray();
				//�����ų��ֵ�λ��
				List leftPlace = new ArrayList();
				//�����ų��ֵ�λ��
				List rightPlace = new ArrayList();
				for (int i = start; i < chars.length; i++) {
					char nowChar = chars[i];
					if(nowChar == '('){
						leftPlace.add(i);
					}else if(nowChar == ')'){
						rightPlace.add(i);
					}
					//�������ƥ�������ţ�������ѭ��
					if(leftPlace.size() == rightPlace.size() && leftPlace.size() > 0){
						break;
					}
				}
				start = (Integer)rightPlace.get(rightPlace.size()-1);
				str = new StringBuilder(str).insert(start, " as signed").toString();
			}
			str = StringUtils.replace(str, keyword, "cast");
		}
		return str;
	}

}
