package com.starvincci.barcodeprint.pojo;

public class BarCode {//条形码图片和品牌 关系表
	
	private Integer id;
	private String brand;//品牌
	private String brandType;//品牌类型    皮带钱夹
	private String backUrl;//背景图片url
	private String barCodeType;//贴纸类型 1位价格标签  2 为胶片贴标
	private String style;//款式  童带 或者 成人用
	private String barCodeSize;//贴纸尺寸
	private String imgUrl;
	private String kehuNo;
	private String upc;
	private String price;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrandType() {
		return brandType;
	}
	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getBarCodeType() {
		return barCodeType;
	}
	public void setBarCodeType(String barCodeType) {
		this.barCodeType = barCodeType;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getBarCodeSize() {
		return barCodeSize;
	}
	public void setBarCodeSize(String barCodeSize) {
		this.barCodeSize = barCodeSize;
	}
	public BarCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getKehuNo() {
		return kehuNo;
	}
	public void setKehuNo(String kehuNo) {
		this.kehuNo = kehuNo;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	

}
