package com.starvincci.barcodeprint.pojo;

/**
 * 
 * sqlserver数据库
 *
 * BOM表父级
 * @author admin
 *
 */
public class BmBom {

	private Integer bomId;//主键id
	private Integer spId;//bom表id(外键)
	private String bomRev;//版本  有01和TW两个版本 我们在数据库中查询用01即可，因为只是为了要查到存货编码

	public Integer getBomId() {
		return bomId;
	}

	public void setBomId(Integer bomId) {
		this.bomId = bomId;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getBomRev() {
		return bomRev;
	}

	public void setBomRev(String bomRev) {
		this.bomRev = bomRev;
	}
}
