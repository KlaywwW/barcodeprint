package com.starvincci.barcodeprint.pojo;



/**
 * 暂不使用
 * @author admin
 *
 */
public class Text {//工具类封装条码   打印一些地址 和参数
	
	private String backgroundPath;//标签背景图片地址(动态)
	private String upcNo;//upc号码(动态)
	private String keHuNo;//(动态)
	private final String qrCodePath="C:\\Users\\admin\\Desktop\\贴纸打印项目\\barcodeprint\\src\\main\\resources\\static\\assets\\img\\upcImg.jpg";//upc号码生成图片的位置，固定
	//最终生成的3.8x2版价格标签的地址
	private final String PidaiJiaGePath="C:\\Users\\admin\\Desktop\\贴纸打印项目\\barcodeprint\\src\\main\\resources\\static\\assets\\img\\pidaiJiaGe.jpg";
	//背景图片的长宽调整
	private  int backgroundW;
	private  int backgroundH;
	//条形码图片的长度 宽度像素
	private  int qrCodeW;
	private  int qrCodeH;
	//客户编码的位置 y为文字距离顶部位置 x为左
	private int keHux;
	private int keHuy;
	//UPC号码首尾文字的写入 (很无奈。。。。因为barcode4j生成的条码不带首尾)
	private  String leftWord;
	private  String rightWord;
	private int upcleftX;
	private int upcleftY;
	private int upcRightX;
	private int upcRightY;
	//条形码图片  写入图片的坐标位置
	private  int qrCodeX;
	private  int qrCodeY;
	//100x30的空白价格图片位置
	private final  String blankPricePath="C:\\Users\\admin\\Desktop\\贴纸打印项目\\barcodeprint\\src\\main\\resources\\static\\assets\\img\\blankPrice.jpg";
	//写入文字后的 价格图片 位置
	private final String  wordPricePath="C:\\Users\\admin\\Desktop\\贴纸打印项目\\barcodeprint\\src\\main\\resources\\static\\assets\\img\\wordPrice.jpg";
	//旋转过后的价格图片位置
	private final String finalPricePath="C:\\Users\\admin\\Desktop\\贴纸打印项目\\barcodeprint\\src\\main\\resources\\static\\assets\\img\\finalPrice.jpg";
	//价格图片在背景图片中的位置
	private int priceX;
	private int priceY;
	private int priceW;
	private int priceH;
	private String priceWord;//(动态)
	
	public String getBackgroundPath() {
		return backgroundPath;
	}
	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}
	public String getUpcNo() {
		return upcNo;
	}
	public void setUpcNo(String upcNo) {
		this.upcNo = upcNo;
	}
	public int getBackgroundW() {
		return backgroundW;
	}
	public void setBackgroundW(int backgroundW) {
		this.backgroundW = backgroundW;
	}
	public int getBackgroundH() {
		return backgroundH;
	}
	public void setBackgroundH(int backgroundH) {
		this.backgroundH = backgroundH;
	}
	public int getQrCodeW() {
		return qrCodeW;
	}
	public void setQrCodeW(int qrCodeW) {
		this.qrCodeW = qrCodeW;
	}
	public int getQrCodeH() {
		return qrCodeH;
	}
	public void setQrCodeH(int qrCodeH) {
		this.qrCodeH = qrCodeH;
	}
	public int getKeHux() {
		return keHux;
	}
	public void setKeHux(int keHux) {
		this.keHux = keHux;
	}
	public int getKeHuy() {
		return keHuy;
	}
	public void setKeHuy(int keHuy) {
		this.keHuy = keHuy;
	}
	public String getLeftWord() {
		return leftWord;
	}
	public void setLeftWord(String leftWord) {
		this.leftWord = leftWord;
	}
	public String getRightWord() {
		return rightWord;
	}
	public void setRightWord(String rightWord) {
		this.rightWord = rightWord;
	}
	public int getUpcleftX() {
		return upcleftX;
	}
	public void setUpcleftX(int upcleftX) {
		this.upcleftX = upcleftX;
	}
	public int getUpcleftY() {
		return upcleftY;
	}
	public void setUpcleftY(int upcleftY) {
		this.upcleftY = upcleftY;
	}
	public int getUpcRightX() {
		return upcRightX;
	}
	public void setUpcRightX(int upcRightX) {
		this.upcRightX = upcRightX;
	}
	public int getUpcRightY() {
		return upcRightY;
	}
	public void setUpcRightY(int upcRightY) {
		this.upcRightY = upcRightY;
	}
	public int getQrCodeX() {
		return qrCodeX;
	}
	public void setQrCodeX(int qrCodeX) {
		this.qrCodeX = qrCodeX;
	}
	public int getQrCodeY() {
		return qrCodeY;
	}
	public void setQrCodeY(int qrCodeY) {
		this.qrCodeY = qrCodeY;
	}
	public int getPriceX() {
		return priceX;
	}
	public void setPriceX(int priceX) {
		this.priceX = priceX;
	}
	public int getPriceY() {
		return priceY;
	}
	public void setPriceY(int priceY) {
		this.priceY = priceY;
	}
	public String getQrCodePath() {
		return qrCodePath;
	}
	public String getPidaiJiaGePath() {
		return PidaiJiaGePath;
	}
	public String getBlankPricePath() {
		return blankPricePath;
	}
	public String getWordPricePath() {
		return wordPricePath;
	}
	public String getFinalPricePath() {
		return finalPricePath;
	}
	public String getKeHuNo() {
		return keHuNo;
	}
	public void setKeHuNo(String keHuNo) {
		this.keHuNo = keHuNo;
	}
	public int getPriceW() {
		return priceW;
	}
	public void setPriceW(int priceW) {
		this.priceW = priceW;
	}
	public int getPriceH() {
		return priceH;
	}
	public void setPriceH(int priceH) {
		this.priceH = priceH;
	}
	public String getPriceWord() {
		return priceWord;
	}
	public void setPriceWord(String priceWord) {
		this.priceWord = priceWord;
	}
	
	
	
	
	
	

}
