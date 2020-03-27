package com.starvincci.barcodeprint.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarCodeGuiGe {//料号和规格对应关系
	
	private String bianMa;//料号
	private String guiGe;//规格型号
	private String  name;
	private List<BarCodeGuiGe> list;
	
	
	public String getBianMa() {
		return bianMa;
	}
	public void setBianMa(String bianMa) {
		this.bianMa = bianMa;
	}
	public String getGuiGe() {
		return guiGe;
	}
	public void setGuiGe(String guiGe) {
		this.guiGe = guiGe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BarCodeGuiGe> getList() {
		return list;
	}
	public void setList(List<BarCodeGuiGe> list) {
		this.list = list;
	}
	
	public  static Map <String,String>  getMap() {
		Map<String,String>   map1=new HashMap<String,String> ();
		map1.put("LAB-3012", "3.0*1.2CM 無價格");
        map1.put("LAB-3020", "3.0*2.0CM 無價格");
        map1.put("LAB-3525", "3.5*2.5CM ");
        map1.put("LAB-3820", "3.8*2.0CM");
        map1.put("LAB-4020","4.0*2.0CM");
        map1.put("LAB-4025","4.0*2.5CM");
        map1.put("LAB-5515","5.5*1.5CM");
        map1.put("LAB-6517", "6.5*1.7CM 有價格");
        map1.put("LAB-9220","9.2*2.0CM 無價格 無價格");

		return map1;
	}
	
	public static List<String> getNameList(){
		List<String> list=new ArrayList<>();
		list.add("LAB-3012");
		list.add("LAB-3020");
		list.add("LAB-3525");
		list.add("LAB-3820");
		list.add("LAB-4020");
		list.add("LAB-5515");
		list.add("LAB-6517");
		list.add("LAB-9220");
		return list;	
	}

}
