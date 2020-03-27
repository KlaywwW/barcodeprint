package com.starvincci.barcodeprint.util;

import com.starvincci.barcodeprint.pojo.Erpsp;

public class ErpSpUtils {//根据下拉框的选择的筛选条件选择
	
	  public static Erpsp getErpspInfo(Integer No,String mes) {
		  Erpsp erpsp=new Erpsp();
		  if(No==1) {  //款号
			  erpsp.setSp_No(mes);
			  return erpsp;
		  }else if(No==2) {//客户编码
			  erpsp.setErpspPlusmyField12(mes);
			  return erpsp;
		  }else if(No==3){//品牌名称
			  erpsp.setErpspPlusmyField05(mes);
			  return erpsp;
		  }else {//工单号
			  return null;  
		  }
		
		 
	  }
	  
	  
	  
	  
	  

}
