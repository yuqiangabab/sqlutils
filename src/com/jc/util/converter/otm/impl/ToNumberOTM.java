package com.jc.util.converter.otm.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jc.util.converter.otm.ConverterOTM;
public class ToNumberOTM implements ConverterOTM {

	public String execute(String str) {
		//设置关键字
		String keyword = "to_number";
		if(str.contains(keyword)){
			int start = 0;
			while(true){
				start = str.indexOf(keyword,start);
				//如果没有to_number则退出
				if(start == -1){
					break;
				}
				char[] chars = str.toCharArray();
				//左括号出现的位置
				List leftPlace = new ArrayList();
				//右括号出现的位置
				List rightPlace = new ArrayList();
				for (int i = start; i < chars.length; i++) {
					char nowChar = chars[i];
					if(nowChar == '('){
						leftPlace.add(i);
					}else if(nowChar == ')'){
						rightPlace.add(i);
					}
					//如果完整匹配右括号，则跳出循环
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
