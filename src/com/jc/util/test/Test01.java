package com.jc.util.test;

import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.jc.util.OTMUtil;

public class Test01 {
	@Test
	public void test01() {
//		while(true){
			System.out.println("please input oracle_sql:");
			Scanner input = new Scanner(System.in);
//		String inStr = input.nextLine();
			String inStr = "select * from to_Number('aaa'),to_date(sysdate,'yyyy-mm-dd') from dual;";
			System.out.println("converte sql:");
			System.out.println(OTMUtil.converte(inStr));
//		}
	}
}
