package com.starvincci.barcodeprint.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOUtils {
	
	//将文字写入文本中
	public static void  WriteLink(String link,String textUrl)throws IOException {
		   File file = new File(textUrl);
		   if(!file.exists()){
		       file.createNewFile();
		      }
		    FileWriter fileWriter =new FileWriter(file);
	        fileWriter.write(link.toString());
	        fileWriter.flush();
	        fileWriter.close();
	}
 
	//将图片传入指定目录
	public static void WriteImage(String orlImgUrl,String barImageUrl) throws IOException {
		    FileInputStream fis = new FileInputStream(orlImgUrl);
	        // 打开输出流
	        FileOutputStream fos = new FileOutputStream(barImageUrl);
	        int len = 0;
	        byte[] bb=new byte[1024];
	        while ((len= fis.read(bb)) != -1) {
	            fos.write(bb,0,len);
	        }

	        // 关闭流  先开后关  后开先关
	        fos.close(); // 后开先关
	        fis.close(); // 先开后关
	}
	
	public static void test() throws FileNotFoundException {
		 String url="D:\\wip\\imge\\pidai\\CODYJAMES";
		 FileInputStream fis = new FileInputStream(url);
		 if(fis!=null) {
			 System.out.println("不为空！");
		 }
	}
	
	
	public static void main(String[] args) throws IOException {
		String imgUrl="D:\\wip\\ariat.jpg";
		String orlImgUrl="C:\\Users\\admin\\Desktop\\贴纸\\wip\\imge\\pidai\\HiredHand.PNG";
		IOUtils.WriteImage(orlImgUrl, imgUrl);
		
	}
	
}
