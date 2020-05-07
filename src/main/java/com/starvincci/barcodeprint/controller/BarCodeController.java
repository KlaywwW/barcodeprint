package com.starvincci.barcodeprint.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.krysalis.barcode4j.tools.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.starvincci.barcodeprint.pojo.BarCode;
import com.starvincci.barcodeprint.pojo.Erpsp;
import com.starvincci.barcodeprint.pojo.Erpsp2;
import com.starvincci.barcodeprint.pojo.Text;
import com.starvincci.barcodeprint.read.mapper.ReadMapper;
import com.starvincci.barcodeprint.read.service.erpService;
import com.starvincci.barcodeprint.util.BarcodeUtil;
import com.starvincci.barcodeprint.util.IOUtils;
import com.starvincci.barcodeprint.util.ImageHandleHelper;
import com.starvincci.barcodeprint.util.SwitchLanguageUtils;
import com.starvincci.barcodeprint.util.TextUtils;
import com.starvincci.barcodeprint.write.service.BarCodeService;


@Controller
public class BarCodeController {

	@Autowired
	private erpService eservice;
	@Autowired
	private BarCodeService barCodeService;
	
	/**
	 * 生成条码
	 * @return
	 */
//	@RequestMapping("findBarCode")
//	public String  makeBarCode(Integer id) {
//       //一般不会说没有存货档案
//	   //根据id拿到对应的对象
//		Erpsp erpsp=new Erpsp();
//		erpsp.setSp_id(id);
//		Erpsp  erpspBarCode=eservice.selectAllerpSP(erpsp).get(0);
//	    List<Erpsp> list=new ArrayList<Erpsp>();
//	    list.add(erpspBarCode);
//	    //加入皮带的贴纸规格
//	    Erpsp pointErpSp=eservice.selectBarCodeStyleByErpSpInfo(list).get(0);
//	    //初始化一些信息
//	    String upc= pointErpSp.getErpspPlusmyField03();
//	    Text text=TextUtils.chuShiHua(1, 1,upc,pointErpSp.getErpspPlusmyField12());
//	    text.setBackgroundPath("C:\\Users\\admin\\Desktop\\贴纸打印项目\\barcodeprint\\src\\main\\resources\\static\\assets\\img\\bac.jpg");
//		String left=upc.substring(0, 1);
//		String right=upc.substring(upc.length()-1,upc.length());
//		text.setLeftWord(left);
//		text.setRightWord(right);
//		text.setPriceWord(pointErpSp.getErpspPlusmyField06());
//		//获取生成的价格图片
//		ImageHandleHelper.creatImgOfPrice(text);
//		//旋转
//		try {
//			ImageHandleHelper.spin(text, 270);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		//条形码图片
//		BarcodeUtil.generateFile(upc, text.getQrCodePath());
//		//生成新的图片
//		ImageHandleHelper.overlapImage(text);
//  
//		return "code";
//	}
//	
	
	

	
	/**
	 * 用户点击打印预览，使用命令行打开模板
	 * @param url  需要打开的模板路径
	 * @param id   bom id
	 * @return
	 */
	 @RequestMapping("/point")
	 public String point(String url,Integer id,HttpSession session) {

		    Erpsp erpsp=new Erpsp();
			erpsp.setSp_id(id);
			Erpsp  erpspBarCode=eservice.selectAllErpsp(erpsp);
			System.out.println(url);
			if(erpspBarCode==null) {
				 session.setAttribute("mes", "顺景ERP数据库暂无此款号的数据，请联系开发人员添加存货档案");
				 return "redirect:http://192.168.123.198:8082/index";
			}else {

			
					String f = "D:\\bartender\\皮带\\"+url+".btw";  //要打开的文件路径
					System.out.println(f);
					Runtime r=Runtime.getRuntime();
					   try {
						r.exec("cmd /c start " + f);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("打印皮带错误");
					}
			
			   session.setAttribute("mes", "改变出货时间 po 地址 需要 重新点击打印预览");
			   //http://192.168.123.198:8082/erpSp?id=82257
			   String url2="redirect:http://192.168.123.198:8082/erpSp?id="+id;
				return url2;
		
	       }		 
	   }
	
	//自动更新
	 @RequestMapping("/autoupdate")
	 @ResponseBody




	 public String AutoUpdate() {
		 //查询mysql数据库
		 List<Erpsp2> list=barCodeService.selectAllErpsp2();
		 List<String> Nos=new ArrayList<String>();
		 int count=0;
		 for(int i=0;i<list.size();i++) {
			 //获取当前对象
			
			 Erpsp2 erpsp=list.get(i);
			 String StrNo=erpsp.getNo();
			 int size1=StrNo.length();
			 String commonNo=StrNo.substring(0, size1-2);
			 if(!Nos.contains(commonNo)) {
				 count++;
				 Nos.add(commonNo);
				 System.out.println("开始更新第"+count+"个款"+"款号为:"+commonNo);
				 
				 for(int j=10;j<=80;) {
				 String bomNo=commonNo+j;
				 List <Erpsp> erpspList=eservice.selectERPSPByNo(bomNo);				 
					 if(!erpspList.isEmpty()) {//不为空
						  barCodeService.deleteBarCodeById(erpspList.get(0).getSp_id());
						  erpsp.setNo(bomNo);
						  erpsp.setId(erpspList.get(0).getSp_id());
					     barCodeService.insertBarCode(erpsp);
						    
					 }	 
				 j=j+2;
			 }
			 }	
			
		 }
		 
		 
		 return "已更新完毕";
	 }
	 
}
