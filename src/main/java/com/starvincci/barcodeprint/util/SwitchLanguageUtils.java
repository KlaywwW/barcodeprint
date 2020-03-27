package com.starvincci.barcodeprint.util;

import java.util.HashMap;
import java.util.Map;

public class SwitchLanguageUtils {//简繁切换
private static final String jianti="带夹";
private static final String fanti="帶夾";

public static  String findMes(String mes) {
	Map<Character, Character> map=new HashMap<Character, Character>();
	for(int i=0;i<jianti.length();i++){
		map.put(fanti.charAt(i),jianti.charAt(i));
	}

	
	String jiantiMes="";
	for(int i=0;i<mes.length();i++) {
		char tempChar=mes.charAt(i);
		Character character=map.get(tempChar);
		char jiantichar;
		if(character==null) {
			jiantichar=tempChar;
		}else {
			jiantichar=character;
		}
		jiantiMes=jiantiMes+String.valueOf(jiantichar);
	}
	return jiantiMes;
}
	

}
