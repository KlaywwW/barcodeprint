package com.starvincci.barcodeprint.pojo;

import java.io.Serializable;

/**
 * sqlserver数据库 
 * 存货Bom表
 * @author zhangzhe
 *
 */
public class Erpsp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String Sp_No;//存货编码
	private String Sp_Name;//名称
	private Integer Sp_id;//主键id
	private String erpspPlusmyField03;//UPC号码
	private String erpspPlusmyField12;//客户编码
	private String erpspPlusmyField05;//品牌名称
	private String erpspPlusmyField06;//价格
	private String erpspPlusmyField09;//款式类别
	private String erpspPlusmyField10;//备注--英文字母
	private String erpspPlusmyField11;//使用对象
	private String style;//使用的价格贴纸规格  1数据库无贴纸规格 2皮带通用 3童带 4为皮夹
	private String photoStyle;//胶片贴标规格
	private String Sp_Spec;//物料规格
	private String kk;//款式类别
	
	public Erpsp() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Erpsp{" +
				"Sp_No='" + Sp_No + '\'' +
				", Sp_Name='" + Sp_Name + '\'' +
				", Sp_id=" + Sp_id +
				", erpspPlusmyField03='" + erpspPlusmyField03 + '\'' +
				", erpspPlusmyField12='" + erpspPlusmyField12 + '\'' +
				", erpspPlusmyField05='" + erpspPlusmyField05 + '\'' +
				", erpspPlusmyField06='" + erpspPlusmyField06 + '\'' +
				", erpspPlusmyField09='" + erpspPlusmyField09 + '\'' +
				", erpspPlusmyField10='" + erpspPlusmyField10 + '\'' +
				", erpspPlusmyField11='" + erpspPlusmyField11 + '\'' +
				", style='" + style + '\'' +
				", photoStyle='" + photoStyle + '\'' +
				", Sp_Spec='" + Sp_Spec + '\'' +
				", kk='" + kk + '\'' +
				'}';
	}

	public String getErpspPlusmyField10() {
		return erpspPlusmyField10;
	}

	public void setErpspPlusmyField10(String erpspPlusmyField10) {
		this.erpspPlusmyField10 = erpspPlusmyField10;
	}

	public String getErpspPlusmyField03() {
		return erpspPlusmyField03;
	}
	public void setErpspPlusmyField03(String erpspPlusmyField03) {
		this.erpspPlusmyField03 = erpspPlusmyField03;
	}
	public String getErpspPlusmyField12() {
		return erpspPlusmyField12;
	}
	public void setErpspPlusmyField12(String erpspPlusmyField12) {
		this.erpspPlusmyField12 = erpspPlusmyField12;
	}
	public String getErpspPlusmyField05() {
		return erpspPlusmyField05;
	}
	public void setErpspPlusmyField05(String erpspPlusmyField05) {
		this.erpspPlusmyField05 = erpspPlusmyField05;
	}
	public String getErpspPlusmyField06() {
		return erpspPlusmyField06;
	}
	public void setErpspPlusmyField06(String erpspPlusmyField06) {
		this.erpspPlusmyField06 = erpspPlusmyField06;
	}
	public String getErpspPlusmyField09() {
		return erpspPlusmyField09;
	}
	public void setErpspPlusmyField09(String erpspPlusmyField09) {
		this.erpspPlusmyField09 = erpspPlusmyField09;
	}
	public String getErpspPlusmyField11() {
		return erpspPlusmyField11;
	}
	public void setErpspPlusmyField11(String erpspPlusmyField11) {
		this.erpspPlusmyField11 = erpspPlusmyField11;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSp_No() {
		return Sp_No;
	}
	public void setSp_No(String sp_No) {
		Sp_No = sp_No;
	}
	public String getSp_Name() {
		return Sp_Name;
	}
	public void setSp_Name(String sp_Name) {
		Sp_Name = sp_Name;
	}
	public Integer getSp_id() {
		return Sp_id;
	}
	public void setSp_id(Integer sp_id) {
		Sp_id = sp_id;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getPhotoStyle() {
		return photoStyle;
	}
	public void setPhotoStyle(String photoStyle) {
		this.photoStyle = photoStyle;
	}
	public String getSp_Spec() {
		return Sp_Spec;
	}
	public void setSp_Spec(String sp_Spec) {
		Sp_Spec = sp_Spec;
	}

	public String getKk() {
		return kk;
	}

	public void setKk(String kk) {
		this.kk = kk;
	}
    
}
