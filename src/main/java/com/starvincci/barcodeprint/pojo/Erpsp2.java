package com.starvincci.barcodeprint.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * mysql数据库   bom表ID和 价格标签规格对应关系图
 * @author admin
 *
 */
public class Erpsp2 implements Serializable{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String no;    //BOM表编号
	private String price; //价格标签规格
	private String neihe; //内盒标签规格
	private String xiaosipian;//小四片规格
	private String jiaopian; //胶片标签规格
	private String guapai;  //挂牌
	private String maodai; 	//帽带
	private String brand;   //品牌
	private String Po;      //po号(童带必须)
	private Integer made;   //生产地址
	private String sellDate;//童带出货时间、
	private Timestamp datetime;//最后修改日期
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNeihe() {
		return neihe;
	}
	public void setNeihe(String neihe) {
		this.neihe = neihe;
	}
	public String getXiaosipian() {
		return xiaosipian;
	}
	public void setXiaosipian(String xiaosipian) {
		this.xiaosipian = xiaosipian;
	}
	public String getJiaopian() {
		return jiaopian;
	}
	public void setJiaopian(String jiaopian) {
		this.jiaopian = jiaopian;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGuapai() {
		return guapai;
	}
	public void setGuapai(String guapai) {
		this.guapai = guapai;
	}
	public String getMaodai() {
		return maodai;
	}
	public void setMaodai(String maodai) {
		this.maodai = maodai;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPo() {
		return Po;
	}
	public void setPo(String po) {
		Po = po;
	}
	public Integer getMade() {
		return made;
	}
	public void setMade(Integer made) {
		this.made = made;
	}
	public String getSellDate() {
		return sellDate;
	}
	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}


}
