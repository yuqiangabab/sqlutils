package com.jc.util.converter.otm.impl;


import org.apache.commons.lang.StringUtils;

import com.jc.util.converter.otm.ConverterOTM;

public class ToDateOTM implements ConverterOTM{

	public String execute(String str) {
		//ÉèÖÃ¹Ø¼ü×Ö
		String keyword = "to_date";
		if(str.contains(keyword)){
			str = StringUtils.replace(str, keyword, "date_format");
		}
		return str;
	}

}
