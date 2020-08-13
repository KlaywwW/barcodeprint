package com.starvincci.barcodeprint.pojo;

/**
 * 
 * sqlserver数据库
 * bom子件信息 关系表
 * 只写了需要的字段
 * @author admin
 *
 */
public class BmBomMat {
	
	private Integer bomMatId;//主键id
	private Integer bomId;//bom父件关系表id
	private Integer matID;//对应着erpsp中的sp_id

	public Integer getBomMatId() {
		return bomMatId;
	}

	public void setBomMatId(Integer bomMatId) {
		this.bomMatId = bomMatId;
	}

	public Integer getBomId() {
		return bomId;
	}

	public void setBomId(Integer bomId) {
		this.bomId = bomId;
	}

	public Integer getMatID() {
		return matID;
	}

	public void setMatID(Integer matID) {
		this.matID = matID;
	}
}
