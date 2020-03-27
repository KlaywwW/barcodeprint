package com.starvincci.barcodeprint.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.starvincci.barcodeprint.pojo.Text;

public class ImageHandleHelper {//图片拼接
	
	/**
	 * 读取图片的相对路径和绝对路径
	 * 
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 * 路径错误或者不存在该文件是抛出IO异常
	 */
	 public static BufferedImage getBufferedImage(String fileUrl)throws IOException { 
	        File f = new File(fileUrl); 
	        return ImageIO.read(f); 
	    } 
    

	     
	     
	     /**
	      * 设置图片的大小
	      * @param x 长
	      * @param y  宽
	      * @param bfi
	      * @return
	      */
	     public static BufferedImage resizeImage(int x,int y,BufferedImage bfi){
	         BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
	         bufferedImage.getGraphics().drawImage(
	                 bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
	         return bufferedImage;
	     }
	 
	     
	     /**
	      *1参数设定封装在text中
	      *2在带log的背景图片上写入客户编码 写入条形码图片
	      *3在
	      *
	      * @return
	      */
	     public static String overlapImage(Text text){
	        
	    	 try {
	             //设置图片大小
	             BufferedImage background = resizeImage(text.getBackgroundW(),text.getBackgroundH(), ImageIO.read(new File(text.getBackgroundPath())));
	             BufferedImage qrCode = resizeImage(text.getQrCodeW(),text.getQrCodeH(),ImageIO.read(new File(text.getQrCodePath())));
	            // BufferedImage price= resizeImage(80,200,ImageIO.read(new File(text.getFinalPricePath())));
	             
	              Graphics2D g=background.createGraphics();
	              g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB); 
	              g.setColor(Color.black);
	              g.setFont(new Font("微软雅黑", Font.BOLD,26));
	           
	               //客户编码的位置 y为字底部距离图片上方的距离
	              g.drawString(text.getKeHuNo(), text.getKeHux(), text.getKeHuy());
	            
	              //upc号码首尾的位置
	              g.setFont(new Font("微软雅黑",Font.PLAIN,25));
	              g.drawString(text.getLeftWord(), text.getUpcleftX(), text.getUpcleftY());
	              g.drawString(text.getRightWord(), text.getUpcRightX(), text.getUpcRightY());
	              //for循环写入价格
	             
	             //在背景图片上添加二维码图片
	             //y为图片顶部距离背景图片上部高度 单位为像素 像素转换为cm 需要当前分辨率 
	             g.drawImage(qrCode, text.getQrCodeX(), text.getQrCodeY(), qrCode.getWidth(), qrCode.getHeight(), null);
	             //g.drawImage(price,300,0, price.getWidth(), price.getHeight(), null);
	             
	             g.dispose();
	             ImageIO.write(background, "jpg", new File(text.getPidaiJiaGePath()));
	             
	         }catch (Exception e){
	             e.printStackTrace();
	         }
	         return null;
	     }
	     
	  /**
	   * 生成竖排带价格    
	   * @param args
	   */
	   public static String  creatImgOfPrice(Text text) {
		   try {
	             //设置空白图片的图片大小
	             BufferedImage background = resizeImage(200,80, ImageIO.read(new File(text.getBlankPricePath())));

	              Graphics2D g=background.createGraphics();
	              g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB); 
	              g.setColor(Color.black);
	              g.setFont(new Font("微软雅黑", Font.BOLD,26));
	              g.drawString(text.getPriceWord(), 50,45);    
	              g.dispose();
	              ImageIO.write(background, "jpg", new File(text.getWordPricePath()));
	             
	             //旋转
	            
	         }catch (Exception e){
	             e.printStackTrace();
	         }
		   return null;
	   }
	     
	   /**
	    * 旋转图片
	    * @param degree
	    * @throws Exception
	    */
	   public static void  spin(Text text,Integer degree) throws Exception {
		   int swidth = 0; // 旋转后的宽度
	        int sheight = 0; // 旋转后的高度
	        int x; // 原点横坐标
	        int y; // 原点纵坐标
	        File file = new File(text.getWordPricePath());
	        BufferedImage bi = ImageIO.read(file); // 读取该图片
	        degree = degree % 360;
	        if (degree < 0)
	            degree = 360 + degree;// 将角度转换到0-360度之间
	        double theta = Math.toRadians(degree);// 将角度转为弧度
	        if (degree == 180 || degree == 0 || degree == 360) {
	            swidth = bi.getWidth();
	            sheight = bi.getHeight();
	        } else if (degree == 90 || degree == 270) {
	            sheight = bi.getWidth();
	            swidth = bi.getHeight();
	        } else {
	            swidth = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
	                    + bi.getHeight() * bi.getHeight()));
	            sheight = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
	                    + bi.getHeight() * bi.getHeight()));
	        }
	        x = (swidth / 2) - (bi.getWidth() / 2);// 确定原点坐标
	        y = (sheight / 2) - (bi.getHeight() / 2);
	        
	        BufferedImage spinImage = new BufferedImage(swidth, sheight,
	                bi.getType());
	        // 设置图片背景颜色
	        Graphics2D gs = (Graphics2D) spinImage.getGraphics();
	        gs.setColor(Color.white);
	        gs.fillRect(0, 0, swidth, sheight);// 以给定颜色绘制旋转后图片的背景
	        AffineTransform at = new AffineTransform();
	        at.rotate(theta, swidth / 2, sheight / 2);// 旋转图象
	        at.translate(x, y);
	        AffineTransformOp op = new AffineTransformOp(at,AffineTransformOp.TYPE_BICUBIC);
	        spinImage = op.filter(bi, spinImage);
	
	        ImageIO.write(spinImage, "jpg", new File(text.getFinalPricePath())); // 保存图片
	         
	   }
	   
	   
	     





public static void main(String[] args) throws Exception {
	Text text=TextUtils.chuShiHua(1, 1, "701340396449", "N8513408-L");
	text.setBackgroundPath("C:\\Users\\admin\\Desktop\\贴纸打印项目\\barcodeprint\\src\\main\\resources\\static\\assets\\img\\bac.jpg");
	text.setPriceWord("$99.00");
	String upc= "701340396449";
	String left=upc.substring(0, 1);
	String right=upc.substring(upc.length()-1,upc.length());
	System.out.println("upc:"+left+right);
	text.setLeftWord(left);
	text.setRightWord(right);
	//获取生成的价格图片
	creatImgOfPrice(text);
	//旋转
	spin(text, 270);
	//条形码图片
	BarcodeUtil.generateFile(upc, text.getQrCodePath());
	//生成新的图片
	overlapImage(text);
   }
}
