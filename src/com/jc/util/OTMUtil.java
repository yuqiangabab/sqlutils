package com.jc.util;

/**
 * OracleתMysql������
 * @author Administrator
 *
 */
public class OTMUtil{
	public  static String converte(String str){
		BaseUtil.setConvertersUrl("src\\com\\jc\\util\\converter\\otm\\impl\\converters.txt");
		return BaseUtil.converte(str);
	}
}
