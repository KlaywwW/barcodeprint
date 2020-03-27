package com.starvincci.barcodeprint.util;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
 
/**
 * 条形码工具类
 *
 * @author tangzz
 * @createDate 2015年9月17日
 * 
 * 
 * N1 N2 N3 N4 N5 N6 N7 N8 N9 N10 N11 C

则检查码之计算步骤如下：

C1 = N1+ N3+N5+N7+N9+N11

C2 = (N2+N4+N6+N8+N10)× 3

CC = (C1+C2) 取个位数

C (检查码) = 10 - CC (若值为10，则取0)
 *
 */
public class BarcodeUtil {
    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
 
    /**
     * 生成字节
     *
     * @param msg
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }
 
    /**
     * 生成到流
     *
     * @param msg
     * @param ous
     */
    public static void generate(String msg, OutputStream ous) {
        if (msg==null || ous == null) {
            return;
        }
 
      
        //不支持upc-a的条码
        UPCABean bean=new UPCABean();
     
 
        // 精细度
        final int dpi =300;
        // module宽度 字符间的间距
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi)*2.8;
        System.out.println("宽为:"+moduleWidth);
        // 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.doQuietZone(false);
        bean.setFontName("701340643802");
        ChecksumMode check=ChecksumMode.CP_AUTO;
        bean.setChecksumMode(check);//是否自动效验
        bean.setBarHeight(4);//设置高度
        String format = "image/png";
        try {
 
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
 
            // 生成条形码
            bean.generateBarcode(canvas, msg);
 
            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
    	  String msg ="701340627246";
  	      System.out.println("正在生成二维码----upc:"+msg);
          String path = "C:\\Users\\admin\\Desktop\\贴纸打印项目\\barcodeprint\\src\\main\\resources\\static\\assets\\img\\upcImg.jpg";
        generateFile(msg, path);
    }
}
