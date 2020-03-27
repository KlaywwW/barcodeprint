package com.starvincci.barcodeprint.util;

import com.starvincci.barcodeprint.pojo.Text;

public class TextUtils {
	
	
	
	
	//一键设置参数  分类:价格标签 ，贴纸标签 ,童带皮带皮夹
	//除了以上信息还需要背景图片的地址
	public static Text chuShiHua(int i,int y,String upc,String keHuNo) {
		Text text=new Text();
		text.setUpcNo(upc);
		text.setKeHuNo(keHuNo);
		if(i==1) {//是价格标签
			if(y==1) {//价格贴标
				text.setBackgroundH(200);
				text.setBackgroundW(300);
				text.setQrCodeW(220);
				text.setQrCodeH(80);
				text.setKeHux(40);
				text.setKeHuy(84);
				text.setUpcleftX(24);
				text.setUpcleftY(164);
				text.setUpcRightX(268);
				text.setUpcRightY(164);
				text.setQrCodeX(42);
				text.setQrCodeY(88);
				text.setPriceX(262);
				text.setPriceY(20);
				text.setPriceW(30);
				text.setPriceH(100);
				
				return text;
			}else {//胶片贴胶
				System.out.println("xxx");
				return text;
			}
		
		}else { //是胶片标签
			    System.out.println("xxx");
			    return text;
		}
		
	}
	
	//根据规格写入对应规格的模板中
	
	
	
	

}
